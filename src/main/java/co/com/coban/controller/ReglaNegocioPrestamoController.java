package co.com.coban.controller;

import co.com.coban.dto.PrestamoDTO;
import co.com.coban.dto.PrestamoResponseDTO;
import co.com.coban.service.IPrestamoService;
import jakarta.validation.Valid;
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

    @GetMapping("/consultar")
    public ResponseEntity<PrestamoResponseDTO> consultarEstadoPrestamo(@RequestParam Long idPrestamo, @RequestParam Integer idCliente) {
        PrestamoResponseDTO response = prestamoService.consultarEstadoPrestamo(idPrestamo, idCliente);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/solicitar")
    public ResponseEntity<PrestamoResponseDTO> solicitarPrestamo(
            @RequestParam Integer idCliente, @Valid @RequestBody PrestamoDTO prestamoDTO) {
        PrestamoResponseDTO response = prestamoService.solicitarPrestamo(idCliente, prestamoDTO);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/aprobar")
    public ResponseEntity<PrestamoResponseDTO> aprobarPrestamo(
            @RequestParam Integer idCliente, @RequestParam Long idPrestamo) {
        PrestamoResponseDTO response = prestamoService.aprobarPrestamo(idCliente, idPrestamo);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/simular")
    public ResponseEntity<PrestamoResponseDTO> simularPrestamo(@RequestParam Double valor, @RequestParam Double interes, @RequestParam Integer duracionMeses) {
        PrestamoResponseDTO response = prestamoService.simularPrestamo(valor, interes, duracionMeses);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
