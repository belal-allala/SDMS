package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    Produit save(Produit produit);
    List<Produit> findAll();
    Optional<Produit> findById(Long id);
    void deleteById(Long id);
}
