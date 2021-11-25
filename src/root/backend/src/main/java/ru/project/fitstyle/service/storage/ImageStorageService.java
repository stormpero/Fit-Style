package ru.project.fitstyle.service.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.project.fitstyle.config.properties.ImageStorageProperties;
import ru.project.fitstyle.exception.storage.EStorageError;
import ru.project.fitstyle.exception.storage.StorageException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements StorageService {

    private final Path rootLocation;

    private final String defaultFileName;

    @Autowired
    public ImageStorageService(ImageStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.defaultFileName = properties.getDefaultImageName();
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException(EStorageError.INIT);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename;
        if(file != null) {
            filename = StringUtils.cleanPath(file.getOriginalFilename());
        }
        else {
            filename = defaultFileName;
        }
        try {
            if (file.isEmpty()) {
                throw new StorageException(EStorageError.STORE);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(EStorageError.STORE);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException(EStorageError.STORE);
        }

        return filename;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException(EStorageError.READ);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageException(EStorageError.NOT_FOUND);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageException(EStorageError.NOT_FOUND);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}