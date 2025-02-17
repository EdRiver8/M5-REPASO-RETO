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

    @GetMapping("/hi")
    public String saludo() {
        return "Hola mundo";
    }

    @GetMapping("/id")
    public ClienteDTO getCliente() {
        return new ClienteDTO("Juan", "juan@mail.com", "1234567890", "Calle 123");
    }

    @PostMapping("/creat")
    public ClienteDTO crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return ClienteMapper.toClienteDTO(cliente);
    }

    @GetMapping("/all")
    public ClienteDTO[] getAllClientes() {
        return clienteRepository.findAll().stream().map(ClienteMapper::toClienteDTO).toArray(ClienteDTO[]::new);
    }

    @GetMapping("/{id}")
    public ClienteDTO getClienteById(@PathVariable Integer id) {
        return ClienteMapper.toClienteDTO(clienteRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ClienteDTO updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toCliente(clienteDTO);
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return ClienteMapper.toClienteDTO(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
    }

}
