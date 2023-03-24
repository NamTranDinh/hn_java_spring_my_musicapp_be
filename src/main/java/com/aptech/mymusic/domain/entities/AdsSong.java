package com.aptech.mymusic.domain.entities;

import com.aptech.mymusic.presentation.internalmodel.Resource;

import javax.persistence.*;

@Entity
@Table(name = "ma_ads")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AdsSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(targetEntity = Song.class)
    @JoinColumn(name = "song_id", insertable = false, updatable = false)
    private Song song;
    @Column(name = "content")
    private String content;
    @Column(name = "img")
    private String image;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Enums.Status status;

    public AdsSong() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Enums.Status getStatus() {
        return status;
    }

    public void setStatus(Enums.Status status) {
        this.status = status;
    }

    public String getImageUrl() {
        return Resource.getUrl(Resource.Path.ADS, image);
    }
}
