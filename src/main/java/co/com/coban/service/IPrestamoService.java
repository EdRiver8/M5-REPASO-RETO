package co.com.coban.service;

import co.com.coban.dto.PrestamoResponseDTO;

public interface IPrestamoService {

    PrestamoResponseDTO solicitarPrestamo(Integer idCliente, Double valor);
    PrestamoResponseDTO aprobarPrestamo(Integer idPrestamo);
    PrestamoResponseDTO consultarEstadoPrestamo(Integer idPrestamo);
    PrestamoResponseDTO simularPrestamo(Integer idCliente, Double valor);

}
