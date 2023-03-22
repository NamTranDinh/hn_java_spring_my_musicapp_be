package com.aptech.mymusic.presentation.internalmodel;

import com.aptech.mymusic.config.FirebaseConfig;
import com.aptech.mymusic.presentation.service.FirebaseStorageService;
import com.google.cloud.storage.BlobId;
import com.google.firebase.database.utilities.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public enum FirebasePath {

    ADS("images/ads"),
    ALBUMS("images/albums"),
    CATEGORIES("images/categories"),
    PLAYLISTS("images/playlists"),
    SONGS("images/songs"),
    TOPIC("images/topics"),
    AUDIO("raw");

    private static final int MB = 1024 * 1024;
    private static final int MAX_IMAGE_SIZE = 3 * MB;
    private static final int MAX_AUDIO_SIZE = 10 * MB;

    private final String path;

    FirebasePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getUrl(String name) {
        return FirebaseStorageService.getUrl(this, name);
    }

    @NotNull
    public BlobId blobOf(String name) {
        return blobOf(this, name);
    }

    public Pair<Boolean, String> validFile(MultipartFile file) {
        return validFile(this, file);
    }

    public boolean isImagePath() {
        return !AUDIO.equals(this);
    }

    @NotNull
    public static BlobId blobOf(@NotNull FirebasePath filePath, String fileName) {
        return BlobId.of(FirebaseConfig.BUCKET_NAME, filePath.path + "/" + fileName);
    }

    public static Pair<Boolean, String> validFile(@NotNull FirebasePath path, @NotNull MultipartFile file) {
        return path.isImagePath() ? validImage(file) : validAudio(file);
    }

    @NotNull
    private static Pair<Boolean, String> validImage(@NotNull MultipartFile file) {
        // check ext
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (ext == null) {
            return new Pair<>(false, "File ext invalid!");
        }
        if (!Arrays.asList("png", "jpg", "jpeg").contains(ext.toLowerCase())) {
            return new Pair<>(false, "File ext isn't support!");
        }
        // check size
        if (file.getSize() > MAX_IMAGE_SIZE) {
            return new Pair<>(false, String.format("File size is too large! (max size is %s)", MAX_IMAGE_SIZE));
        }
        // check if file is empty
        if (file.isEmpty()) {
            return new Pair<>(false, "File is empty!");
        }
        // check if file contains valid image data
        try {
            if (ImageIO.read(file.getInputStream()) == null) {
                return new Pair<>(false, "File is not a valid image!");
            }
        } catch (IOException e) {
            return new Pair<>(false, e.getMessage());
        }
        return new Pair<>(true, null);
    }

    @NotNull
    private static Pair<Boolean, String> validAudio(@NotNull MultipartFile file) {
        // check ext
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (ext == null) {
            return new Pair<>(false, "File ext invalid!");
        }
        if (!Objects.equals("mp3", ext.toLowerCase())) {
            return new Pair<>(false, "File ext isn't support!");
        }
        // check size
        if (file.getSize() > MAX_AUDIO_SIZE) {
            return new Pair<>(false, String.format("File size is too large! (max size is %s)", MAX_IMAGE_SIZE));
        }
        // check if file is empty
        if (file.isEmpty()) {
            return new Pair<>(false, "File is empty!");
        }
        /*/
        // check if file contains valid audio data
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getInputStream());
            if (audioInputStream == null) {
                return new Pair<>(false, "File is not a valid audio file!");
            }
        } catch (UnsupportedAudioFileException | IOException e) {
            return new Pair<>(false, e.getMessage());
        }
        /*/
        return new Pair<>(true, null);
    }
}
