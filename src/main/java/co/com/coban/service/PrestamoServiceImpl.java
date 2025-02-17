package co.com.coban.service;

import co.com.coban.dto.ClienteDTO;
import co.com.coban.dto.PrestamoDTO;
import co.com.coban.dto.PrestamoResponseDTO;
import co.com.coban.entity.Cliente;
import co.com.coban.entity.Prestamo;
import co.com.coban.repository.ClienteRepository;
import co.com.coban.repository.PrestamoRepository;
import co.com.coban.util.ClienteMapper;
import co.com.coban.util.PrestamoMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

    private final ClienteRepository clienteRepository;

    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(ClienteRepository clienteRepository, PrestamoRepository prestamoRepository) {
        this.clienteRepository = clienteRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public PrestamoResponseDTO consultarEstadoPrestamo(Long idPrestamo, Integer idCliente) {

        if (validarCliente(idCliente) == null) {
            return responseClienteNoEncontrado();
        }

        if (validarPrestamo(idPrestamo) == null) {
            return responsePrestamoNoEncontrado();
        }

        return responsePrestamoExitoso(true, "Estado del préstamo consultado con éxito", validarPrestamo(idPrestamo), 200);
    }

    @Override
    public PrestamoResponseDTO solicitarPrestamo(Integer idCliente, PrestamoDTO prestamoDTO) {
        if (validarCliente(idCliente) == null) {
            return responseClienteNoEncontrado();
        }

        if (validarPrestamo(prestamoDTO.getId()) != null) {
            return responsePrestamoExitoso(false, "El préstamo ya existe", null, 400);
        }

        HashMap<String, Object> prestamoSimulado = new HashMap<>();

        Prestamo prestamo = PrestamoMapper.toPrestamo(prestamoDTO, ClienteMapper.toCliente(validarCliente(idCliente)));
        prestamoRepository.save(prestamo);
        // Calculo de la cuota del prestamo
        Double cuota = calcularCuota(prestamoDTO.getMonto(), 0.1, 12);
        prestamoSimulado.put("cuota", cuota);
        prestamoSimulado.put("totalPagar", cuota * 12);
        prestamoSimulado.put("totalInteres", (cuota * 12) - prestamoDTO.getMonto().doubleValue());
        prestamoSimulado.put("prestamo", PrestamoMapper.toPrestamoDTO(prestamo));

        return responsePrestamoExitoso(true, "Préstamo solicitado con éxito", prestamoSimulado, 200);
    }

    @Override
    public PrestamoResponseDTO aprobarPrestamo(Integer idCliente, Long idPrestamo) {
        if (validarCliente(idCliente) == null) {
            return responseClienteNoEncontrado();
        }

        if (validarPrestamo(idPrestamo) == null) {
            return responsePrestamoNoEncontrado();
        }

        Prestamo prestamo = PrestamoMapper.toPrestamo(validarPrestamo(idPrestamo), ClienteMapper.toCliente(validarCliente(idCliente)));
        prestamo.setEstado("APROBADO");
        prestamoRepository.save(prestamo);

        return responsePrestamoExitoso(true, "Préstamo aprobado con éxito", PrestamoMapper.toPrestamoDTO(prestamo), 200);
    }

    @Override
    public PrestamoResponseDTO simularPrestamo(Double valor, Double interes, Integer duracionMeses) {
        HashMap<String, Object> prestamoSimulado = new HashMap<>();

        Double cuota = calcularCuota(BigDecimal.valueOf(valor), interes, duracionMeses);
        prestamoSimulado.put("cuota", cuota);
        prestamoSimulado.put("totalPagar", cuota * duracionMeses);
        prestamoSimulado.put("totalInteres", (cuota * duracionMeses) - valor);

        return responsePrestamoExitoso(true, "Préstamo simulado con éxito", prestamoSimulado, 200);
    }

    public Double calcularCuota(BigDecimal valor, Double tasaInteres, Integer cuotas) {
        Double cuota = valor.doubleValue() * (tasaInteres * Math.pow(1 + tasaInteres, cuotas)) / (Math.pow(1 + tasaInteres, cuotas) - 1);
        return cuota;
    }

    public ClienteDTO validarCliente(Integer idCliente) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        return ClienteMapper.toClienteDTO(clienteOpt.get());
    }

    public PrestamoDTO validarPrestamo(Long idPrestamo) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(idPrestamo);
        return PrestamoMapper.toPrestamoDTO(prestamoOpt.get());
    }

    public PrestamoResponseDTO responseClienteNoEncontrado() {
        return responsePrestamoExitoso(false, "Cliente no encontrado", null, 404);
    }

    public PrestamoResponseDTO responsePrestamoNoEncontrado() {
        return responsePrestamoExitoso(false, "Préstamo no encontrado", null, 404);
    }

    public PrestamoResponseDTO responsePrestamoExitoso(boolean success, String message, Object data, int statusCode) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();
        response.setSuccess(success);
        response.setMessage(message);
        response.setData(data);
        response.setStatusCode(statusCode);
        return response;
    }
}
