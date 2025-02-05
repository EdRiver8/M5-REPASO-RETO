package co.com.coban;

import co.com.coban.entity.Cliente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GestionPrestamosBancariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPrestamosBancariosApplication.class, args);

        Cliente cliente = new Cliente("Edwin", "ed@coban.com", "123456", "Calle 123");
        System.out.println(cliente.getNombre());
        System.out.println(cliente.getEmail());
        System.out.println(cliente.getTelefono());
        System.out.println(cliente.getDireccion());

        List<Cliente> clientes = List.of(
                new Cliente("Johan", "jo@coban.com, ", "123456", "Calle 123"),
                new Cliente("River ", "ri@coban.com, ", "123456", "Calle 123"),
                new Cliente("Goku", "go@coban.com, ", "123456", "Calle 123"));

        for (Cliente c : clientes) {
            System.out.print("Cliente: ");
            System.out.print(c.getNombre() + ", ");
            System.out.print(c.getEmail() + ", ");
            System.out.print(c.getTelefono() + ", ");
            System.out.print(c.getDireccion() + ", ");
            System.out.println();
        }
    }

}
