package ar.edu.utn.frc.backend.grupo114.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


@Entity
@Table(name = "tipos_camion")
public class TipoCamion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "capacidad_peso_max_kg", nullable = false)
    private Double capacidadPesoMaxKg;

    @Column(name = "capacidad_volumen_max_m3", nullable = false)
    private Double capacidadVolumenMaxM3;

    protected TipoCamion() {
    }

    public TipoCamion(String nombre, Double capacidadPesoMaxKg, Double capacidadVolumenMaxM3) {
        this.nombre = nombre;
        this.capacidadPesoMaxKg = capacidadPesoMaxKg;
        this.capacidadVolumenMaxM3 = capacidadVolumenMaxM3;
    }

    // Getters y Setters
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
