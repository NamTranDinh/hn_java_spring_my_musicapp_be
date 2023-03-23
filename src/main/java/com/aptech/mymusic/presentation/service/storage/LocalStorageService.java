package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public class LocalStorageService extends StorageService {

    LocalStorageService() {
    }

    @Override
    protected void init() {

    }

    @Override
    public boolean uploadFile(@NotNull MultipartFile file, Resource.@NotNull Path path, String name) {
        return false;
    }

    @Override
    public boolean deleteFile(Resource.@NotNull Path path, String name) {
        return false;
    }

    @Nullable
    public static String getUrl(@NotNull Resource.Path path, String name) {
        return null;
    }

    private static @NotNull String getBaseUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String scheme = request.getScheme();
            String host = request.getServerName();
            int port = request.getServerPort();
            return scheme + "://" + host + ":" + port;
        }
        return "";
    }
}
