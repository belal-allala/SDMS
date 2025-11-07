package com.smartlogi.smds.repository;

import com.smartlogi.smds.entity.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColisRepository extends JpaRepository<Colis, UUID>, JpaSpecificationExecutor<Colis> {
    Page<Colis> findByClientExpediteurId(UUID clientExpediteurId, Pageable pageable);
}
