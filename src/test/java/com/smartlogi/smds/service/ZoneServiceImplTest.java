package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ZoneDTO;
import com.smartlogi.smds.entity.Zone;
import com.smartlogi.smds.mapper.ZoneMapper;
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
class ZoneServiceImplTest {

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private ZoneMapper zoneMapper;

    @InjectMocks
    private ZoneServiceImpl zoneService;

    private Zone zone;
    private ZoneDTO zoneDTO;
    private UUID zoneId;

    @BeforeEach
    void setUp() {
        zoneId = UUID.randomUUID();
        zone = new Zone();
        zone.setId(zoneId);
        zone.setNom("Test Zone");

        zoneDTO = new ZoneDTO();
        zoneDTO.setId(zoneId.toString());
        zoneDTO.setNom("Test Zone");
    }

    @Test
    void save() {
        when(zoneMapper.toEntity(any(ZoneDTO.class))).thenReturn(zone);
        when(zoneRepository.save(any(Zone.class))).thenReturn(zone);
        when(zoneMapper.toDTO(any(Zone.class))).thenReturn(zoneDTO);

        ZoneDTO savedDto = zoneService.save(new ZoneDTO());

        assertNotNull(savedDto);
        assertEquals(zoneId.toString(), savedDto.getId());
        verify(zoneRepository, times(1)).save(zone);
    }

    @Test
    void findAll() {
        when(zoneRepository.findAll()).thenReturn(Collections.singletonList(zone));
        when(zoneMapper.toDTO(any(Zone.class))).thenReturn(zoneDTO);

        List<ZoneDTO> result = zoneService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(zoneDTO.getNom(), result.get(0).getNom());
        verify(zoneRepository, times(1)).findAll();
    }

    @Test
    void findById_whenZoneExists() {
        when(zoneRepository.findById(zoneId)).thenReturn(Optional.of(zone));
        when(zoneMapper.toDTO(any(Zone.class))).thenReturn(zoneDTO);

        Optional<ZoneDTO> result = zoneService.findById(zoneId);

        assertTrue(result.isPresent());
        assertEquals(zoneDTO.getId(), result.get().getId());
        verify(zoneRepository, times(1)).findById(zoneId);
    }

    @Test
    void findById_whenZoneDoesNotExist() {
        when(zoneRepository.findById(zoneId)).thenReturn(Optional.empty());

        Optional<ZoneDTO> result = zoneService.findById(zoneId);

        assertFalse(result.isPresent());
        verify(zoneRepository, times(1)).findById(zoneId);
    }

    @Test
    void deleteById() {
        doNothing().when(zoneRepository).deleteById(zoneId);

        zoneService.deleteById(zoneId);

        verify(zoneRepository, times(1)).deleteById(zoneId);
    }
}
