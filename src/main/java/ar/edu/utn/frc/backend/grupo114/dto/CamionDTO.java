package ar.edu.utn.frc.backend.grupo114.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CamionDTO {
    private Long id;
    private String patente;
    private String tipoCamionNombre;
    private Boolean disponible;
    private Double consumoCombustiblePorKm;
    private Double costoPorKm;
    private Long transportistaId;
}
