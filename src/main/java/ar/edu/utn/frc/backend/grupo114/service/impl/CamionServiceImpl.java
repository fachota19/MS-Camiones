package ar.edu.utn.frc.backend.grupo114.service.impl;

import ar.edu.utn.frc.backend.grupo114.dto.CamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.CreateCamionDTO;
import ar.edu.utn.frc.backend.grupo114.dto.UpdateCamionDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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
    @Override
    @Transactional
    public Camion actualizar(Long id, UpdateCamionDTO dto) {
        
        // 1. Buscar el camión existente. Si no existe, lanza 404.
        Camion camionExistente = camionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                    "Camión con ID " + id + " no encontrado."));
        
        // 2. Aplicar los cambios del DTO
        camionExistente.setPatente(dto.getPatente());
        
        // 2.1. Actualizar Tipo de Camión (si cambia la capacidad)
        if (!camionExistente.getTipoCamion().getId().equals(dto.getTipoCamionId())) {
            TipoCamion nuevoTipo = tipoCamionRepository.findById(dto.getTipoCamionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                                    "Tipo de Camión con ID " + dto.getTipoCamionId() + " no encontrado."));
            camionExistente.setTipoCamion(nuevoTipo);
        }
        
        // 2.2. Actualizar campos de costo y consumo
        if (dto.getConsumoCombustiblePorKm() != null) {
            camionExistente.setConsumoCombustiblePorKm(
                    BigDecimal.valueOf(dto.getConsumoCombustiblePorKm()));
        }
        if (dto.getCostoPorKm() != null) {
            camionExistente.setCostoPorKm(
                    BigDecimal.valueOf(dto.getCostoPorKm()));
        }
        
        // 2.3. Actualizar Transportista (si cambia el dueño)
        camionExistente.setTransportistaId(dto.getTransportistaId());
        
        // 2.4. Actualizar Estado (si se proporciona un estado válido)
        try {
            EstadoCamion nuevoEstado = EstadoCamion.valueOf(dto.getEstado().toUpperCase());
            camionExistente.setEstado(nuevoEstado);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                                    "Estado de camión inválido: " + dto.getEstado());
        }
        
        // 3. Guardar y retornar la entidad actualizada
        return camionRepository.save(camionExistente);
    }

    // ===========================================================
    //   CALCULAR RUTA (CONSUME GEOAPI)
    // ===========================================================
    @Override
    public RutaResponse calcularRuta(double origenLat, double origenLon, double destinoLat, double destinoLon) {

        return geoApiService.calcularRuta(origenLat, origenLon, destinoLat, destinoLon);
    }
}
