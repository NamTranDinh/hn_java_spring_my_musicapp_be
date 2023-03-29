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
        MasterCommon("templates/master/common_layout.html"),
        MasterAjax("templates/master/common_ajax_layout.html"),
        MasterAdmin("templates/master/admin_layout.html"),
        MasterAdminBlank("templates/master/admin_blank_layout.html"),
        MasterClient("templates/master/client_layout.html"),
        SharedCommon("templates/common/shared.html");

        private final String name;

        Layout(@NotNull String name) {
            this.name = verifyLayout(name);
        }

        @NotNull
        public String getName() {
            return name;
        }

        @NotNull
        public static String verifyLayout(@NotNull String layout) {
            if (layout.startsWith("templates/")) {
                layout = layout.substring("templates/".length());
            }
            if (layout.endsWith(".html")) {
                layout = layout.substring(0, layout.lastIndexOf("."));
            }
            return layout;
        }
    }

    public enum Icon {
        AppLogo("static/public/admin/assets/img/favicon/favicon.png");
        private final String name;

        Icon(@NotNull String name) {
            this.name = name.substring("static".length());
        }

        @NotNull
        public String getName() {
            return name;
        }
    }
}
