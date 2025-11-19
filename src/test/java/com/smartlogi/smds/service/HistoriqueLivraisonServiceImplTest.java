package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.HistoriqueLivraisonDTO;
import com.smartlogi.smds.entity.Colis;
import com.smartlogi.smds.entity.HistoriqueLivraison;
import com.smartlogi.smds.entity.StatutColis;
import com.smartlogi.smds.mapper.HistoriqueLivraisonMapper;
import com.smartlogi.smds.repository.HistoriqueLivraisonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriqueLivraisonServiceImplTest {

    @Mock
    private HistoriqueLivraisonRepository historiqueLivraisonRepository;

    @Mock
    private HistoriqueLivraisonMapper historiqueLivraisonMapper;

    @InjectMocks
    private HistoriqueLivraisonServiceImpl historiqueLivraisonService;

    private HistoriqueLivraison historique;
    private HistoriqueLivraisonDTO historiqueDTO;
    private UUID historiqueId;
    private Colis colis;

    @BeforeEach
    void setUp() {
        historiqueId = UUID.randomUUID();
        UUID colisId = UUID.randomUUID();

        colis = new Colis();
        colis.setId(colisId);

        historique = new HistoriqueLivraison();
        historique.setId(historiqueId);
        historique.setColis(colis);
        historique.setStatut(StatutColis.CREE);
        historique.setDateChangement(LocalDateTime.now());

        historiqueDTO = new HistoriqueLivraisonDTO();
        historiqueDTO.setId(historiqueId.toString());
        historiqueDTO.setColisId(colisId.toString());
        historiqueDTO.setStatut(StatutColis.CREE);
    }

    @Test
    void save() {
        when(historiqueLivraisonRepository.save(any(HistoriqueLivraison.class))).thenReturn(historique);

        HistoriqueLivraisonDTO savedDto = historiqueLivraisonService.save(new HistoriqueLivraisonDTO());

        assertNotNull(savedDto);
        assertEquals(historiqueId.toString(), savedDto.getId());
        verify(historiqueLivraisonRepository, times(1)).save(any(HistoriqueLivraison.class));
    }

    @Test
    void findAll() {
        when(historiqueLivraisonRepository.findAll()).thenReturn(Collections.singletonList(historique));

        List<HistoriqueLivraisonDTO> result = historiqueLivraisonService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(historiqueDTO.getId(), result.get(0).getId());
        verify(historiqueLivraisonRepository, times(1)).findAll();
    }

    @Test
    void findById_whenHistoriqueExists() {
        when(historiqueLivraisonRepository.findById(historiqueId)).thenReturn(Optional.of(historique));

        Optional<HistoriqueLivraisonDTO> result = historiqueLivraisonService.findById(historiqueId);

        assertTrue(result.isPresent());
        assertEquals(historiqueDTO.getId(), result.get().getId());
        verify(historiqueLivraisonRepository, times(1)).findById(historiqueId);
    }

    @Test
    void findById_whenHistoriqueDoesNotExist() {
        when(historiqueLivraisonRepository.findById(historiqueId)).thenReturn(Optional.empty());

        Optional<HistoriqueLivraisonDTO> result = historiqueLivraisonService.findById(historiqueId);

        assertFalse(result.isPresent());
        verify(historiqueLivraisonRepository, times(1)).findById(historiqueId);
    }

    @Test
    void deleteById() {
        doNothing().when(historiqueLivraisonRepository).deleteById(historiqueId);

        historiqueLivraisonService.deleteById(historiqueId);

        verify(historiqueLivraisonRepository, times(1)).deleteById(historiqueId);
    }
}
