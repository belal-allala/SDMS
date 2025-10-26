package com.smartlogi.smds.mapper;

import com.smartlogi.smds.entity.ClientExpediteur;
import com.smartlogi.smds.dto.ClientExpediteurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientExpediteurMapper {

    ClientExpediteurMapper INSTANCE = Mappers.getMapper(ClientExpediteurMapper.class);

    ClientExpediteurDTO toDto(ClientExpediteur clientExpediteur);

    ClientExpediteur toEntity(ClientExpediteurDTO clientExpediteurDTO);
}
// Commit 21 on 2025-10-28 00:16:56
// Commit 58 on 2025-10-27 21:53:30
// Commit 64 on 2025-10-28 19:23:31
// Commit 90 on 2025-10-26 16:35:00
