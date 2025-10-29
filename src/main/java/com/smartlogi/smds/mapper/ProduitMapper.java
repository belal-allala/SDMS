package com.smartlogi.smds.mapper;

import com.smartlogi.smds.dto.ProduitDTO;
import com.smartlogi.smds.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
    ProduitMapper INSTANCE = Mappers.getMapper(ProduitMapper.class);
    Produit toEntity(ProduitDTO produitDTO);
    ProduitDTO toDTO(Produit produit);
}
// Commit 14 on 2025-10-29 06:34:35
