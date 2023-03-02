package com.aptech.mymusic.domain.repository;


import com.aptech.mymusic.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE FUNCTION('JSON_CONTAINS', c.topicIds, :topicId) = 1")
    List<Category> getAllCategoryInTopic(String topicId);
}
