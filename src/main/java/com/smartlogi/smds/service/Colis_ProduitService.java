package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.Colis_ProduitDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Colis_ProduitService {
    Colis_ProduitDTO save(Colis_ProduitDTO colisProduitDTO);
    List<Colis_ProduitDTO> findAll();
    Optional<Colis_ProduitDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 12 on 2025-10-26 10:15:29
// Commit 71 on 2025-10-28 21:34:43
// Commit 97 on 2025-10-29 20:43:20
// Commit 112 on 2025-10-30 21:58:30
