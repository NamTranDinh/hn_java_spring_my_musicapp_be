package com.aptech.mymusic.domain.repository;


import com.aptech.mymusic.domain.entities.AdsSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsSongRepository extends JpaRepository<AdsSong, Long> {
}
