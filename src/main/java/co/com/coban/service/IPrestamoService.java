package co.com.coban.service;

import co.com.coban.dto.PrestamoDTO;
import co.com.coban.dto.PrestamoResponseDTO;

public interface IPrestamoService {
    PrestamoResponseDTO consultarEstadoPrestamo(Long idPrestamo, Integer idCliente);
    PrestamoResponseDTO solicitarPrestamo(Integer idCliente, PrestamoDTO prestamoDTO);
    PrestamoResponseDTO aprobarPrestamo(Integer idCliente, Long idPrestamo);
    PrestamoResponseDTO simularPrestamo(Double valor, Double interes, Integer duracionMeses);

}
