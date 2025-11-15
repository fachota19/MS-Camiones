package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoCamionDTO {
    private Long id;
    private String nombre;
    private Double capacidadPesoMaxKg;
    private Double capacidadVolumenMaxM3;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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