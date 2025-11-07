package com.smartlogi.smds.mapper;

import com.smartlogi.smds.entity.Colis_Produit;
import com.smartlogi.smds.dto.Colis_ProduitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface Colis_ProduitMapper {
    Colis_ProduitMapper INSTANCE = Mappers.getMapper(Colis_ProduitMapper.class);

    @Mapping(source = "colis.id", target = "colisId")
    @Mapping(source = "produit.id", target = "produitId")
    Colis_ProduitDTO toDTO(Colis_Produit colis_produit);

    @Mapping(source = "colisId", target = "colis.id")
    @Mapping(source = "produitId", target = "produit.id")
    @Mapping(target = "prix", ignore = true) // Ignorer le prix lors de la conversion DTO -> Entité
    @Mapping(target = "dateAjout", ignore = true) // Ignorer la date lors de la conversion DTO -> Entité
    Colis_Produit toEntity(Colis_ProduitDTO colis_produitDTO);
}
