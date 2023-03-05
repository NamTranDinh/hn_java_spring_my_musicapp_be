package com.aptech.mymusic.utils;

import org.jetbrains.annotations.NotNull;

public class ResourceUtils {

    public enum Path {

        ADS("/images/music-app/ads"),
        ALBUMS("/images/music-app/albums"),
        CATEGORIES("/images/music-app/categories"),
        PLAYLISTS("/images/music-app/playlists"),
        SONGS("/images/music-app/songs"),
        TOPIC("/images/music-app/topic"),
        AUDIO("/raw");

        private static String BASE_URL = null;
        private final String path;

        Path(String path) {
            this.path = path;
        }

        public @NotNull String getPath(String name) {
            if (BASE_URL == null) {
                BASE_URL = UrlUtils.getBaseUrl();
            }
            return BASE_URL + path + "/" + name;
        }

    }

}
