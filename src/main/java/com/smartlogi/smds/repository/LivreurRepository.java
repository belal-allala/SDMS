package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, UUID> {
}

