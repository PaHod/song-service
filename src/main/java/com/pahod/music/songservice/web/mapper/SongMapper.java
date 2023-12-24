package com.pahod.music.songservice.web.mapper;

import com.pahod.music.songservice.entity.SongEntity;
import com.pahod.music.songservice.web.dto.MetadataDTO;
import com.pahod.music.songservice.web.dto.SongDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDTO modelToDTO(SongEntity model);

    SongEntity metadataToModel(MetadataDTO dto);

}
