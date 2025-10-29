package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ClientExpediteurDTO;
import com.smartlogi.smds.mapper.ClientExpediteurMapper;
import com.smartlogi.smds.entity.ClientExpediteur;
import com.smartlogi.smds.repository.ClientExpediteurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientExpediteurServiceImpl implements  ClientExpediteurService {
    private final ClientExpediteurRepository clientExpediteurRepository;
    private final ClientExpediteurMapper clientExpediteurMapper = ClientExpediteurMapper.INSTANCE;


    public ClientExpediteurServiceImpl(ClientExpediteurRepository clientExpediteurRepository) {
        this.clientExpediteurRepository = clientExpediteurRepository;
    }

    @Override
    public ClientExpediteurDTO save(ClientExpediteurDTO clientExpediteurDTO) {
        ClientExpediteur clientExpediteur = clientExpediteurMapper.toEntity(clientExpediteurDTO);
        clientExpediteur =  clientExpediteurRepository.save(clientExpediteur);
        return clientExpediteurMapper.toDto(clientExpediteur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientExpediteurDTO> findAll() {

        return clientExpediteurRepository.findAll()
                .stream()
                .map(clientExpediteurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientExpediteurDTO> findById(UUID id) {
        return clientExpediteurRepository.findById(id)
                .map(clientExpediteurMapper::toDto);
    }

    @Override
    public void deleteById(UUID id) {
        clientExpediteurRepository.deleteById(id);
    }

}
// Commit 37 on 2025-10-27 03:11:15
// Commit 46 on 2025-10-29 16:05:47
