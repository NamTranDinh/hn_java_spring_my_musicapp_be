package com.aptech.mymusic.presentation.service;

import com.aptech.mymusic.config.FirebaseConfig;
import com.aptech.mymusic.presentation.internalmodel.FirebasePath;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseStorageService {

    private static Storage storage;

    public FirebaseStorageService() throws Exception {
        GoogleCredentials credentials = GoogleCredentials.fromStream(Files.newInputStream(Paths.get(FirebaseConfig.CREDENTIALS_PATH)));
        FirebaseApp.initializeApp(FirebaseOptions.builder()
                .setCredentials(credentials)
                .setProjectId(FirebaseConfig.PROJECT_ID)
                .setStorageBucket(FirebaseConfig.BUCKET_NAME)
                .build());
        storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    public boolean uploadFile(@NotNull MultipartFile file, @NotNull FirebasePath path, String name) {
        try {
            storage.create(BlobInfo.newBuilder(path.blobOf(name))
                    .setContentType(file.getContentType())
                    .build(), file.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @TestOnly
    public boolean uploadFile(String contentType, @NotNull File file, @NotNull FirebasePath path, String name) {
        try {
            storage.create(BlobInfo.newBuilder(path.blobOf(name))
                    .setContentType(contentType)
                    .build(), Files.readAllBytes(file.toPath()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteFile(@NotNull FirebasePath path, String name) {
        return storage.delete(path.blobOf(name));
    }

    @Nullable
    public static String getUrl(@NotNull FirebasePath path, String name) {
        if (storage == null) {
            return null;
        }
        Blob blob;
        if ((blob = storage.get(path.blobOf(name))) != null) {
            return blob.signUrl(1, TimeUnit.HOURS).toString();
        }
        return null;
    }

}
