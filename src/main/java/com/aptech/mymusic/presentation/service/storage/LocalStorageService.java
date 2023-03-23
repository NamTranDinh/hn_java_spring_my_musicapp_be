package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.presentation.internalmodel.Resource;
import com.google.common.io.Files;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class LocalStorageService extends StorageService {

    private static final String RESOURCE_STATIC_PATH = "./src/main/resources/static/";

    LocalStorageService() {
    }

    @Override
    protected void init() {
    }

    @Override
    public boolean uploadFile(@NotNull MultipartFile file, @NotNull Resource.Path path, String name) {
        try {
            Files.write(file.getBytes(), fileOf(path, name));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean deleteFile(@NotNull Resource.Path path, String name) {
        File file = fileOf(path, name);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    @NotNull
    private File fileOf(@NotNull Resource.Path path, String name) {
        return new File(RESOURCE_STATIC_PATH, path.getPath() + name);
    }

    @Nullable
    public static String getUrl(@NotNull Resource.Path path, String name) {
        if (getBaseUrl() == null) {
            return null;
        }
        return BASE_URL + RESOURCE_STATIC_PATH.substring(1) + path.getPath() + name;
    }

    private static String BASE_URL;

    private static String getBaseUrl() {
        if (BASE_URL != null) {
            return BASE_URL;
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String scheme = request.getScheme();
            String host = request.getServerName();
            int port = request.getServerPort();
            BASE_URL = scheme + "://" + host + ":" + port;
        }
        return BASE_URL;
    }
}
