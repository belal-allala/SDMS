package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Produit;
import com.smartlogi.smds.repository.ProduitRepository;
import com.smartlogi.smds.dto.ProduitDTO;
import com.smartlogi.smds.mapper.ProduitMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper = ProduitMapper.INSTANCE;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitDTO save(ProduitDTO produitDTO ) {
        Produit produit = produitMapper.toEntity(produitDTO);
        produit = produitRepository.save(produit);
        return produitMapper.toDTO(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDTO> findAll() {

        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProduitDTO> findById(UUID id) {

        return produitRepository.findById(id)
                .map(produitMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        produitRepository.deleteById(id);
    }
}
// Commit 68 on 2025-10-26 06:03:03
