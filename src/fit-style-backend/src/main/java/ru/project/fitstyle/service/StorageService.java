package ru.project.fitstyle.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(final MultipartFile file);

    Stream<Path> loadAll();

    Path load(final String filename);

    Resource loadAsResource(final String filename);

    void deleteAll();

}
