package com.aptech.mymusic.presentation.controller.api;

import com.aptech.mymusic.domain.entities.*;
import com.aptech.mymusic.presentation.internalmodel.FirebasePath;
import com.aptech.mymusic.presentation.service.ApiService;
import com.aptech.mymusic.presentation.service.FirebaseStorageService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.io.File;
import java.util.UUID;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
@TestOnly
@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final ApiService service;
    private final FirebaseStorageService firebaseStorageService;

    public TransferController(ApiService service, FirebaseStorageService firebaseStorageService) {
        this.service = service;
        this.firebaseStorageService = firebaseStorageService;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Test
    ///////////////////////////////////////////////////////////////////////////

    @RequestMapping("/test_url")
    @TestOnly
    public void testUrl() {
        System.out.println("Start");
        service.getSongRepository().findById(1L).ifPresent(song -> {
            System.out.println("Image -> " + song.getImageUrl());
            System.out.println("Audio -> " + song.getAudioUrl());
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Transfer files
    ///////////////////////////////////////////////////////////////////////////

    private static final String DEFAULT_IMAGE_PATH = "./src/main/resources/static/images/music-app";
    private static final String DEFAULT_AUDIO_PATH = "./src/main/resources/static/raw";

    // @PostMapping("/transfer_audio")
    @TestOnly
    public void transferAudio() {
        service.getSongRepository().findAll().forEach(song -> {
            String currentFileName = song.getAudio();
            if (currentFileName == null) {
                return;
            }
            String name = isValidUUID(currentFileName) ? currentFileName : UUID.randomUUID().toString();
            File file = new File(DEFAULT_AUDIO_PATH, currentFileName);
            boolean success = firebaseStorageService.uploadFile("audio/mpeg", file, FirebasePath.AUDIO, name);
            System.out.println("Transfer audio --> " + song.getClass().getSimpleName() + " >>> " + success + " > " + name + " - " + currentFileName);

            if (success) {
                renameFile(DEFAULT_AUDIO_PATH, currentFileName, name + ".mp3");
                song.setAudio(name);
                service.getSongRepository().save(song);
            }
        });
    }

    // @PostMapping("/transfer_images")

    @TestOnly
    public void transferAllImage() {
        transferImages(service.getAdsSongRepository(), AdsSong.class);
        transferImages(service.getAlbumRepository(), Album.class);
        transferImages(service.getCategoryRepository(), Category.class);
        transferImages(service.getPlaylistRepository(), Playlist.class);
        transferImages(service.getSongRepository(), Song.class);
        transferImages(service.getTopicRepository(), Topic.class);
    }

    private <S> void transferImages(@NotNull CrudRepository<S, ?> repository, Class<S> clazz) {
        repository.findAll().forEach(item -> {
            String currentFileName = getFileName(item);
            if (currentFileName == null) {
                return;
            }
            String name = isValidUUID(currentFileName) ? currentFileName : UUID.randomUUID().toString();
            File file = new File(DEFAULT_IMAGE_PATH + getPath(clazz), currentFileName);
            boolean success = firebaseStorageService.uploadFile("image/jpeg", file, getFilePath(item), name);
            System.out.println("Transfer Image --> " + item.getClass().getSimpleName() + " >>> " + success + " > " + name + " - " + currentFileName);

            if (item instanceof Playlist) {
                String currentFileName1 = ((Playlist) item).getImageBackground();
                if (currentFileName1 == null) {
                    return;
                }
                String name1 = isValidUUID(currentFileName1) ? currentFileName1 : UUID.randomUUID().toString();
                File file1 = new File(DEFAULT_IMAGE_PATH + getPath(clazz), currentFileName1);
                boolean success1 = firebaseStorageService.uploadFile("image/jpeg", file1, getFilePath(item), name1);
                System.out.println("Transfer Image --> " + item.getClass().getSimpleName() + " >>> " + success1 + " > " + name1 + " - " + currentFileName1);
                if (success1) {
                    renameFile(DEFAULT_IMAGE_PATH + getPath(clazz), currentFileName1, name1 + ".jpg");
                    ((Playlist) item).setImageBackground(name1);
                }
            }

            if (success) {
                renameFile(DEFAULT_IMAGE_PATH + getPath(clazz), currentFileName, name + ".jpg");
                setFileName(item, name);
                repository.save(item);
            }
        });
    }

    private void renameFile(String parent, String current, String news) {
        File file1 = new File(parent, current);
        File file2 = new File(parent, news);
        //noinspection ResultOfMethodCallIgnored
        file1.renameTo(file2);
    }

    private String getFileName(Object o) {
        if (o instanceof AdsSong) {
            return ((AdsSong) o).getImage();
        }
        if (o instanceof Album) {
            return ((Album) o).getImage();
        }
        if (o instanceof Category) {
            return ((Category) o).getImage();
        }
        if (o instanceof Playlist) {
            return ((Playlist) o).getImageIcon();
        }
        if (o instanceof Song) {
            return ((Song) o).getImage();
        }
        if (o instanceof Topic) {
            return ((Topic) o).getImage();
        }
        return null;
    }

    private void setFileName(Object o, String name) {
        if (o instanceof AdsSong) {
            ((AdsSong) o).setImage(name);
        }
        if (o instanceof Album) {
            ((Album) o).setImage(name);
        }
        if (o instanceof Category) {
            ((Category) o).setImage(name);
        }
        if (o instanceof Playlist) {
            ((Playlist) o).setImageIcon(name);
        }
        if (o instanceof Song) {
            ((Song) o).setImage(name);
        }
        if (o instanceof Topic) {
            ((Topic) o).setImage(name);
        }
    }

    private FirebasePath getFilePath(Object o) {
        if (o instanceof AdsSong) {
            return FirebasePath.ADS;
        }
        if (o instanceof Album) {
            return FirebasePath.ALBUMS;
        }
        if (o instanceof Category) {
            return FirebasePath.CATEGORIES;
        }
        if (o instanceof Playlist) {
            return FirebasePath.PLAYLISTS;
        }
        if (o instanceof Song) {
            return FirebasePath.SONGS;
        }
        if (o instanceof Topic) {
            return FirebasePath.TOPIC;
        }
        return null;
    }

    @NotNull
    private String getPath(Class<?> clazz) {
        if (clazz == AdsSong.class) {
            return "/ads";
        }
        if (clazz == Album.class) {
            return "/albums";
        }
        if (clazz == Category.class) {
            return "/categories";
        }
        if (clazz == Playlist.class) {
            return "/playlists";
        }
        if (clazz == Song.class) {
            return "/songs";
        }
        if (clazz == Topic.class) {
            return "/topics";
        }
        return "";
    }

    private boolean isValidUUID(@Nullable String uuid) {
        if (uuid == null) {
            return false;
        }
        Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        return UUID_REGEX.matcher(uuid).matches();
    }

}
