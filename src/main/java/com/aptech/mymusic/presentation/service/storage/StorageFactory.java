package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.config.ResourceConfig;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StorageFactory {

    public static StorageService createStorageService() {
        return createStorageService(ResourceConfig.getInstance().getSystemResourceType());
    }

    @Nullable
    public static StorageService createStorageService(@NotNull Resource.Type type) {
        switch (type) {
            case AWS3:
                return new AmazonStorageService();
            case GCP:
                return new FirebaseStorageService();
            case LOCAL:
                return new LocalStorageService();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends StorageService> T createStorageService(Class<T> clazz) {
        if (clazz == AmazonStorageService.class) {
            return (T) new AmazonStorageService();
        }
        if (clazz == FirebaseStorageService.class) {
            return (T) new FirebaseStorageService();
        }
        if (clazz == LocalStorageService.class) {
            return (T) new LocalStorageService();
        }
        return null;
    }
}
