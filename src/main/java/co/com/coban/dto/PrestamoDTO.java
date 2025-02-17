package co.com.coban.dto;

import co.com.coban.entity.Cliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PrestamoDTO {
    private Long id;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser un valor positivo")
    private BigDecimal monto;

    @NotNull(message = "El interés es obligatorio")
    @Positive(message = "El interés debe ser un valor positivo")
    private BigDecimal interes;

    @NotNull(message = "La duración en meses es obligatoria")
    @Positive(message = "La duración en meses debe ser un valor positivo")
    private Integer duracionMeses;

    @NotNull(message = "El estado es obligatorio")
    private String estado;


    public PrestamoDTO() { }

    public PrestamoDTO(Long id, BigDecimal monto, BigDecimal interes, Integer duracionMeses, String estado, Long idCliente) {
        this.id = id;
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "Prestamo { " +
                "id=" + id +
                ", monto=" + monto +
                ", interes=" + interes +
                ", duracionMeses=" + duracionMeses +
                ", estado='" + estado + '\'' +
                " }";
    }
}
