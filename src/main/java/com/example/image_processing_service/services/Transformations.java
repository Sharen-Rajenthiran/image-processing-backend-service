package com.example.image_processing_service.services;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.global.opencv_imgcodecs;

public class Transformations {
    // List of Transformations
    // Resize: width, height
    // Crop: width, height
    // Rotate: rotation
    // Filter: grayscale
    // Save: png, jpg, jpeg

    // Resize
    public static Mat resize(Mat source, int width, int height) {
        Mat resizedImage = new Mat();
        opencv_imgproc.resize(source, resizedImage, new Size(width, height));
        return resizedImage;
    }

    // Crop
    public static Mat crop(Mat source, int width, int height) {
        Rect roi = new Rect(0, 0, Math.min(width, source.cols()), Math.min(height, source.rows()));
        return new Mat(source, roi).clone(); // clone so we don't reference original memory
    }

    // Rotate (angle in degrees)
    public static Mat rotate(Mat source, double angle) {
        Mat rotatedImage = new Mat();
        Point2f center = new Point2f(source.cols() / 2.0f, source.rows() / 2.0f);
        Mat rotationMatrix = opencv_imgproc.getRotationMatrix2D(center, angle, 1.0);
        opencv_imgproc.warpAffine(source, rotatedImage, rotationMatrix, new Size(source.cols(), source.rows()));
        return rotatedImage;
    }

    // Filter: Grayscale
    public static Mat toGray(Mat source) {
        Mat grayscaleImage = new Mat();
        opencv_imgproc.cvtColor(source, grayscaleImage, opencv_imgproc.COLOR_BGR2GRAY);
        return grayscaleImage;
    }

    // Save format: png, jpg, jpeg
    public static String saveTo(Mat source, String format, String baseFileName) {
        String normalizedFormat = format.toLowerCase();
        if (normalizedFormat.equals("jpeg")) {
            normalizedFormat = "jpg";
        }
        String finalFilename = baseFileName + "." + normalizedFormat;
        opencv_imgcodecs.imwrite(finalFilename, source);
        return finalFilename;
    }

}
