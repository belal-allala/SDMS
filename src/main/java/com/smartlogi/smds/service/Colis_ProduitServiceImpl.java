package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.Colis_ProduitDTO;
import com.smartlogi.smds.entity.Colis;
import com.smartlogi.smds.entity.Colis_Produit;
import com.smartlogi.smds.entity.Produit;
import com.smartlogi.smds.mapper.Colis_ProduitMapper;
import com.smartlogi.smds.repository.ColisRepository;
import com.smartlogi.smds.repository.Colis_ProduitRepository;
import com.smartlogi.smds.repository.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class Colis_ProduitServiceImpl implements Colis_ProduitService {

    private final Colis_ProduitRepository colisProduitRepository;
    private final ColisRepository colisRepository;
    private final ProduitRepository produitRepository;
    private final Colis_ProduitMapper colisProduitMapper = Colis_ProduitMapper.INSTANCE;

    public Colis_ProduitServiceImpl(Colis_ProduitRepository colisProduitRepository, ColisRepository colisRepository, ProduitRepository produitRepository) {
        this.colisProduitRepository = colisProduitRepository;
        this.colisRepository = colisRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public Colis_ProduitDTO save(Colis_ProduitDTO colisProduitDTO) {
        Colis_Produit colisProduit = colisProduitMapper.toEntity(colisProduitDTO);

        if (colisProduitDTO.getColisId() != null) {
            Colis colis = colisRepository.findById(UUID.fromString(colisProduitDTO.getColisId()))
                    .orElseThrow(() -> new RuntimeException("Colis non trouvé"));
            colisProduit.setColis(colis);
        }

        if (colisProduitDTO.getProduitId() != null) {
            Produit produit = produitRepository.findById(UUID.fromString(colisProduitDTO.getProduitId()))
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            colisProduit.setProduit(produit);
        }

        colisProduit = colisProduitRepository.save(colisProduit);
        return colisProduitMapper.toDTO(colisProduit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Colis_ProduitDTO> findAll() {
        return colisProduitRepository.findAll()
                .stream()
                .map(colisProduitMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Colis_ProduitDTO> findById(UUID id) {
        return colisProduitRepository.findById(id).map(colisProduitMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        colisProduitRepository.deleteById(id);
    }
}
// Commit 20 on 2025-10-28 10:59:01
// Commit 68 on 2025-10-30 12:38:59
