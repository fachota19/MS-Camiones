package ar.edu.utn.frc.backend.grupo114.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.utn.frc.backend.grupo114.models.TipoCamion;

@Repository
public interface TipoCamionRepository extends JpaRepository<TipoCamion, Long> {
    TipoCamion findByNombre(String nombre);

}
