package ar.edu.utn.frc.backend.grupo114.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.utn.frc.backend.grupo114.models.Camion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

    Camion findByPatente(String patente);

    @Query("""
        SELECT c 
        FROM Camion c 
        JOIN c.tipoCamion tc
        WHERE c.estado = ar.edu.utn.frc.backend.grupo114.models.EstadoCamion.DISPONIBLE
            AND tc.capacidadPesoMaxKg >= :pesoCarga
            AND tc.capacidadVolumenMaxM3 >= :volumenCarga
    """)
    List<Camion> findAvailableCamionesForCarga(
            @Param("pesoCarga") Double pesoCarga,
            @Param("volumenCarga") Double volumenCarga
    );
}

