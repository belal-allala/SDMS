package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ColisDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColisService {
    ColisDTO save(ColisDTO colisDTO);
    List<ColisDTO> findAll();
    Optional<ColisDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 19 on 2025-10-28 19:47:47
// Commit 102 on 2025-10-29 23:52:32
