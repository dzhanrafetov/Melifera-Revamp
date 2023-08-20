package com.dzhanrafetov.melifera.service;

import com.dzhanrafetov.melifera.dto.ImageDto;
import com.dzhanrafetov.melifera.dto.converters.ImageDtoConverter;
import com.dzhanrafetov.melifera.dto.requests.ImageUploadRequest;
import com.dzhanrafetov.melifera.exception.DuplicateEntryException;
import com.dzhanrafetov.melifera.exception.ImageUploadLimitExceededException;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.model.Advertisement;
import com.dzhanrafetov.melifera.model.Image;
import com.dzhanrafetov.melifera.model.User;
import com.dzhanrafetov.melifera.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageDtoConverter converter;
    private final AdvertisementService advertisementService;
    private final ImageRepository repository;

    public ImageService(ImageDtoConverter converter, AdvertisementService advertisementService, ImageRepository repository) {
        this.converter = converter;
        this.advertisementService = advertisementService;
        this.repository = repository;
    }


//    public List<ImageDto> uploadImages(String id, ImageUploadRequest imageRequests) throws IOException {
//        if (imageRequests.size() > 5) { // Fixed the condition to check for exceeding 5 images
//            throw new IllegalArgumentException("You can upload up to 5 images.");
//        }
//
//        List<ImageDto> uploadedImages = new ArrayList<>();
//
//        for (ImageUploadRequest request : imageRequests) {
//            ImageDto uploadedImage = uploadImageWithRequest(id, request); // Use the uploadImageWithLimit method
//            uploadedImages.add(uploadedImage);
//        }
//
//        return uploadedImages;
//    }




    public ImageDto uploadImage(String id, ImageUploadRequest request) throws IOException {
        Advertisement advertisement = advertisementService.findAdvertisementById(id);
        List<ImageDto> existingImages = getImagesByAdvertisementId(id);

        if (existingImages.stream().anyMatch(image -> image.getName().equals(request.getFile().getOriginalFilename()))) {
            throw new DuplicateEntryException("The image is already uploaded for this advertisement.");
        }

        if (existingImages.size() >= 5) {
            throw new ImageUploadLimitExceededException("You can upload up to 5 images.");
        }

        return uploadImageWithRequest(advertisement.getId(), request);
    }
    private ImageDto uploadImageWithRequest(String id, ImageUploadRequest request) throws IOException {
        Advertisement advertisement = advertisementService.findAdvertisementById(id);
        User user = advertisement.getUser();

        String folderName = user.getId() + "-" + user.getUsername() + "/" + advertisement.getId();
        String fileName = request.getFile().getOriginalFilename();

        Path uploadPath = Paths.get("src/main/resources/static/images/uploads", folderName); // Fixed the upload path

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = request.getFile().getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/images/uploads/" + folderName + "/" + fileName;

            Image image = new Image(imageUrl, fileName, advertisement);
            Image savedImage = repository.save(image); // Save the image
            return converter.convert(savedImage);
        }
    }


    public List<ImageDto> getImagesByAdvertisementId(String id) {
        return converter.convert(repository.findImageByAdvertisement_Id(id));
    }
    public List<ImageDto>getAllImages() {
        return converter.convert(repository.findAll());
    }
    public Image findImageById(Long id) {
        return  repository.findById(id).
                orElseThrow(() -> new NotFoundException("Image couldn't found by id:  " + id));
    }

    public void deleteImage(Long id) {
        findImageById(id);
        repository.deleteById(id);
    }


}
