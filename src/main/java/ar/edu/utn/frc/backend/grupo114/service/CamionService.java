package ar.edu.utn.frc.backend.grupo114.service;

import java.util.List;
import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateCamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.RutaResponse;
import ar.edu.utn.frc.backend.grupo114.dto.UpdateCamionDTO;
import ar.edu.utn.frc.backend.grupo114.models.Camion;

public interface CamionService {
    List<CamionDTO> listarTodos();
    CamionDTO obtenerPorId(Long id);
    CamionDTO crear(CreateCamionDTO createCamionDTO); 
    List<CamionDTO> listarDisponibles(Double peso, Double volumen);
    RutaResponse calcularRuta(double origenLat, double origenLon, double destinoLat, double destinoLon);
    Camion actualizar(Long id, UpdateCamionDTO dto);

}