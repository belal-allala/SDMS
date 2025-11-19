package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.DestinataireDTO;
import com.smartlogi.smds.entity.Destinataire;
import com.smartlogi.smds.mapper.DestinataireMapper;
import com.smartlogi.smds.repository.DestinataireRepository;
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
class DestinataireServiceImplTest {

    @Mock
    private DestinataireRepository destinataireRepository;

    @Mock
    private DestinataireMapper destinataireMapper;

    @InjectMocks
    private DestinataireServiceImpl destinataireService;

    private Destinataire destinataire;
    private DestinataireDTO destinataireDTO;
    private UUID destinataireId;

    @BeforeEach
    void setUp() {
        destinataireId = UUID.randomUUID();
        destinataire = new Destinataire();
        destinataire.setId(destinataireId);
        destinataire.setNom("Test Nom");

        destinataireDTO = new DestinataireDTO();
        destinataireDTO.setId(destinataireId.toString());
        destinataireDTO.setNom("Test Nom");
    }

    @Test
    void save() {
        when(destinataireRepository.save(any(Destinataire.class))).thenReturn(destinataire);

        DestinataireDTO savedDto = destinataireService.save(new DestinataireDTO());

        assertNotNull(savedDto);
        assertEquals(destinataireId.toString(), savedDto.getId());
        verify(destinataireRepository, times(1)).save(any(Destinataire.class));
    }

    @Test
    void findAll() {
        when(destinataireRepository.findAll()).thenReturn(Collections.singletonList(destinataire));

        List<DestinataireDTO> result = destinataireService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(destinataireDTO.getNom(), result.get(0).getNom());
        verify(destinataireRepository, times(1)).findAll();
    }

    @Test
    void findById_whenDestinataireExists() {
        when(destinataireRepository.findById(destinataireId)).thenReturn(Optional.of(destinataire));
        when(destinataireMapper.toDTO(any(Destinataire.class))).thenReturn(destinataireDTO);

        Optional<DestinataireDTO> result = destinataireService.findById(destinataireId);

        assertTrue(result.isPresent());
        assertEquals(destinataireDTO.getId(), result.get().getId());
        verify(destinataireRepository, times(1)).findById(destinataireId);
    }

    @Test
    void findById_whenDestinataireDoesNotExist() {
        when(destinataireRepository.findById(destinataireId)).thenReturn(Optional.empty());

        Optional<DestinataireDTO> result = destinataireService.findById(destinataireId);

        assertFalse(result.isPresent());
        verify(destinataireRepository, times(1)).findById(destinataireId);
    }

    @Test
    void deleteById() {
        doNothing().when(destinataireRepository).deleteById(destinataireId);

        destinataireService.deleteById(destinataireId);

        verify(destinataireRepository, times(1)).deleteById(destinataireId);
    }
}
