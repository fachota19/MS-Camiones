package ar.edu.utn.frc.backend.grupo114.service;

import java.util.List;
import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateCamionDTO;

public interface CamionService {
    List<CamionDTO> listarTodos();
    CamionDTO obtenerPorId(Long id);
    CamionDTO crear(CreateCamionDTO createCamionDTO); 
    List<CamionDTO> listarDisponibles(Double peso, Double volumen);
}