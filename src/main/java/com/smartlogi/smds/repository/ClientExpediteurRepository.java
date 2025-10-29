package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.ClientExpediteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientExpediteurRepository extends JpaRepository<ClientExpediteur, UUID> {
}
// Commit 54 on 2025-10-29 19:19:37
