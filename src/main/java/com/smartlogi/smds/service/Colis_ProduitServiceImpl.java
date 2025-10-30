package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Colis_Produit;
import com.smartlogi.smds.repository.Colis_ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Colis_ProduitServiceImpl implements Colis_ProduitService {

    private final Colis_ProduitRepository colisProduitRepository;

    public Colis_ProduitServiceImpl(Colis_ProduitRepository colisProduitRepository) {
        this.colisProduitRepository = colisProduitRepository;
    }

    @Override
    public Colis_Produit save(Colis_Produit colisProduit) {
        return colisProduitRepository.save(colisProduit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Colis_Produit> findAll() {
        return colisProduitRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Colis_Produit> findById(Long id) {
        return colisProduitRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        colisProduitRepository.deleteById(id);
    }
}
