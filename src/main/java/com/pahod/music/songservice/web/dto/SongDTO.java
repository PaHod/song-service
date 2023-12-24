package com.pahod.music.songservice.web.dto;

import lombok.Data;

@Data
public class SongDTO {

    private String name;
    private String artist;
    private String album;
    private String length;
    private Integer resourceId;
    private String year;
}
