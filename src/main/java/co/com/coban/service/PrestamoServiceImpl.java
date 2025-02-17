package co.com.coban.service;

import co.com.coban.dto.PrestamoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

    @Override
    public PrestamoResponseDTO solicitarPrestamo(Integer idCliente, Double valor) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        response.setSuccess(true);
        response.setMessage("Préstamo solicitado con éxito");
        response.setData(null);
        return response;
    }

    @Override
    public PrestamoResponseDTO aprobarPrestamo(Integer idPrestamo) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        response.setSuccess(true);
        response.setMessage("Préstamo aprobado con éxito");
        response.setData(null);
        return response;
    }

    @Override
    public PrestamoResponseDTO consultarEstadoPrestamo(Integer idPrestamo) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        response.setSuccess(true);
        response.setMessage("Estado del préstamo consultado con éxito");
        response.setData(null);
        return response;
    }

    @Override
    public PrestamoResponseDTO simularPrestamo(Integer idCliente, Double valor) {
        PrestamoResponseDTO response = new PrestamoResponseDTO();

        response.setSuccess(true);
        response.setMessage("Préstamo simulado con éxito");
        response.setData(null);
        return response;
    }
}
