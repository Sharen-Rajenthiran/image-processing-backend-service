package com.example.image_processing_service.services;

import com.example.image_processing_service.models.TransformationRequest;
import com.example.image_processing_service.models.TransformationResponse;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.stereotype.Service;

@Service
public class TransformationService {
    private static final String TEST_IMAGE = "C:/Users/User/Desktop/Code/end-to-end/Image-Processing-Service/image-processing-service/image-processing-service/src/main/java/com/example/image_processing_service/images/dev/";

    public TransformationResponse applyTransformation(String filename, TransformationRequest request) {
        // Load Image
        String inputPath = TEST_IMAGE + filename;
        Mat source = opencv_imgcodecs.imread(inputPath);

        if (source.empty()) {
            return new TransformationResponse(
                    "Error: Could not load image",
                    inputPath,
                    null
            );
        }

        Mat result = source;

        // Apply test transformation
        if (request.getResize() != null) {
            result = Transformations.resize(result,
                    request.getResize().getWidth(),
                    request.getResize().getHeight());
        }

        String outputPath = TEST_IMAGE + "out_" + filename;
        opencv_imgcodecs.imwrite(outputPath, result);

        return new TransformationResponse(
                "Transformation successful",
                inputPath,
                outputPath
        );
    }

}
