package ar.edu.utn.frc.backend.grupo114.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateDepositoDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria.")
    private String direccion;

    @NotNull(message = "La latitud es obligatoria.")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria.")
    private Double longitud;

    @NotNull(message = "El costo de estadía diario es obligatorio.")
    @Positive(message = "El costo de estadía diario debe ser un valor positivo.")
    private Double costoEstadiaDiario;

    // --- GETTERS Y SETTERS MANUALES ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getCostoEstadiaDiario() {
        return costoEstadiaDiario;
    }

    public void setCostoEstadiaDiario(Double costoEstadiaDiario) {
        this.costoEstadiaDiario = costoEstadiaDiario;
    }
}