package com.aptech.mymusic.domain.repository;


import com.aptech.mymusic.domain.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
