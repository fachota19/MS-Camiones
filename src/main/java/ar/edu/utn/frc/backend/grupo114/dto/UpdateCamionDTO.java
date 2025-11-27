package ar.edu.utn.frc.backend.grupo114.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateCamionDTO {

    @NotNull(message = "La patente no puede ser nula")
    private String patente;

    @NotNull(message = "El ID del transportista no puede ser nulo")
    private Long transportistaId;

    @NotNull(message = "El ID del tipo de camión no puede ser nulo")
    private Long tipoCamionId;

    private String estado; // Se espera que sea un valor del enum EstadoCamion (ej: DISPONIBLE, OCUPADO)

    @NotNull(message = "El consumo de combustible no puede ser nulo")
    @Positive(message = "El consumo de combustible debe ser positivo")
    private Double consumoCombustiblePorKm;

    @NotNull(message = "El costo por KM no puede ser nulo")
    @Positive(message = "El costo por KM debe ser positivo")
    private Double costoPorKm;
}