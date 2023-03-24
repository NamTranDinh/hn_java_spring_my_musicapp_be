package com.aptech.mymusic.presentation.internalmodel;

import com.aptech.mymusic.config.ResourceConfig;
import com.aptech.mymusic.presentation.service.storage.AmazonStorageService;
import com.aptech.mymusic.presentation.service.storage.FirebaseStorageService;
import com.aptech.mymusic.presentation.service.storage.LocalStorageService;
import com.aptech.mymusic.utils.FileUtils;
import com.google.firebase.database.utilities.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public class Resource {
    public static String getUrl(Path path, String name) {
        return getUrl(ResourceConfig.getInstance().getSystemResourceType(), path, name);
    }

    @Nullable
    public static String getUrl(@NotNull Type type, Path path, String name) {
        switch (type) {
            case AWS3:
                return AmazonStorageService.getUrl(path, name);
            case GCP:
                return FirebaseStorageService.getUrl(path, name);
            case LOCAL:
                return LocalStorageService.getUrl(path, name);
        }
        return null;
    }

    private static String BASE_URL;

    public static String getBaseUrl() {
        if (BASE_URL != null) {
            return BASE_URL;
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String scheme = request.getScheme();
            String host = request.getServerName();
            int port = request.getServerPort();
            BASE_URL = scheme + "://" + host + ":" + port + "/";
        }
        return BASE_URL;
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

    public enum Layout {
        CommonMaster("templates/master/common_layout.html"),
        AdminMaster("templates/master/admin_layout.html"),
        ClientMaster("templates/master/client_layout.html"),
        CommonFragment("templates/fragments/common_fragments.html"),
        AdminFragment("templates/fragments/admin_fragments.html"),
        ClientFragment("templates/fragments/client_fragments.html");

        private final String name;

        Layout(@NotNull String name) {
            this.name = name.substring("templates/".length(), name.lastIndexOf("."));
        }

        @NotNull
        public String getName() {
            return name;
        }
    }

    public enum Icon {
        MusicIcon("static/public/icons/icon_music.png");
        private final String name;

        Icon(@NotNull String name) {
            this.name = name.substring("static/".length());
        }

        @NotNull
        public String getName() {
            return getBaseUrl() + name;
        }
    }
}
