package ar.edu.utn.frc.backend.grupo114.service.impl;

import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateCamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.RutaResponse;
import ar.edu.utn.frc.backend.grupo114.models.Camion;
import ar.edu.utn.frc.backend.grupo114.models.EstadoCamion;
import ar.edu.utn.frc.backend.grupo114.models.TipoCamion;
import ar.edu.utn.frc.backend.grupo114.repository.CamionRepository;
import ar.edu.utn.frc.backend.grupo114.repository.TipoCamionRepository;
import ar.edu.utn.frc.backend.grupo114.service.CamionService;
import ar.edu.utn.frc.backend.grupo114.service.GeoApiService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CamionServiceImpl implements CamionService {

    private final CamionRepository camionRepository;
    private final TipoCamionRepository tipoCamionRepository;
    private final ModelMapper modelMapper;
    private final GeoApiService geoApiService;  // ← agregado

    public CamionServiceImpl(
            CamionRepository camionRepository,
            TipoCamionRepository tipoCamionRepository,
            ModelMapper modelMapper,
            GeoApiService geoApiService       // ← agregado
    ) {
        this.camionRepository = camionRepository;
        this.tipoCamionRepository = tipoCamionRepository;
        this.modelMapper = modelMapper;
        this.geoApiService = geoApiService;    // ← agregado
    }

    // ===========================================================
    //   CREAR CAMIÓN
    // ===========================================================
    @Override
    @Transactional
    public CamionDTO crear(CreateCamionDTO createCamionDTO) {

        TipoCamion tipo = tipoCamionRepository.findById(createCamionDTO.getTipoCamionId())
                .orElseThrow(() ->
                        new RuntimeException("El tipo de camión con ID "
                                + createCamionDTO.getTipoCamionId() + " no existe.")
                );

        Camion camion = modelMapper.map(createCamionDTO, Camion.class);

        camion.setEstado(EstadoCamion.valueOf(createCamionDTO.getEstado().toUpperCase()));

        camion.setTipoCamion(tipo);

        Camion camionGuardado = camionRepository.save(camion);

        return mapToDto(camionGuardado);
    }

    // ===========================================================
    //   LISTAR
    // ===========================================================
    @Override
    @Transactional(readOnly = true)
    public List<CamionDTO> listarDisponibles(Double peso, Double volumen) {
        return camionRepository.findAvailableCamionesForCarga(peso, volumen)
                .stream()
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

    // ===========================================================
    //   MAPEO DTO
    // ===========================================================
    private CamionDTO mapToDto(Camion camion) {
        CamionDTO dto = modelMapper.map(camion, CamionDTO.class);

        dto.setTipoCamionNombre(camion.getTipoCamion().getNombre());
        dto.setEstado(camion.getEstado().name());

        return dto;
    }

    // ===========================================================
    //   CALCULAR RUTA (CONSUME GEOAPI)
    // ===========================================================
    @Override
    public RutaResponse calcularRuta(double origenLat, double origenLon, double destinoLat, double destinoLon) {

        return geoApiService.calcularRuta(origenLat, origenLon, destinoLat, destinoLon);
    }
}
