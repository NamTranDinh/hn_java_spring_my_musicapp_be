package com.aptech.mymusic.domain.entities;

import com.aptech.mymusic.utils.ResourceUtils;

import javax.persistence.*;

@Entity
@Table(name = "ma_song")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "album_ids")
    private String albumIds;
    @Column(name = "category_ids")
    private String categoryIds;
    @Column(name = "playlist_ids")
    private String playlistIds;
    @Column(name = "name")
    private String name;
    @Column(name = "img")
    private String image;
    @Column(name = "singer_name")
    private String singerName;
    @Column(name = "audio")
    private String audio;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(String albumIds) {
        this.albumIds = albumIds;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getPlaylistIds() {
        return playlistIds;
    }

    public void setPlaylistIds(String playlistIds) {
        this.playlistIds = playlistIds;
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

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return ResourceUtils.Path.SONGS.getPath(image);
    }

    public String getAudioUrl() {
        return ResourceUtils.Path.AUDIO.getPath(audio);
    }

}
