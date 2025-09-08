package com.example.image_processing_service.models;

public class TransformationResponse {
    private String message;
    private String inputPath;
    private String outputPath;
    private String imageName;
    private String imageFormat;
    private AppliedTransformations transformations;

    public TransformationResponse(
            String message,
            String inputPath,
            String outputPath,
            String imageName,
            String imageFormat,
            AppliedTransformations transformations
    ) {
        this.message = message;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.imageName = imageName;
        this.imageFormat = imageFormat;
        this.transformations = transformations;
    }

    // Getters & Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public AppliedTransformations getTransformations() {
        return transformations;
    }

    public void setTransformations(AppliedTransformations transformations) {
        this.transformations = transformations;
    }

}
