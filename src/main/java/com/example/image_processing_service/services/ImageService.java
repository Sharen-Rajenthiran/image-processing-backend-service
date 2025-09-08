package com.example.image_processing_service.services;

import com.example.image_processing_service.models.ImageResponse;
import com.example.image_processing_service.models.PaginatedImageResponse;
import com.example.image_processing_service.utils.ConfigUtil;
import org.springframework.stereotype.Service;
import java.nio.file.*;
import java.util.Properties;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageService {

    Properties config = ConfigUtil.loadProperties("config.properties");

    public boolean isFileAvailable(String filename) {
        Path dir = Paths.get(config.getProperty("LOCAL_DEV_IMAGES_DIRECTORY"));
        Path file = dir.resolve(filename);

        return Files.exists(file);
    }

    public String fileOutputPath (String filename) {
        if (!isFileAvailable(filename)) { return "No file directory is found"; }

        String baseOutputPath = config.getProperty("LOCAL_DEV_IMAGES_DIRECTORY");
        String outputPath = baseOutputPath + "//" + filename;

        return outputPath;
    }

    public ImageResponse getImageResponse(String filename) {
        String format = filename.contains(".")
                ? filename.substring(filename.lastIndexOf('.') + 1).toLowerCase()
                : "";

        if (!isFileAvailable(filename)) {
            return new ImageResponse(
                    "Image not successfully retrieved",
                    null,
                    filename,
                    null

            );
        }

        String outputPath = fileOutputPath(filename);

        return new ImageResponse(
                "Image successfully retrieved",
                outputPath,
                filename,
                format
        );
    }

    public PaginatedImageResponse getPaginatedImages(int page, int limit) {
        String baseDir = config.getProperty("LOCAL_DEV_IMAGES_DIRECTORY");
        File dir = new File(baseDir);

        File[] files = dir.listFiles((d, name) -> name.matches(".*\\.(png|jpg|jpeg)$"));
        if (files == null || files.length == 0) {
            return new PaginatedImageResponse(page, limit, 0, new ArrayList<>());
        }

        // Sort files by last modified (newest first)
        Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

        int totalItems = files.length;
        int totalPages = (int) Math.ceil((double) totalItems / limit);

        if (page < 1) page = 1;
        if (page > totalPages) page = totalPages;

        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, totalItems);

        List<ImageResponse> responses = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            File file = files[i];
            String fileName = file.getName();
            String format = fileName.contains(".")
                    ? fileName.substring(fileName.lastIndexOf('.') + 1)
                    : "";

            responses.add(new ImageResponse(
                    "OK",
                    file.getAbsolutePath(),
                    fileName,
                    format
            ));
        }

        return new PaginatedImageResponse(page, limit, totalItems, responses);
    }


}
