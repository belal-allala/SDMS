package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.HistoriqueLivraison;
import com.smartlogi.smds.repository.HistoriqueLivraisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoriqueLivraisonServiceImpl implements HistoriqueLivraisonService {

    private final HistoriqueLivraisonRepository historiqueLivraisonRepository;

    public HistoriqueLivraisonServiceImpl(HistoriqueLivraisonRepository historiqueLivraisonRepository) {
        this.historiqueLivraisonRepository = historiqueLivraisonRepository;
    }

    @Override
    public HistoriqueLivraison save(HistoriqueLivraison historiqueLivraison) {
        return historiqueLivraisonRepository.save(historiqueLivraison);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoriqueLivraison> findAll() {
        return historiqueLivraisonRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistoriqueLivraison> findById(Long id) {
        return historiqueLivraisonRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        historiqueLivraisonRepository.deleteById(id);
    }
}
