package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.HistoriqueLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoriqueLivraisonRepository extends JpaRepository<HistoriqueLivraison, UUID> {
}
// Commit 3 on 2025-10-26 01:07:04
// Commit 6 on 2025-10-27 10:42:59
// Commit 50 on 2025-10-26 14:24:39
// Commit 83 on 2025-10-28 21:56:48
