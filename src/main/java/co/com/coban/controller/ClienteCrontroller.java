package co.com.coban.controller;

import co.com.coban.dto.ClienteDTO;
import co.com.coban.entity.Cliente;
import co.com.coban.repository.ClienteRepository;
import co.com.coban.util.ClienteMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cliente")
public class ClienteCrontroller {

    private final ClienteRepository clienteRepository;

    public ClienteCrontroller(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

//    private Cliente cliente;
//
//    public ClienteCrontroller(Cliente cliente) {
//        this.cliente = cliente;
//    }

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola mundo";
    }


    // mostrar un cliente quemado para pruebas
    @GetMapping("/id")
    public ClienteDTO getCliente() {
        return new ClienteDTO("Juan", "juan@mail.com", "1234567890", "Calle 123");
    }

    @PostMapping("/crear")
    public ClienteDTO crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return ClienteMapper.toClienteDTO(cliente);
    }

    // mostrar todos los clientes
//    @GetMapping("/all")
//    public ClienteDTO[] getAllClientes() {
//
//    }

}
