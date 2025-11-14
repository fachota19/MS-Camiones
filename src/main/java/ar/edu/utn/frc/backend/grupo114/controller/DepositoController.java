package ar.edu.utn.frc.backend.grupo114.controller;

import ar.edu.utn.frc.backend.grupo114.dto.CreateDepositoDTO;
import ar.edu.utn.frc.backend.grupo114.dto.DepositoDTO;
import ar.edu.utn.frc.backend.grupo114.service.DepositoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depositos")
public class DepositoController {

    private final DepositoService depositoService;

    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @GetMapping
    public ResponseEntity<List<DepositoDTO>> listarTodos() {
        return ResponseEntity.ok(depositoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(depositoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<DepositoDTO> crear(@Valid @RequestBody CreateDepositoDTO createDepositoDTO) {
        DepositoDTO nuevoDeposito = depositoService.crear(createDepositoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDeposito);
    }
}