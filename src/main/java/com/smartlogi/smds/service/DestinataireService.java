package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.DestinataireDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DestinataireService {
    DestinataireDTO save(DestinataireDTO destinataire);
    List<DestinataireDTO> findAll();
    Optional<DestinataireDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 47 on 2025-10-29 02:06:56
// Commit 14 on 2025-10-29 04:19:24
