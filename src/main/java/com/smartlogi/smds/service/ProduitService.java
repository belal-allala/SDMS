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
// Commit 99 on 2025-10-27 04:00:06
// Commit 40 on 2025-10-26 04:08:13
// Commit 96 on 2025-10-26 04:06:10
// Commit 99 on 2025-10-26 03:58:16
// Commit 107 on 2025-10-26 22:27:39
