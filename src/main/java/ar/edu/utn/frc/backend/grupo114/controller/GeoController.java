package ar.edu.utn.frc.backend.grupo114.controller;
import ar.edu.utn.frc.backend.grupo114.dto.GeoRutaResponse;
import ar.edu.utn.frc.backend.grupo114.service.GeoRutaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruta")
public class GeoController {

    private final GeoRutaService geoRutaService;

    public GeoController(GeoRutaService geoRutaService) {
        this.geoRutaService = geoRutaService;
    }

    @GetMapping
    public GeoRutaResponse getRuta(
            @RequestParam double startLat,
            @RequestParam double startLon,
            @RequestParam double endLat,
            @RequestParam double endLon) {

        return geoRutaService.obtenerRuta(startLat, startLon, endLat, endLon);
    }
}