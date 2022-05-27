package io.base.coreapi.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    String store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Optional<String> saveImage(String filename, String base64Content);

    Optional<String> saveFile(String filename, String base64Content);

    Resource loadAsResource(String filename);

    void deleteAll();
}
