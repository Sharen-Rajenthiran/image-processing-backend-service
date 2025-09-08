package com.example.image_processing_service.services;

import com.example.image_processing_service.models.ImageResponse;
import com.example.image_processing_service.utils.ConfigUtil;
import org.springframework.stereotype.Service;
import java.nio.file.*;
import java.util.Properties;

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
}
