package com.pahod.music.songservice.repository;

import com.pahod.music.songservice.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Integer> {
}
