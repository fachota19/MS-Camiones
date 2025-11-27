package ar.edu.utn.frc.backend.grupo114.mapper;

import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.models.Camion;
import ar.edu.utn.frc.backend.grupo114.models.TipoCamion;

public class CamionMapper {

    private CamionMapper() {
        // Utility class; only static mapping helpers.
    }

    /**
     * Convierte una entidad Camion a un CamionDTO de respuesta.
     */
    public static CamionDTO toDTO(Camion camion) {
        if (camion == null) {
            return null;
        }

        String estado = camion.getEstado() != null ? camion.getEstado().name() : null;
        TipoCamion tipoCamion = camion.getTipoCamion();
        String tipoCamionNombre = tipoCamion != null ? tipoCamion.getNombre() : null;

        return new CamionDTO(
                camion.getId(),
                camion.getPatente(),
                tipoCamionNombre,
                estado
        );
    }
}
