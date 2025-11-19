package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.Colis_ProduitDTO;
import com.smartlogi.smds.entity.Colis;
import com.smartlogi.smds.entity.Colis_Produit;
import com.smartlogi.smds.entity.Produit;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.mapper.Colis_ProduitMapper;
import com.smartlogi.smds.repository.ColisRepository;
import com.smartlogi.smds.repository.Colis_ProduitRepository;
import com.smartlogi.smds.repository.ProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Colis_ProduitServiceImplTest {

    @Mock private Colis_ProduitRepository colisProduitRepository;

    @Mock private ColisRepository colisRepository;

    @Mock private ProduitRepository produitRepository;

    @Mock private Colis_ProduitMapper colisProduitMapper;

    @InjectMocks private Colis_ProduitServiceImpl colisProduitService;

    private Colis colis;
    private Produit produit;
    private Colis_ProduitDTO colisProduitDTO;
    private UUID colisId;
    private UUID produitId;

    @BeforeEach
    void setUp() {
        colisId = UUID.randomUUID();
        produitId = UUID.randomUUID();

        colis = new Colis();
        colis.setId(colisId);

        produit = new Produit();
        produit.setId(produitId);
        produit.setPrix(100.0);

        colisProduitDTO = new Colis_ProduitDTO();
        colisProduitDTO.setColisId(colisId.toString());
        colisProduitDTO.setProduitId(produitId.toString());
        colisProduitDTO.setQuantite(2);
    }

    @Test
    void save_shouldCalculatePriceAndSetDate() {
        when(colisRepository.findById(colisId)).thenReturn(Optional.of(colis));
        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));

        when(colisProduitRepository.save(any(Colis_Produit.class))).thenAnswer(invocation -> {
            Colis_Produit savedEntity = invocation.getArgument(0);
            assertNotNull(savedEntity.getDateAjout());
            assertEquals(LocalDate.now(), savedEntity.getDateAjout());
            assertEquals(200.0, savedEntity.getPrix());
            return savedEntity;
        });

        when(colisProduitMapper.toDTO(any(Colis_Produit.class))).thenAnswer(invocation -> {
            Colis_Produit savedEntity = invocation.getArgument(0);
            Colis_ProduitDTO resultDto = new Colis_ProduitDTO();
            resultDto.setPrix(savedEntity.getPrix());
            resultDto.setDateAjout(savedEntity.getDateAjout());
            return resultDto;
        });

        Colis_ProduitDTO result = colisProduitService.save(colisProduitDTO);
        assertNotNull(result);
        assertEquals(200.0, result.getPrix());
        assertEquals(LocalDate.now(), result.getDateAjout());

        verify(colisRepository, times(1)).findById(colisId);
        verify(produitRepository, times(1)).findById(produitId);
        verify(colisProduitRepository, times(1)).save(any(Colis_Produit.class));
    }

    @Test
    void save_throwsExceptionWhenColisNotFound() {
        when(colisRepository.findById(colisId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            colisProduitService.save(colisProduitDTO);
        });

        assertTrue(thrown.getMessage().contains("Colis non trouvé"));
        verify(produitRepository, never()).findById(any());
        verify(colisProduitRepository, never()).save(any());
    }

    @Test
    void save_throwsExceptionWhenProduitNotFound() {
        when(colisRepository.findById(colisId)).thenReturn(Optional.of(colis));
        when(produitRepository.findById(produitId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            colisProduitService.save(colisProduitDTO);
        });

        assertTrue(thrown.getMessage().contains("Produit non trouvé"));
        verify(colisProduitRepository, never()).save(any());
    }
}
