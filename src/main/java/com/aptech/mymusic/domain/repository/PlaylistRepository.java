package com.aptech.mymusic.domain.repository;


import com.aptech.mymusic.domain.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
