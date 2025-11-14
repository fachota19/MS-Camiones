package ar.edu.utn.frc.backend.grupo114.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateTipoCamionDTO {

    @NotBlank(message = "El nombre de tipo camión es obligatorio.")
    private String nombre;

    @NotNull(message = "La capacidad de peso maximo no puede ser nula")
    @Positive(message = "La capacidad de peso maximo debe ser un numero positivo")
    private Double capacidadPesoMaxKg;

    @NotNull(message = "La capacidad de volumen maximo no puede ser nula")
    @Positive(message = "La capacidad de volumen maximo debe ser un numero positivo")
    private Double capacidadVolumenMaxM3;

    // --- GETTERS Y SETTERS MANUALES ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCapacidadPesoMaxKg() {
        return capacidadPesoMaxKg;
    }

    public void setCapacidadPesoMaxKg(Double capacidadPesoMaxKg) {
        this.capacidadPesoMaxKg = capacidadPesoMaxKg;
    }

    public Double getCapacidadVolumenMaxM3() {
        return capacidadVolumenMaxM3;
    }

    public void setCapacidadVolumenMaxM3(Double capacidadVolumenMaxM3) {
        this.capacidadVolumenMaxM3 = capacidadVolumenMaxM3;
    }
}