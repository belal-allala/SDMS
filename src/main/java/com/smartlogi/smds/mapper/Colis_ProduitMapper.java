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
    Colis_Produit toEntity(Colis_ProduitDTO colis_produitDTO);
}
// Commit 16 on 2025-10-28 13:50:17
// Commit 73 on 2025-10-28 19:47:01
// Commit 74 on 2025-10-28 20:02:00
// Commit 79 on 2025-10-29 09:01:49
// Commit 87 on 2025-10-28 00:59:03
// Commit 38 on 2025-10-26 03:37:02
// Commit 61 on 2025-10-30 23:40:49
