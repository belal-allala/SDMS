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

