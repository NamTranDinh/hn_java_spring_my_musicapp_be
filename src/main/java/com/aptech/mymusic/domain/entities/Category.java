package com.aptech.mymusic.domain.entities;

import com.aptech.mymusic.utils.ResourceUtils;

import javax.persistence.*;

@Entity
@Table(name = "ma_category")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "topic_ids")
    private String topicIds;
    @Column(name = "name")
    private String name;
    @Column(name = "img")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(String topicIds) {
        this.topicIds = topicIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return ResourceUtils.Path.ALBUMS.getPath(image);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return ResourceUtils.Path.ALBUMS.getPath(image);
    }
}
