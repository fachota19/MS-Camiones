package ar.edu.utn.frc.backend.grupo114.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCamionDTO {

    @NotBlank(message = "La patente es obligatoria.")
    @Size(max = 10, message = "La patente no puede tener más de 10 caracteres.")
    private String patente;

    @NotNull(message = "El id del tipo de camión es obligatorio.")
    private Long tipoCamionId;

    @NotNull(message = "El consumo de combustible por km es obligatorio.")
    @Positive(message = "El consumo de combustible debe ser un valor positivo.")
    private Double consumoCombustiblePorKm;

    @NotNull(message = "El costo por km es obligatorio.")
    @Positive(message = "El costo por km debe ser un valor positivo.")
    private Double costoPorKm;

    @NotNull(message = "El id del transportista es obligatorio.")
    private Long transportistaId;


    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Long getTipoCamionId() {
        return tipoCamionId;
    }

    public void setTipoCamionId(Long tipoCamionId) {
        this.tipoCamionId = tipoCamionId;
    }

    public Double getConsumoCombustiblePorKm() {
        return consumoCombustiblePorKm;
    }

    public void setConsumoCombustiblePorKm(Double consumoCombustiblePorKm) {
        this.consumoCombustiblePorKm = consumoCombustiblePorKm;
    }

    public Double getCostoPorKm() {
        return costoPorKm;
    }

    public void setCostoPorKm(Double costoPorKm) {
        this.costoPorKm = costoPorKm;
    }

    public Long getTransportistaId() {
        return transportistaId;
    }

    public void setTransportistaId(Long transportistaId) {
        this.transportistaId = transportistaId;
    }
}