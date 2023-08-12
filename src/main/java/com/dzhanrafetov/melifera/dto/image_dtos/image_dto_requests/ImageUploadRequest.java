package com.dzhanrafetov.melifera.dto.image_dtos.image_dto_requests;


import org.springframework.web.multipart.MultipartFile;

public class ImageUploadRequest {
    private MultipartFile file;

    public ImageUploadRequest() {
    }

    public ImageUploadRequest(MultipartFile file) {
        this.file=file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


}