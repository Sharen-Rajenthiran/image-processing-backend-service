package com.example.image_processing_service.models;

public class TransformationResponse {
    private String message;
    private String inputPath;
    private String outputPath;

    public TransformationResponse(String message, String inputPath, String outputPath) {
        this.message = message;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
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
}
