package com.smartlogi.smds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartlogi.smds.entity.Destinataire;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinataireRepository extends JpaRepository<Destinataire, UUID> {
}
// Commit 29 on 2025-10-28 07:45:26
// Commit 31 on 2025-10-30 03:13:01
