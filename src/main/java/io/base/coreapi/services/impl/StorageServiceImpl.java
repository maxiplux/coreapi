package io.base.coreapi.services.impl;


import io.base.coreapi.exceptions.FileNotFoundException;
import io.base.coreapi.exceptions.StorageException;
import io.base.coreapi.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    private Path rootLocation;


    @Value("${storage.location}")
    private String locationPath;


    @Override
    @PostConstruct
    public void init() {
        try {
            this.rootLocation = Paths.get(locationPath);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                        + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        return filename;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                .filter(path -> !path.equals(this.rootLocation))
                .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }


    @Override
    public Optional<String> saveImage(String filename, String base64Content) {

        FileOutputStream fos;

        try {
            fos = new FileOutputStream(this.rootLocation.resolve(filename).toFile());
            //"data:image/png;base64,"+
            fos.write(Base64.getDecoder().decode(base64Content));
            fos.flush();
            fos.close();
            log.info("saveImage Success");
            return Optional.of(this.rootLocation.resolve(filename).toFile().getAbsolutePath());

        } catch (IOException e) {
            log.error("saveImage {}", e.getMessage());
        }
        return Optional.empty();


    }


    @Override
    public Optional<String> saveFile(String filename, String base64Content) {

        FileOutputStream fos;

        try {
            fos = new FileOutputStream(this.rootLocation.resolve(filename).toFile());
            fos.write(Base64.getDecoder().decode(base64Content));
            fos.flush();
            fos.close();
            log.info("saveFile Success");
            return Optional.of(this.rootLocation.resolve(filename).toFile().getAbsolutePath());

        } catch (IOException e) {
            log.error("saveFile {}", e.getMessage());
        }
        return Optional.empty();


    }


    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
