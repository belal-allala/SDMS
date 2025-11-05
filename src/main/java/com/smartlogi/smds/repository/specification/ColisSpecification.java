package com.smartlogi.smds.repository.specification;

import com.smartlogi.smds.entity.Colis;
import com.smartlogi.smds.entity.StatutColis;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class ColisSpecification {

    public static Specification<Colis> hasStatut(StatutColis statut) {
        return (root, query, criteriaBuilder) -> {
            if (statut == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("statutColis"), statut);
        };
    }

    public static Specification<Colis> hasLivreur(UUID livreurId) {
        return (root, query, criteriaBuilder) -> {
            if (livreurId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("livreur").get("id"), livreurId);
        };
    }

    public static Specification<Colis> hasZone(UUID zoneId) {
        return (root, query, criteriaBuilder) -> {
            if (zoneId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("zone").get("id"), zoneId);
        };
    }

    public static Specification<Colis> createdAfter(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dateCreation"), date);
        };
    }

    public static Specification<Colis> createdBefore(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("dateCreation"), date);
        };
    }
}
