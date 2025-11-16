package ar.edu.utn.frc.backend.grupo114.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "camiones")
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String patente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCamion estado; 

    @Column(name = "consumo_combustible_por_km", nullable = false)
    private BigDecimal consumoCombustiblePorKm; 

    @Column(name = "costo_por_km", nullable = false)
    private BigDecimal costoPorKm; // CAMBIO A BigDecimal

    @Column(name = "transportista_id", nullable = false)
    private Long transportistaId;

    @ManyToOne
    @JoinColumn(name = "tipo_camion_id", nullable = false)
    private TipoCamion tipoCamion;

    public Camion() {}

    public Camion(
            String patente,
            EstadoCamion estado,
            BigDecimal consumoCombustiblePorKm,
            BigDecimal costoPorKm,
            Long transportistaId,
            TipoCamion tipoCamion
    ) {
        this.patente = patente;
        this.estado = estado;
        this.consumoCombustiblePorKm = consumoCombustiblePorKm;
        this.costoPorKm = costoPorKm;
        this.transportistaId = transportistaId;
        this.tipoCamion = tipoCamion;
    }

    // ---------- GETTERS & SETTERS ----------

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

    public EstadoCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }

    public BigDecimal getConsumoCombustiblePorKm() {
        return consumoCombustiblePorKm;
    }

    public void setConsumoCombustiblePorKm(BigDecimal consumoCombustiblePorKm) {
        this.consumoCombustiblePorKm = consumoCombustiblePorKm;
    }

    public BigDecimal getCostoPorKm() {
        return costoPorKm;
    }

    public void setCostoPorKm(BigDecimal costoPorKm) {
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
