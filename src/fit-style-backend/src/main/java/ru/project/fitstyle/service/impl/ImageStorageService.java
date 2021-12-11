package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.project.fitstyle.config.properties.ImageStorageProperties;
import ru.project.fitstyle.service.StorageService;
import ru.project.fitstyle.service.exception.storage.FileNotFoundException;
import ru.project.fitstyle.service.exception.storage.StorageException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public ImageStorageService(final ImageStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename;
        try {
            String [] div = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            filename = StringUtils.cleanPath(UUID.randomUUID().toString() + '.' + Objects.requireNonNull(div[div.length - 1]));
            System.out.println(filename);
        }
        catch (NullPointerException e) {
            return null;
        }
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty!");
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException("Cannot store file!");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Cannot store file!");
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
            throw new StorageException("Cannot read stored files!");
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
                throw new FileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (NullPointerException e) {
            throw new FileNotFoundException("File does not exist!");
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}