package com.smartlogi.smds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartlogi.smds.entity.Destinataire;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinataireRepository extends JpaRepository<Destinataire, UUID> {
}

