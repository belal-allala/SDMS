package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.LivreurDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivreurService {
    LivreurDTO save(LivreurDTO livreurDTO);
    List<LivreurDTO> findAll();
    Optional<LivreurDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 103 on 2025-10-27 21:08:29
// Commit 104 on 2025-10-27 06:19:38
