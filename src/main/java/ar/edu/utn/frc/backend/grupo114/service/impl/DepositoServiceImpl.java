package ar.edu.utn.frc.backend.grupo114.service.impl;

import ar.edu.utn.frc.backend.grupo114.dto.CreateDepositoDTO;
import ar.edu.utn.frc.backend.grupo114.dto.DepositoDTO;
import ar.edu.utn.frc.backend.grupo114.dto.UpdateDepositoDTO;
import ar.edu.utn.frc.backend.grupo114.models.Deposito;
import ar.edu.utn.frc.backend.grupo114.repository.DepositoRepository;
import ar.edu.utn.frc.backend.grupo114.service.DepositoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository depositoRepository;
    private final ModelMapper modelMapper;

    public DepositoServiceImpl(DepositoRepository depositoRepository, ModelMapper modelMapper) {
        this.depositoRepository = depositoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositoDTO> listarTodos() {
        return depositoRepository.findAll().stream()
                .map(deposito -> modelMapper.map(deposito, DepositoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepositoDTO obtenerPorId(Long id) {
        Deposito deposito = depositoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deposito no encontrado con ID: " + id));
        return modelMapper.map(deposito, DepositoDTO.class);
    }

    @Override
    @Transactional
    public DepositoDTO crear(CreateDepositoDTO createDepositoDTO) {
        Deposito deposito = modelMapper.map(createDepositoDTO, Deposito.class);
        Deposito depositoGuardado = depositoRepository.save(deposito);
        return modelMapper.map(depositoGuardado, DepositoDTO.class);
    }

    @Override
    @Transactional
    public DepositoDTO actualizar(Long id, UpdateDepositoDTO dto) {
        Deposito deposito = depositoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deposito no encontrado con ID: " + id));

        deposito.setNombre(dto.getNombre());
        deposito.setDireccion(dto.getDireccion());
        deposito.setLatitud(dto.getLatitud());
        deposito.setLongitud(dto.getLongitud());
        deposito.setCostoEstadiaDiario(dto.getCostoEstadiaDiario());

        Deposito actualizado = depositoRepository.save(deposito);
        return modelMapper.map(actualizado, DepositoDTO.class);
    }
}
