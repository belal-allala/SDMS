package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColisRepository extends JpaRepository<Colis, UUID> {
}
// Commit 39 on 2025-10-30 17:40:45
