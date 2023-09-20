package com.dzhanrafetov.melifera.controller;


import com.dzhanrafetov.melifera.dto.ImageDto;
import com.dzhanrafetov.melifera.dto.requests.ImageUploadRequest;
import com.dzhanrafetov.melifera.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("v1/image")
public class ImageController {
    private final ImageService ImageService;

    public ImageController(ImageService ImageService) {
        this.ImageService = ImageService;
    }

    @PostMapping("uploadImageByAdvertisementId/{id}")
    public ResponseEntity<ImageDto> uploadImage
            (@PathVariable String id,
             @RequestParam("file") MultipartFile file) throws IOException {
        ImageUploadRequest request = new ImageUploadRequest(file);
        return ResponseEntity.ok
                (ImageService.
                        uploadImage(id,request));
    }

    @GetMapping("/getImagesByAdvertisementId/{id}")
    public ResponseEntity<List<ImageDto>> getImagesByAdvertisementId(@PathVariable String id) {
        return ResponseEntity.ok(ImageService.getImagesByAdvertisementId(id));
    }


    @GetMapping("/images")
    public ResponseEntity<List<ImageDto>> getAllImages() {
        return ResponseEntity.ok(ImageService.getAllImages());
    }
    @DeleteMapping("/deleteImageById/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") Long id) {

        ImageService.deleteImage(id);
        return ResponseEntity.ok().build();

    }

}