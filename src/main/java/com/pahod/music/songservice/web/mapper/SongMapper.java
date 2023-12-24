package com.pahod.music.songservice.web.mapper;

import com.pahod.music.songservice.entity.SongEntity;
import com.pahod.music.songservice.web.dto.MetadataDTO;
import com.pahod.music.songservice.web.dto.SongIdDTO;
import com.pahod.music.songservice.web.dto.SongResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongResponseDTO modelToDTO(SongEntity model);

    @Mapping(target = "id", ignore = true)
    SongEntity metadataToModel(MetadataDTO dto);

    SongIdDTO modelToSongIdDTO(SongEntity song);
}
