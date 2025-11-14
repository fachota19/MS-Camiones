package ar.edu.utn.frc.backend.grupo114.service.impl;

import ar.edu.utn.frc.backend.grupo114.dto.CreateTipoCamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.TipoCamionDTO;
import ar.edu.utn.frc.backend.grupo114.models.TipoCamion;
import ar.edu.utn.frc.backend.grupo114.repository.TipoCamionRepository;
import ar.edu.utn.frc.backend.grupo114.service.TipoCamionService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoCamionServiceImpl implements TipoCamionService {

    private final TipoCamionRepository tipoCamionRepository;
    private final ModelMapper modelMapper;

    public TipoCamionServiceImpl(TipoCamionRepository tipoCamionRepository, ModelMapper modelMapper) {
        this.tipoCamionRepository = tipoCamionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoCamionDTO> listarTodos() {
        return tipoCamionRepository.findAll().stream()
                .map(tipo -> modelMapper.map(tipo, TipoCamionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TipoCamionDTO obtenerPorId(Long id) {
        TipoCamion tipo = tipoCamionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de camión no encontrado con ID: " + id));
        return modelMapper.map(tipo, TipoCamionDTO.class);
    }

    @Override
    @Transactional
    public TipoCamionDTO crear(CreateTipoCamionDTO createTipoCamionDTO) {
        TipoCamion tipo = modelMapper.map(createTipoCamionDTO, TipoCamion.class);
        TipoCamion tipoGuardado = tipoCamionRepository.save(tipo);
        return modelMapper.map(tipoGuardado, TipoCamionDTO.class);
    }
}