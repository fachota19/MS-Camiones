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

}
