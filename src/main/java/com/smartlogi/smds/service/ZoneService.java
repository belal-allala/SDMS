package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ZoneDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ZoneService {
    ZoneDTO save(ZoneDTO zone);
    List<ZoneDTO> findAll();
    Optional<ZoneDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 18 on 2025-10-30 03:28:49
// Commit 49 on 2025-10-26 10:38:24
