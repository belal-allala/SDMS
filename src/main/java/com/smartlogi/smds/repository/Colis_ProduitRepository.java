package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Colis_Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Colis_ProduitRepository extends JpaRepository<Colis_Produit, UUID> {
}
// Commit 9 on 2025-10-28 02:08:38
// Commit 10 on 2025-10-26 01:39:21
// Commit 95 on 2025-10-28 01:37:54
