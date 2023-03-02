package com.aptech.mymusic.domain.repository;


import com.aptech.mymusic.domain.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
