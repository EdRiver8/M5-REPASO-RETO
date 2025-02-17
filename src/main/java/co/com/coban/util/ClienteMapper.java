package co.com.coban.util;

import co.com.coban.dto.ClienteDTO;
import co.com.coban.entity.Cliente;

import java.util.stream.Collectors;

public class ClienteMapper {
    public static ClienteDTO toClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setPrestamos(cliente.getPrestamos().stream().map(PrestamoMapper::toPrestamoDTO).collect(Collectors.toList()));
        return clienteDTO;
    }

    public static Cliente toCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDireccion(clienteDTO.getDireccion());
        return cliente;
    }
}
