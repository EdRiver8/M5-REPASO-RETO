package co.com.coban.dto;

import co.com.coban.entity.Prestamo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {
    @NotNull(message = "El id es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El id debe tener máximo 10 enteros y 2 decimales")
    private Integer id;
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El email es obligatorio")
    private String email;
    @NotNull(message = "El teléfono es obligatorio")
    private String telefono;
    @NotNull(message = "La dirección es obligatoria")
    private String direccion;
    private List<PrestamoDTO> prestamos = new ArrayList<>();

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String email, String telefono, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
