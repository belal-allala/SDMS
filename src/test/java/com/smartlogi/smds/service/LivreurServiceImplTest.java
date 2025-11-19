package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.LivreurDTO;
import com.smartlogi.smds.entity.Livreur;
import com.smartlogi.smds.entity.Zone;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.mapper.LivreurMapper;
import com.smartlogi.smds.repository.LivreurRepository;
import com.smartlogi.smds.repository.ZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivreurServiceImplTest {

    @Mock
    private LivreurRepository livreurRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private LivreurMapper livreurMapper;

    @InjectMocks
    private LivreurServiceImpl livreurService;

    private Livreur livreur;
    private LivreurDTO livreurDTO;
    private UUID livreurId;
    private UUID zoneId;
    private Zone zone;

    @BeforeEach
    void setUp() {
        livreurId = UUID.randomUUID();
        zoneId = UUID.randomUUID();

        zone = new Zone();
        zone.setId(zoneId);
        zone.setNom("Test Zone");

        livreur = new Livreur();
        livreur.setId(livreurId);
        livreur.setNom("Test Livreur");
        livreur.setZone(zone);

        livreurDTO = new LivreurDTO();
        livreurDTO.setId(livreurId.toString());
        livreurDTO.setNom("Test Livreur");
        livreurDTO.setZoneId(zoneId.toString());
    }

    @Test
    void save() {
        when(zoneRepository.findById(zoneId)).thenReturn(Optional.of(zone));
        when(livreurRepository.save(any(Livreur.class))).thenReturn(livreur);

        LivreurDTO savedDto = livreurService.save(livreurDTO);

        assertNotNull(savedDto);
        assertEquals(livreurId.toString(), savedDto.getId());
        verify(livreurRepository, times(1)).save(any(Livreur.class));
        verify(zoneRepository, times(1)).findById(zoneId);
    }

    @Test
    void save_throwsExceptionWhenZoneNotFound() {
        when(livreurMapper.toEntity(any(LivreurDTO.class))).thenReturn(livreur);
        when(zoneRepository.findById(zoneId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            livreurService.save(livreurDTO);
        });

        assertTrue(thrown.getMessage().contains("Zone non trouvée"));
        verify(zoneRepository, times(1)).findById(zoneId);
        verify(livreurRepository, never()).save(any(Livreur.class));
    }

    @Test
    void findAll() {
        when(livreurRepository.findAll()).thenReturn(Collections.singletonList(livreur));

        List<LivreurDTO> result = livreurService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(livreurDTO.getNom(), result.get(0).getNom());
        verify(livreurRepository, times(1)).findAll();
    }

    @Test
    void findById_whenLivreurExists() {
        when(livreurRepository.findById(livreurId)).thenReturn(Optional.of(livreur));

        Optional<LivreurDTO> result = livreurService.findById(livreurId);

        assertTrue(result.isPresent());
        assertEquals(livreurDTO.getId(), result.get().getId());
        verify(livreurRepository, times(1)).findById(livreurId);
    }

    @Test
    void findById_whenLivreurDoesNotExist() {
        when(livreurRepository.findById(livreurId)).thenReturn(Optional.empty());

        Optional<LivreurDTO> result = livreurService.findById(livreurId);

        assertFalse(result.isPresent());
        verify(livreurRepository, times(1)).findById(livreurId);
    }

}
