package ar.edu.utn.frc.backend.grupo114.service;

import java.util.List;
import ar.edu.utn.frc.backend.grupo114.dto.DepositoDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateDepositoDTO;

public interface DepositoService {
    List<DepositoDTO> listarTodos();
    DepositoDTO obtenerPorId(Long id);
    DepositoDTO crear(CreateDepositoDTO createDepositoDTO);
}