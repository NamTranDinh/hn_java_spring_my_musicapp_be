package com.aptech.mymusic.domain.entities;

import com.aptech.mymusic.presentation.internalmodel.FirebasePath;

import javax.persistence.*;

@Entity
@Table(name = "ma_topic")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return FirebasePath.TOPIC.getUrl(image);
    }

}
