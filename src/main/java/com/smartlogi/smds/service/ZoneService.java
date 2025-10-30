package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Zone;

import java.util.List;
import java.util.Optional;

public interface ZoneService {
    Zone save(Zone zone);
    List<Zone> findAll();
    Optional<Zone> findById(Long id);
    void deleteById(Long id);
}
