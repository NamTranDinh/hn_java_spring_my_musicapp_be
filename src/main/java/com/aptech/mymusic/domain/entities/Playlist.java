package com.aptech.mymusic.domain.entities;

import com.aptech.mymusic.presentation.internalmodel.Resource;

import javax.persistence.*;

@Entity
@Table(name = "ma_playlist")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "img_icon")
    private String imgIcon;
    @Column(name = "img_background")
    private String imgBackground;

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

    public String getImageIcon() {
        return imgIcon;
    }

    public void setImageIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

    public String getImageBackground() {
        return imgBackground;
    }

    public void setImageBackground(String imgBackground) {
        this.imgBackground = imgBackground;
    }

    public String getImageIconUrl() {
        return Resource.getUrl(Resource.Path.PLAYLISTS, imgIcon);
    }

    public String getImageBackgroundUrl() {
        return Resource.getUrl(Resource.Path.PLAYLISTS, imgBackground);
    }
}
