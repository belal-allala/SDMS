package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.HistoriqueLivraison;

import java.util.List;
import java.util.Optional;

public interface HistoriqueLivraisonService {
    HistoriqueLivraison save(HistoriqueLivraison historiqueLivraison);
    List<HistoriqueLivraison> findAll();
    Optional<HistoriqueLivraison> findById(Long id);
    void deleteById(Long id);
}
