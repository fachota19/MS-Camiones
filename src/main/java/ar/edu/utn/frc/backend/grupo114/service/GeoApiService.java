package ar.edu.utn.frc.backend.grupo114.service;

import ar.edu.utn.frc.backend.grupo114.dto.GeoRutaResponse;
import ar.edu.utn.frc.backend.grupo114.dto.RutaResponse;
import org.springframework.stereotype.Service;

@Service
public class GeoApiService {

    private final GeoApiClient geoApiClient;

    public GeoApiService(GeoApiClient geoApiClient) {
        this.geoApiClient = geoApiClient;
    }

    public RutaResponse calcularRuta(double origenLat, double origenLon, double destinoLat, double destinoLon) {

    GeoRutaResponse geo = geoApiClient.calcularRuta(
            origenLon, origenLat,   // lon1, lat1
            destinoLon, destinoLat  // lon2, lat2
    );

    return new RutaResponse(geo.getDistance(), geo.getDuration());
    }
}
