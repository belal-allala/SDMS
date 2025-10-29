package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Zone;
import com.smartlogi.smds.repository.ZoneRepository;
import com.smartlogi.smds.dto.ZoneDTO;
import com.smartlogi.smds.mapper.ZoneMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper = ZoneMapper.INSTANCE;


    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public ZoneDTO save(ZoneDTO zoneDTO) {
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone = zoneRepository.save(zone);
        return zoneMapper.toDTO(zone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ZoneDTO> findAll() {

        return zoneRepository.findAll()
                .stream()
                .map(zoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ZoneDTO> findById(UUID id) {
        return zoneRepository.findById(id)
                .map(zoneMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        zoneRepository.deleteById(id);
    }
}
// Commit 13 on 2025-10-29 05:39:50
