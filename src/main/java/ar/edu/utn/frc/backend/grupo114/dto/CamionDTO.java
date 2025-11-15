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
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public String getTipoCamionNombre() { return tipoCamionNombre; }
    public void setTipoCamionNombre(String tipoCamionNombre) { this.tipoCamionNombre = tipoCamionNombre; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

