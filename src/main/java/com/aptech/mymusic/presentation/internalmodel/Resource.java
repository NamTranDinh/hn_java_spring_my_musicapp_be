package com.aptech.mymusic.presentation.internalmodel;

import com.aptech.mymusic.config.ResourceConfig;
import com.aptech.mymusic.presentation.service.storage.AmazonStorageService;
import com.aptech.mymusic.presentation.service.storage.FirebaseStorageService;
import com.aptech.mymusic.presentation.service.storage.LocalStorageService;
import com.aptech.mymusic.utils.FileUtils;
import com.google.firebase.database.utilities.Pair;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class Resource {

    @Nullable
    public static String getUrl(Path path, String name) {
        switch (ResourceConfig.SYSTEM_RESOURCE_TYPE) {
            case AWS3:
                return AmazonStorageService.getUrl(path, name);
            case GCP:
                return FirebaseStorageService.getUrl(path, name);
            case LOCAL:
                return LocalStorageService.getUrl(path, name);
        }
        return null;
    }

    public enum Type {
        AWS3,       // Amazon S3
        GCP,        // Google cloud platform
        LOCAL,      // My computer
    }

    public enum Path {
        ADS("images/ads/"),
        ALBUMS("images/albums/"),
        CATEGORIES("images/categories/"),
        PLAYLISTS("images/playlists/"),
        SONGS("images/songs/"),
        TOPICS("images/topics/"),
        AUDIO("raw/");

        private final String path;

        Path(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public boolean isImagePath() {
            return !AUDIO.equals(this);
        }

        public Pair<Boolean, String> validFile(MultipartFile file) {
            return FileUtils.validFile(isImagePath(), file);
        }

    }


}
