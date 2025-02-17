package co.com.coban.util;

import co.com.coban.dto.PrestamoDTO;
import co.com.coban.entity.Prestamo;

public class PrestamoMapper {
    public static PrestamoDTO toPrestamoDTO(Prestamo prestamo) {
        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setId(prestamo.getId());
        prestamoDTO.setMonto(prestamo.getMonto());
        prestamoDTO.setInteres(prestamo.getInteres());
        prestamoDTO.setDuracionMeses(prestamo.getDuracionMeses());
        prestamoDTO.setEstado(prestamo.getEstado());
        return prestamoDTO;
    }

    public static Prestamo toPrestamo(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(prestamoDTO.getId());
        prestamo.setMonto(prestamoDTO.getMonto());
        prestamo.setInteres(prestamoDTO.getInteres());
        prestamo.setDuracionMeses(prestamoDTO.getDuracionMeses());
        prestamo.setEstado(prestamoDTO.getEstado());
        return prestamo;
    }
}
