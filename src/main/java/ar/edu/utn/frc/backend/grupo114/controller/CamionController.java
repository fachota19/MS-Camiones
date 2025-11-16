package ar.edu.utn.frc.backend.grupo114.controller;

import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateCamionDTO;
import ar.edu.utn.frc.backend.grupo114.service.CamionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @GetMapping
    public ResponseEntity<List<CamionDTO>> listarTodos() {
        return ResponseEntity.ok(camionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CamionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(camionService.obtenerPorId(id));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<CamionDTO>> listarDisponibles(
            @RequestParam Double peso,
            @RequestParam Double volumen
    ) {
        return ResponseEntity.ok(camionService.listarDisponibles(peso, volumen));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CreateCamionDTO createCamionDTO) {
        try {
            CamionDTO nuevoCamion = camionService.crear(createCamionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCamion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // -------------------------------------------------------
    // NUEVO ENDPOINT: calcular distancia usando GEOAPI
    // -------------------------------------------------------
    @GetMapping("/distancia")
    public ResponseEntity<?> calcularDistancia(
            @RequestParam double origenLat,
            @RequestParam double origenLon,
            @RequestParam double destinoLat,
            @RequestParam double destinoLon
    ) {
        try {
            return ResponseEntity.ok(
                    camionService.calcularRuta(origenLat, origenLon, destinoLat, destinoLon)
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .body("Error al consultar GeoAPI: " + e.getMessage());
        }
    }
}
