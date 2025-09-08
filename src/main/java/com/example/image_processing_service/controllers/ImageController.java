package com.example.image_processing_service.controllers;

import com.example.image_processing_service.models.ImageResponse;
import com.example.image_processing_service.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{filename}")
    public ResponseEntity<ImageResponse> imageResponseResponseEntity(@PathVariable("filename") String filename) {
        ImageResponse response = imageService.getImageResponse(filename);
        return ResponseEntity.ok(response);
    }

}
