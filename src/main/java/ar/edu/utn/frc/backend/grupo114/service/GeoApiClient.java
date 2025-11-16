package ar.edu.utn.frc.backend.grupo114.service;

import ar.edu.utn.frc.backend.grupo114.dto.GeoRutaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "geoapi", url = "${geoapi.url}")
public interface GeoApiClient {

    @GetMapping("/api/rutas")
    GeoRutaResponse calcularRuta(
            @RequestParam("lon1") double lon1,
            @RequestParam("lat1") double lat1,
            @RequestParam("lon2") double lon2,
            @RequestParam("lat2") double lat2
    );
}

