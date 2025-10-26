package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {
}
// Commit 107 on 2025-10-26 15:03:31
