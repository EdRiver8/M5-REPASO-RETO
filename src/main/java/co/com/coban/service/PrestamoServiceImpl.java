package co.com.coban.service;

import co.com.coban.dto.PrestamoDTO;
import co.com.coban.dto.PrestamoResponseDTO;
import co.com.coban.entity.Cliente;
import co.com.coban.entity.Prestamo;
import co.com.coban.repository.ClienteRepository;
import co.com.coban.repository.PrestamoRepository;
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
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isEmpty()) {

            response.setSuccess(false);
            response.setMessage("Cliente no encontrado");
            response.setData(null);
            response.setStatusCode(404);
            return response;

        }

        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(idPrestamo);
        if (prestamoOpt.isEmpty()) {

            response.setSuccess(false);
            response.setMessage("Préstamo no encontrado");
            response.setData(null);
            response.setStatusCode(404);
            return response;

        }

        response.setSuccess(true);
        response.setMessage("Estado del préstamo consultado con éxito");
        response.setData(PrestamoMapper.toPrestamoDTO(prestamoOpt.get()));
        response.setStatusCode(200);
        return response;
    }

    @Override
    public PrestamoResponseDTO solicitarPrestamo(Integer idCliente, PrestamoDTO prestamoDTO) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isEmpty()) {

            response.setSuccess(false);
            response.setMessage("Cliente no encontrado");
            response.setData(null);
            response.setStatusCode(404);
            return response;

        }

        Prestamo prestamo = PrestamoMapper.toPrestamo(prestamoDTO, clienteOpt.get());
        prestamoRepository.save(prestamo);
        // Calculo de la cuota del prestamo
        Double cuota = calcularCuota(prestamoDTO.getMonto(), 0.1, 12);
        response.setSuccess(true);
        response.setMessage("Préstamo solicitado con éxito");
        response.setData(cuota);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public PrestamoResponseDTO aprobarPrestamo(Integer idCliente, Long idPrestamo) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isEmpty()) {

            response.setSuccess(false);
            response.setMessage("Cliente no encontrado");
            response.setData(null);
            response.setStatusCode(404);
            return response;

        }

        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(idPrestamo);
        if (prestamoOpt.isEmpty()) {

            response.setSuccess(false);
            response.setMessage("Préstamo no encontrado");
            response.setData(null);
            response.setStatusCode(404);
            return response;

        }

        Prestamo prestamo = prestamoOpt.get();
        prestamo.setEstado("APROBADO");
        prestamoRepository.save(prestamo);

        response.setSuccess(true);
        response.setMessage("Préstamo aprobado con éxito");
        response.setData(PrestamoMapper.toPrestamoDTO(prestamo));
        response.setStatusCode(200);
        return response;
    }

    @Override
    public PrestamoResponseDTO simularPrestamo(Double valor, Double interes, Integer duracionMeses) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();
        HashMap<String, Object> prestamoSimulado = new HashMap<>();

        Double cuota = calcularCuota(BigDecimal.valueOf(valor), interes, duracionMeses);
        prestamoSimulado.put("cuota", cuota);
        prestamoSimulado.put("totalPagar", cuota * duracionMeses);
        prestamoSimulado.put("totalInteres", (cuota * duracionMeses) - valor);
        response.setData(prestamoSimulado);

        response.setSuccess(true);
        response.setMessage("Préstamo simulado con éxito");
        response.setStatusCode(200);
        return response;
    }

    public Double calcularCuota(BigDecimal valor, Double tasaInteres, Integer cuotas) {
        Double cuota = valor.doubleValue() * (tasaInteres * Math.pow(1 + tasaInteres, cuotas)) / (Math.pow(1 + tasaInteres, cuotas) - 1);
        return cuota;
    }
}
