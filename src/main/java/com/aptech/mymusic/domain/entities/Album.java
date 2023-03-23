package com.aptech.mymusic.domain.entities;

import com.aptech.mymusic.presentation.internalmodel.Resource;

import javax.persistence.*;

@Entity
@Table(name = "ma_album")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "singer_name")
    private String singerName;
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

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return Resource.getUrl(Resource.Path.ALBUMS, image);
    }
}
