package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;


@Data 
public class RutaResponse {
    private double distance;
    private double duration;

    public RutaResponse() {
    }

    // Constructor con parámetros
    public RutaResponse(double distance, double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    // Getter y Setter de distance
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Getter y Setter de duration
    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}

