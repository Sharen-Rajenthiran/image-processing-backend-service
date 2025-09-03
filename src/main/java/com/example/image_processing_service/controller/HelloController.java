package com.example.image_processing_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.bytedeco.opencv.global.opencv_core;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("/opencv-version")
    public String getOpenCvVersion() {
        return "OpenCV version: " + opencv_core.CV_VERSION;
    }
}
