package com.example.image_processing_service.models;

public class ImageResponse {
    private String message;
    private String outputPath;
    private String imageName;
    private String imageFormat;

    public ImageResponse(
            String message,
            String outputPath,
            String imageName,
            String imageFormat
    ) {
        this.message = message;
        this.outputPath = outputPath;
        this.imageName = imageName;
        this.imageFormat = imageFormat;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { this.outputPath = outputPath; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImageFormat() { return imageFormat; }
    public void setImageFormat(String imageFormat) { this.imageFormat = imageFormat; }

}
