package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Produit;
import com.smartlogi.smds.repository.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        produitRepository.deleteById(id);
    }
}
