package co.com.coban.controller;

import co.com.coban.entity.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cliente")
public class ClienteCrontroller {

//    private Cliente cliente;
//
//    public ClienteCrontroller(Cliente cliente) {
//        this.cliente = cliente;
//    }

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola mundo";
    }

}
