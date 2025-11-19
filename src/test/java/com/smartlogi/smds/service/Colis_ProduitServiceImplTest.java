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

}
