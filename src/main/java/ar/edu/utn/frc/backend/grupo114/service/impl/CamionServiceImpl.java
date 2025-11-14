package ar.edu.utn.frc.backend.grupo114.service.impl;
import ar.edu.utn.frc.backend.grupo114.repository.CamionRepository;
import ar.edu.utn.frc.backend.grupo114.repository.TipoCamionRepository;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateCamionDTO;
import ar.edu.utn.frc.backend.grupo114.models.Camion;
import ar.edu.utn.frc.backend.grupo114.models.TipoCamion;
import ar.edu.utn.frc.backend.grupo114.service.CamionService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service

public class CamionServiceImpl implements CamionService {

    private final CamionRepository camionRepository;
    private final TipoCamionRepository tipoCamionRepository;
    private final ModelMapper modelMapper;

    public CamionServiceImpl(CamionRepository camionRepository, TipoCamionRepository tipoCamionRepository, ModelMapper modelMapper) {
        this.camionRepository = camionRepository;
        this.tipoCamionRepository = tipoCamionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public CamionDTO crear(CreateCamionDTO createCamionDTO) {
        TipoCamion tipo = tipoCamionRepository.findById(createCamionDTO.getTipoCamionId())
                .orElseThrow(() -> new RuntimeException("El tipo de camión con ID " + createCamionDTO.getTipoCamionId() + " no existe."));
        

        Camion camion = modelMapper.map(createCamionDTO, Camion.class);

        camion.setTipoCamion(tipo);
        camion.setDisponible(true);

        Camion camionGuardado = camionRepository.save(camion);

        return mapToDto(camionGuardado);

    }

    @Override
    @Transactional(readOnly = true)
    public List<CamionDTO> listarDisponibles(Double peso, Double volumen) {
        // Usa la Query personalizada del repositorio
        List<Camion> camiones = camionRepository.findAvailableCamionesForCarga(peso, volumen);
        
        return camiones.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CamionDTO> listarTodos() {
        return camionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CamionDTO obtenerPorId(Long id) {
        Camion camion = camionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con ID: " + id));
        return mapToDto(camion);
    }

    // Método auxiliar privado para controlar el mapeo de salida
    private CamionDTO mapToDto(Camion camion) {
        CamionDTO dto = modelMapper.map(camion, CamionDTO.class);
        
        // ModelMapper es inteligente, pero a veces es bueno asegurar el nombre del tipo
        if (camion.getTipoCamion() != null) {
            dto.setTipoCamionNombre(camion.getTipoCamion().getNombre());
        }
        return dto;
    }


}
