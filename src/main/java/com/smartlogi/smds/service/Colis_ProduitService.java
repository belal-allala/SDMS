package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Colis_Produit;

import java.util.List;
import java.util.Optional;

public interface Colis_ProduitService {
    Colis_Produit save(Colis_Produit colisProduit);
    List<Colis_Produit> findAll();
    Optional<Colis_Produit> findById(Long id);
    void deleteById(Long id);
}
