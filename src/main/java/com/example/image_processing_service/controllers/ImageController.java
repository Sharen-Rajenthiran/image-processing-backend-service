package com.example.image_processing_service.controllers;

import com.example.image_processing_service.models.ImageResponse;
import com.example.image_processing_service.models.PaginatedImageResponse;
import com.example.image_processing_service.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping
    public ResponseEntity<PaginatedImageResponse> getImages(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        PaginatedImageResponse response = imageService.getPaginatedImages(page, limit);

        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images")
                .toUriString();

        if (page > 1) { response.setPrevPage(baseUrl + "?page=" + (page - 1) + "&limit=" + limit); }
        if (page < response.getTotalPages()) { response.setNextPage(baseUrl + "?page=" + (page + 1) + "&limit=" + limit); }

        return ResponseEntity.ok(response);
    }

}
