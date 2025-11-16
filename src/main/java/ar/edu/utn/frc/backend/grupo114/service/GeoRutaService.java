package ar.edu.utn.frc.backend.grupo114.service;

import ar.edu.utn.frc.backend.grupo114.dto.GeoRutaResponse;
import org.springframework.stereotype.Service;

@Service
public class GeoRutaService {

    private final GeoApiClient geoApiClient;

    public GeoRutaService(GeoApiClient geoApiClient) {
        this.geoApiClient = geoApiClient;
    }

    public GeoRutaResponse obtenerRuta(double origenLat, double origenLon, double destinoLat, double destinoLon) {

        // Tu GEOAPI usa lon1, lat1, lon2, lat2  (OSRM style)
        return geoApiClient.calcularRuta(
                origenLon,  // lon1
                origenLat,  // lat1
                destinoLon, // lon2
                destinoLat  // lat2
        );
    }
}
