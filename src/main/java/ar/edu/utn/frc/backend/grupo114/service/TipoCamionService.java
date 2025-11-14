package ar.edu.utn.frc.backend.grupo114.service;

import java.util.List;
import ar.edu.utn.frc.backend.grupo114.dto.TipoCamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateTipoCamionDTO;

public interface TipoCamionService {
    List<TipoCamionDTO> listarTodos();
    TipoCamionDTO obtenerPorId(Long id);
    TipoCamionDTO crear(CreateTipoCamionDTO createTipoCamionDTO);
}