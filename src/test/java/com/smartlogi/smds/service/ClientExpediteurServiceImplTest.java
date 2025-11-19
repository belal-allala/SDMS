package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ClientExpediteurDTO;
import com.smartlogi.smds.entity.ClientExpediteur;
import com.smartlogi.smds.mapper.ClientExpediteurMapper;
import com.smartlogi.smds.repository.ClientExpediteurRepository;
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
class ClientExpediteurServiceImplTest {

    @Mock
    private ClientExpediteurRepository clientExpediteurRepository;

    @Mock
    private ClientExpediteurMapper clientExpediteurMapper;

    @InjectMocks
    private ClientExpediteurServiceImpl clientExpediteurService;

    private ClientExpediteur clientExpediteur;
    private ClientExpediteurDTO clientExpediteurDTO;
    private UUID clientId;

    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        clientExpediteur = new ClientExpediteur();
        clientExpediteur.setId(clientId);
        clientExpediteur.setNom("Test Nom");

        clientExpediteurDTO = new ClientExpediteurDTO();
        clientExpediteurDTO.setId(clientId.toString());
        clientExpediteurDTO.setNom("Test Nom");
    }

    @Test
    void save() {
        when(clientExpediteurRepository.save(any(ClientExpediteur.class))).thenReturn(clientExpediteur);

        ClientExpediteurDTO savedDto = clientExpediteurService.save(new ClientExpediteurDTO());

        assertNotNull(savedDto);
        assertEquals(clientId.toString(), savedDto.getId());
        verify(clientExpediteurRepository, times(1)).save(any(ClientExpediteur.class));
    }

    @Test
    void findAll() {
        when(clientExpediteurRepository.findAll()).thenReturn(Collections.singletonList(clientExpediteur));

        List<ClientExpediteurDTO> result = clientExpediteurService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(clientExpediteurDTO.getNom(), result.get(0).getNom());
        verify(clientExpediteurRepository, times(1)).findAll();
    }

    @Test
    void findById_whenClientExists() {
        when(clientExpediteurRepository.findById(clientId)).thenReturn(Optional.of(clientExpediteur));

        Optional<ClientExpediteurDTO> result = clientExpediteurService.findById(clientId);

        assertTrue(result.isPresent());
        assertEquals(clientExpediteurDTO.getId(), result.get().getId());
        verify(clientExpediteurRepository, times(1)).findById(clientId);
    }

    @Test
    void findById_whenClientDoesNotExist() {
        when(clientExpediteurRepository.findById(clientId)).thenReturn(Optional.empty());

        Optional<ClientExpediteurDTO> result = clientExpediteurService.findById(clientId);

        assertFalse(result.isPresent());
        verify(clientExpediteurRepository, times(1)).findById(clientId);
    }

}
