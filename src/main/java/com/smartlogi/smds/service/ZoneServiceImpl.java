package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Zone;
import com.smartlogi.smds.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Zone> findById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        zoneRepository.deleteById(id);
    }
}
