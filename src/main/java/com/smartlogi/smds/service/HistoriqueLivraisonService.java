package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.HistoriqueLivraisonDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoriqueLivraisonService {
    HistoriqueLivraisonDTO save(HistoriqueLivraisonDTO historiqueLivraisonDTO);
    List<HistoriqueLivraisonDTO> findAll();
    Optional<HistoriqueLivraisonDTO> findById(UUID id);
    void deleteById(UUID id);
}
