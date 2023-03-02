package com.aptech.mymusic.api;

import com.aptech.mymusic.data.ApiService;
import com.aptech.mymusic.domain.entities.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiMusicController {

    private final ApiService service;

    public ApiMusicController(ApiService service) {
        this.service = service;
    }

    @RequestMapping("/song_banner")
    public List<AdsSong> getDataBanner() {
        return service.getAdsSongRepository().findAll()
                .stream()
                .filter(adsSong -> adsSong.getSong().getStatus() == 0 && adsSong.getStatus() == 0)
                .collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////
    // RANDOM
    ///////////////////////////////////////////////////////////////////////////

    @RequestMapping("/rand_category_for_current_day")
    public List<Category> getRandCategory() {
        return service.getRandData(Category.class, 5);
    }

    @RequestMapping("/rand_album_for_current_day")
    public List<Album> getRandAlbum() {
        return service.getRandData(Album.class, 5);
    }

    @RequestMapping("/rand_playlist_for_current_day")
    public List<Playlist> getRandPlaylist() {
        return service.getRandData(Playlist.class, 5);
    }

    ///////////////////////////////////////////////////////////////////////////
    // GET ALL
    ///////////////////////////////////////////////////////////////////////////

    @RequestMapping("/all_playlist")
    public List<Playlist> getAllPlayList() {
        return service.getPlaylistRepository().findAll();
    }

    @RequestMapping("/all_album")
    public List<Album> getAllAlbum() {
        return service.getAlbumRepository().findAll();
    }

    @RequestMapping("/all_topic")
    public List<Topic> getAllTopic() {
        return service.getTopicRepository().findAll();
    }

    @RequestMapping("/all_category_in_topic/{id}")
    public List<Category> getAllCategoryInTopic(@PathVariable String id) {
        return service.getCategoryRepository().getAllCategoryInTopic(id);
    }

    ///////////////////////////////////////////////////////////////////////////
    // GET SONG
    ///////////////////////////////////////////////////////////////////////////

    @RequestMapping("/song/{id}")
    public Song getSong(@PathVariable String id) {
        return service.getSongRepository().findById(Long.valueOf(id)).orElse(null);
    }

    @RequestMapping("/newly_released_music")
    public List<Song> getNewlyReleasedMusic() {
        return service.getNewlyReleasedMusic();
    }

    @RequestMapping("/all_song_from/{type}/{id}")
    public List<Song> getAllSongFrom(@PathVariable String type, @PathVariable String id) {
        if (!Arrays.asList("album", "category", "playlist").contains(type)) {
            return null;
        }
        return service.getAllSongFrom(type, id);
    }

    @RequestMapping("/get_suggest_song/{id}/{listCurrentSongId}")
    public List<Song> getSuggestSong(@PathVariable String id, @PathVariable String listCurrentSongId) {
        return service.getRandData(Song.class, 10);
    }

}
