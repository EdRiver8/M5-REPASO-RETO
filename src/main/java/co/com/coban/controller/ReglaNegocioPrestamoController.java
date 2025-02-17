package co.com.coban.controller;

import co.com.coban.dto.PrestamoResponseDTO;
import co.com.coban.service.IPrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operaciones-prestamo")
public class ReglaNegocioPrestamoController {
    private final IPrestamoService prestamoService;

    public ReglaNegocioPrestamoController(IPrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<PrestamoResponseDTO> solicitarPrestamo(@RequestParam Integer idCliente, @RequestParam Double valor) {
        PrestamoResponseDTO response = prestamoService.solicitarPrestamo(idCliente, valor);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/aprobar")
    public ResponseEntity<PrestamoResponseDTO> aprobarPrestamo(@RequestParam Integer idPrestamo) {
        PrestamoResponseDTO response = prestamoService.aprobarPrestamo(idPrestamo);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/consultar")
    public ResponseEntity<PrestamoResponseDTO> consultarEstadoPrestamo(@RequestParam Integer idPrestamo) {
        PrestamoResponseDTO response = prestamoService.consultarEstadoPrestamo(idPrestamo);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/simular")
    public ResponseEntity<PrestamoResponseDTO> simularPrestamo(@RequestParam Integer idCliente, @RequestParam Double valor) {
        PrestamoResponseDTO response = prestamoService.simularPrestamo(idCliente, valor);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
