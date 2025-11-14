package ar.edu.utn.frc.backend.grupo114.controller;

import ar.edu.utn.frc.backend.grupo114.dto.CreateTipoCamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.TipoCamionDTO;
import ar.edu.utn.frc.backend.grupo114.service.TipoCamionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-camion")
public class TipoCamionController {

    private final TipoCamionService tipoCamionService;

    public TipoCamionController(TipoCamionService tipoCamionService) {
        this.tipoCamionService = tipoCamionService;
    }

    @GetMapping
    public ResponseEntity<List<TipoCamionDTO>> listarTodos() {
        return ResponseEntity.ok(tipoCamionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCamionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoCamionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoCamionDTO> crear(@Valid @RequestBody CreateTipoCamionDTO createTipoCamionDTO) {
        TipoCamionDTO nuevoTipo = tipoCamionService.crear(createTipoCamionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
    }
}