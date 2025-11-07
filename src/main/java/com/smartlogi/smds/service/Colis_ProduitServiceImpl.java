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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        // 1. Charger les entités parentes
        Colis colis = colisRepository.findById(UUID.fromString(colisProduitDTO.getColisId()))
                .orElseThrow(() -> new ResourceNotFoundException("Colis non trouvé avec l'ID: " + colisProduitDTO.getColisId()));

        Produit produit = produitRepository.findById(UUID.fromString(colisProduitDTO.getProduitId()))
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID: " + colisProduitDTO.getProduitId()));

        // 2. Créer l'entité Colis_Produit
        Colis_Produit colisProduit = new Colis_Produit();
        colisProduit.setColis(colis);
        colisProduit.setProduit(produit);
        colisProduit.setQuantite(colisProduitDTO.getQuantite());

        // 3. Logique métier : Calculer le prix et définir la date
        colisProduit.setPrix(produit.getPrix() * colisProduitDTO.getQuantite());
        colisProduit.setDateAjout(LocalDate.now());

        // 4. Sauvegarder et retourner le DTO
        Colis_Produit savedRelation = colisProduitRepository.save(colisProduit);
        return colisProduitMapper.toDTO(savedRelation);
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
