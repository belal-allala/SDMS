package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ColisDTO;
import com.smartlogi.smds.entity.*;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.mapper.ColisMapper;
import com.smartlogi.smds.repository.*;
import com.smartlogi.smds.repository.specification.ColisSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ColisServiceImpl implements ColisService {
    private final ColisRepository colisRepository;
    private final ClientExpediteurRepository clientExpediteurRepository;
    private final DestinataireRepository destinataireRepository;
    private final LivreurRepository livreurRepository;
    private final ZoneRepository zoneRepository;
    private final HistoriqueLivraisonRepository historiqueLivraisonRepository;
    private final ColisMapper colisMapper = ColisMapper.INSTANCE;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    public ColisServiceImpl(ColisRepository colisRepository, ClientExpediteurRepository clientExpediteurRepository, DestinataireRepository destinataireRepository, LivreurRepository livreurRepository, ZoneRepository zoneRepository, HistoriqueLivraisonRepository historiqueLivraisonRepository, EmailService emailService, TemplateEngine templateEngine) {
        this.colisRepository = colisRepository;
        this.clientExpediteurRepository = clientExpediteurRepository;
        this.destinataireRepository = destinataireRepository;
        this.livreurRepository = livreurRepository;
        this.zoneRepository = zoneRepository;
        this.historiqueLivraisonRepository = historiqueLivraisonRepository;
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    @Override
    public ColisDTO save(ColisDTO colisDTO) {
        Colis colis = colisMapper.toEntity(colisDTO);

        if (colis.getId() == null) { // C'est une nouvelle entité
            colis.setDateCreation(LocalDate.now());
        }

        if (colisDTO.getClientExpediteurId() != null) {
            ClientExpediteur clientExpediteur = clientExpediteurRepository.findById(UUID.fromString(colisDTO.getClientExpediteurId()))
                    .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur non trouvé"));
            colis.setClientExpediteur(clientExpediteur);
        }

        if (colisDTO.getDestinataireId() != null) {
            Destinataire destinataire = destinataireRepository.findById(UUID.fromString(colisDTO.getDestinataireId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Destinataire non trouvé"));
            colis.setDestinataire(destinataire);
        }

        if (colisDTO.getLivreurId() != null) {
            Livreur livreur = livreurRepository.findById(UUID.fromString(colisDTO.getLivreurId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Livreur non trouvé"));
            colis.setLivreur(livreur);
        }

        if (colisDTO.getZoneId() != null) {
            Zone zone = zoneRepository.findById(UUID.fromString(colisDTO.getZoneId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Zone non trouvée"));
            colis.setZone(zone);
        }

        colis = colisRepository.save(colis);
        return colisMapper.toDTO(colis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColisDTO> findAll() {
        return colisRepository.findAll()
                .stream()
                .map(colisMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ColisDTO> findById(UUID id) {
        return colisRepository.findById(id).map(colisMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        colisRepository.deleteById(id);
    }

    @Override
    public ColisDTO updateStatus(UUID id, StatutColis newStatus) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));

        colis.setStatutColis(newStatus);

        HistoriqueLivraison historique = new HistoriqueLivraison();
        historique.setColis(colis);
        historique.setStatut(newStatus);
        historique.setDateChangement(LocalDateTime.now());
        historique.setCommentaire("Statut du colis mis à jour à " + newStatus);
        historiqueLivraisonRepository.save(historique);

        // Send email notification using Thymeleaf template
        if (colis.getDestinataire() != null && colis.getDestinataire().getEmail() != null) {
            String to = colis.getDestinataire().getEmail();
            String subject = "Mise à jour du statut de votre colis: " + colis.getId();

            Context context = new Context();
            context.setVariable("recipientName", colis.getDestinataire().getPrenom());
            context.setVariable("trackingNumber", colis.getId().toString());
            context.setVariable("newStatus", newStatus.toString());

            String htmlBody = templateEngine.process("email/status-update-template", context);
            emailService.sendHtmlMessage(to, subject, htmlBody);
        }

        Colis updatedColis = colisRepository.save(colis);
        return colisMapper.toDTO(updatedColis);
    }

    @Override
    public ColisDTO assignerLivreur(UUID colisId, UUID livreurId) {
        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + colisId));

        Livreur livreur = livreurRepository.findById(livreurId)
                .orElseThrow(() -> new ResourceNotFoundException("Livreur not found with id: " + livreurId));

        colis.setLivreur(livreur);

        Colis updatedColis = colisRepository.save(colis);
        return colisMapper.toDTO(updatedColis);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColisDTO> searchColis(StatutColis statut, UUID livreurId, UUID zoneId, LocalDate dateDebut, LocalDate dateFin, Pageable pageable) {
        Specification<Colis> spec = Specification.where(ColisSpecification.hasStatut(statut))
                .and(ColisSpecification.hasLivreur(livreurId))
                .and(ColisSpecification.hasZone(zoneId))
                .and(ColisSpecification.createdAfter(dateDebut))
                .and(ColisSpecification.createdBefore(dateFin));

        Page<Colis> colisPage = colisRepository.findAll(spec, pageable);
        return colisPage.map(colisMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColisDTO> findByClientExpediteurId(UUID clientExpediteurId, Pageable pageable) {
        Page<Colis> colisPage = colisRepository.findByClientExpediteurId(clientExpediteurId, pageable);
        return colisPage.map(colisMapper::toDTO);
    }
}
