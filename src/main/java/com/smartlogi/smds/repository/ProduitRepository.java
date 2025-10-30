package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,UUID>{
}
// Commit 69 on 2025-10-30 07:59:39
