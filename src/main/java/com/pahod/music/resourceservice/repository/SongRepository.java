package com.pahod.music.resourceservice.repository;

import com.pahod.music.resourceservice.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Integer> {}
