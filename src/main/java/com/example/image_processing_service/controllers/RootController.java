package com.example.image_processing_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.bytedeco.opencv.global.opencv_core;

@RestController
public class RootController {
    @GetMapping("/")
    public String hello() {
        return "Welcome to Image Processing Service!";
    }

    @GetMapping("/opencv-version")
    public String getOpenCvVersion() {
        return "OpenCV version: " + opencv_core.CV_VERSION;
    }
}
