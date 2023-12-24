package com.pahod.music.songservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetadataDTO {
    String name;
    String artist;
    String album;
    String length;
    Integer resourceId;
    String year;
}
