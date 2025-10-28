package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ProduitDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProduitService {
    ProduitDTO save(ProduitDTO produit);
    List<ProduitDTO> findAll();
    Optional<ProduitDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 51 on 2025-10-28 22:47:12
