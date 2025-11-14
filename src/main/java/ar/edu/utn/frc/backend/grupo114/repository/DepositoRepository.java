package ar.edu.utn.frc.backend.grupo114.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.utn.frc.backend.grupo114.models.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    Deposito findByNombre(String nombre);
}
