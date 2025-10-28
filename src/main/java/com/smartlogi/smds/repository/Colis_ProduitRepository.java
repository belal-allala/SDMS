package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Colis_Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Colis_ProduitRepository extends JpaRepository<Colis_Produit, UUID> {
}
// Commit 9 on 2025-10-28 02:08:38
