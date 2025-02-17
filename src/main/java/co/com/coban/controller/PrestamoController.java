package co.com.coban.controller;

import co.com.coban.dto.PrestamoDTO;
import co.com.coban.repository.PrestamoRepository;
import co.com.coban.util.PrestamoMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("v1/prestamo")
public class PrestamoController {
    private final PrestamoRepository prestamoRepository;

    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @GetMapping("/hi")
    public String saludo() {
        return "Hola desde el controlador de prestamos";
    }

    @GetMapping("/id")
    public String getPrestamo() {
        return new PrestamoDTO(1L, new BigDecimal(100000), new BigDecimal(0.05), 12, "ACTIVO", 1L).toString();
    }

    @PostMapping("/create")
    public PrestamoDTO createPrestamo(@Valid @RequestBody PrestamoDTO prestamoDTO) {
        return PrestamoMapper.toPrestamoDTO(prestamoRepository.save(PrestamoMapper.toPrestamo(prestamoDTO)));
    }

    @GetMapping("/all")
    public PrestamoDTO[] getAllPrestamos() {
        return prestamoRepository.findAll().stream().map(PrestamoMapper::toPrestamoDTO).toArray(PrestamoDTO[]::new);
    }

    @GetMapping("/{id}")
    public PrestamoDTO getPrestamoById(@PathVariable Long id) {
        return PrestamoMapper.toPrestamoDTO(Objects.requireNonNull(prestamoRepository.findById(id).orElse(null)));
    }

    @PutMapping("/{id}")
    public PrestamoDTO updatePrestamo(@PathVariable Long id, @Valid @RequestBody PrestamoDTO prestamoDTO) {
        prestamoDTO.setId(id);
        return PrestamoMapper.toPrestamoDTO(prestamoRepository.save(PrestamoMapper.toPrestamo(prestamoDTO)));
    }

    @DeleteMapping("/{id}")
    public void deletePrestamo(@PathVariable Long id) {
        prestamoRepository.deleteById(id);
    }


}
