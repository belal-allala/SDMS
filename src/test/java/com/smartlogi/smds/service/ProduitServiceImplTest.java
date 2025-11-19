package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ProduitDTO;
import com.smartlogi.smds.entity.Produit;
import com.smartlogi.smds.mapper.ProduitMapper;
import com.smartlogi.smds.repository.ProduitRepository;
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
class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private ProduitMapper produitMapper;

    @InjectMocks
    private ProduitServiceImpl produitService;

    private Produit produit;
    private ProduitDTO produitDTO;
    private UUID produitId;

    @BeforeEach
    void setUp() {
        produitId = UUID.randomUUID();
        produit = new Produit();
        produit.setId(produitId);
        produit.setNom("Test Produit");

        produitDTO = new ProduitDTO();
        produitDTO.setId(produitId.toString());
        produitDTO.setNom("Test Produit");
    }

    @Test
    void save() {
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        ProduitDTO savedDto = produitService.save(new ProduitDTO());

        assertNotNull(savedDto);
        assertEquals(produitId.toString(), savedDto.getId());
        verify(produitRepository, times(1)).save(any(Produit.class));
    }

    @Test
    void findAll() {
        when(produitRepository.findAll()).thenReturn(Collections.singletonList(produit));

        List<ProduitDTO> result = produitService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(produitDTO.getNom(), result.get(0).getNom());
        verify(produitRepository, times(1)).findAll();
    }

    @Test
    void findById_whenProduitExists() {
        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));

        Optional<ProduitDTO> result = produitService.findById(produitId);

        assertTrue(result.isPresent());
        assertEquals(produitDTO.getId(), result.get().getId());
        verify(produitRepository, times(1)).findById(produitId);
    }

    @Test
    void findById_whenProduitDoesNotExist() {
        when(produitRepository.findById(produitId)).thenReturn(Optional.empty());

        Optional<ProduitDTO> result = produitService.findById(produitId);

        assertFalse(result.isPresent());
        verify(produitRepository, times(1)).findById(produitId);
    }

    @Test
    void deleteById() {
        doNothing().when(produitRepository).deleteById(produitId);

        produitService.deleteById(produitId);

        verify(produitRepository, times(1)).deleteById(produitId);
    }
}
