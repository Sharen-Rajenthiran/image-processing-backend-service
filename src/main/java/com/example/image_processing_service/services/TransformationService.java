package com.example.image_processing_service.services;

import com.example.image_processing_service.models.TransformationRequest;
import com.example.image_processing_service.models.TransformationResponse;
import com.example.image_processing_service.utils.ConfigUtil;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class TransformationService {
    private static final Properties config;
    static {
        config = ConfigUtil.loadProperties("config.properties");
    }
    private static final String TEST_IMAGE = config.getProperty("LOCAL_DEV_IMAGES_DIRECTORY");

    public TransformationResponse applyTransformation(String filename, TransformationRequest request) {
        // Load Image
        String inputPath = TEST_IMAGE + "//" + filename;
        Mat source = opencv_imgcodecs.imread(inputPath);

        if (source.empty()) {
            return new TransformationResponse(
                    "Error: Could not load image",
                    inputPath,
                    null,
                    null,
                    null
            );
        }

        Mat result = source;
        String finalFilename;
        String imageFormat;
        String outputPath;

        // Apply test transformation
        if (request.getResize() != null) {
            result = Transformations.resize(result,
                    request.getResize().getWidth(),
                    request.getResize().getHeight());
        }

        if (request.getFormat() != null) {
            finalFilename = "user_" + filename.split("\\.")[0];
            imageFormat = request.getFormat();
            outputPath = TEST_IMAGE + "//" + finalFilename + "." + imageFormat;
            Transformations.saveTo(result, imageFormat, TEST_IMAGE + "//" + finalFilename);
        } else {
            finalFilename = "out_" + filename;
            outputPath = TEST_IMAGE + "//" + finalFilename;
            opencv_imgcodecs.imwrite(outputPath, result);

            String[] splittedFilename = outputPath.split("\\.");
            imageFormat = splittedFilename[splittedFilename.length - 1];
        }

        return new TransformationResponse(
                "Transformation successful",
                inputPath,
                outputPath,
                finalFilename,
                imageFormat
        );
    }

}
