package com.example.image_processing_service.controllers;

import com.example.image_processing_service.models.TransformationRequest;
import com.example.image_processing_service.models.TransformationResponse;
import com.example.image_processing_service.services.TransformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class TransformationController {
    private final TransformationService transformationService;

    public TransformationController(TransformationService transformationService) {
        this.transformationService = transformationService;
    }

    @PostMapping("/{filename}/transform")
    public ResponseEntity<TransformationResponse> transformImage(
            @PathVariable("filename") String filename,
            @RequestBody TransformationRequest request) {

        TransformationResponse response = transformationService.applyTransformation(filename, request);
        return ResponseEntity.ok(response);
    }
}
