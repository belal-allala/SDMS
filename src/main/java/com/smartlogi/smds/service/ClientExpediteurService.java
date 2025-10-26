package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ClientExpediteurDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientExpediteurService {

    ClientExpediteurDTO save(ClientExpediteurDTO clientExpediteurDto);
    List<ClientExpediteurDTO> findAll();
    Optional<ClientExpediteurDTO> findById(UUID id);
    void deleteById(UUID id);
}
// Commit 66 on 2025-10-29 22:42:49
// Commit 18 on 2025-10-27 00:48:19
