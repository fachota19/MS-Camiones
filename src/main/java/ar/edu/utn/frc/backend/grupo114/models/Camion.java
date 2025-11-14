package ar.edu.utn.frc.backend.grupo114.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "camiones")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patente", nullable = false, unique = true, length = 10)
    private String patente;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible;

    @Column(name = "consumo_combustible_por_km", nullable = false)
    private Double consumoCombustiblePorKm;

    @Column(name = "costo_por_km", nullable = false)
    private Double costoPorKm;

    @Column(name = "transportista_id", nullable = false)
    private Long transportistaId;

    @ManyToOne
    @JoinColumn(name = "tipo_camion_id")
    private TipoCamion tipoCamion;

    protected Camion() {
    }
    
    public Camion(String patente, Boolean disponible, Double consumoCombustiblePorKm, Double costoPorKm, Long transportistaId, TipoCamion tipoCamion) {
        this.patente = patente;
        this.disponible = disponible;
        this.consumoCombustiblePorKm = consumoCombustiblePorKm;
        this.costoPorKm = costoPorKm;
        this.transportistaId = transportistaId;
        this.tipoCamion = tipoCamion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
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

    public TipoCamion getTipoCamion() {
        return tipoCamion;
    }

    public void setTipoCamion(TipoCamion tipoCamion) {
        this.tipoCamion = tipoCamion;
    }

}
