package com.dzhanrafetov.melifera.service;


import com.dzhanrafetov.melifera.dto.AdvertisementDto;
import com.dzhanrafetov.melifera.dto.AdvertisementUserDto;
import com.dzhanrafetov.melifera.dto.converters.AdvertisementDtoConverter;
import com.dzhanrafetov.melifera.dto.converters.AdvertisementUserDtoConverter;
import com.dzhanrafetov.melifera.dto.requests.CreateAdvertisementRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateAdvertisementRequest;
import com.dzhanrafetov.melifera.exception.AdvertisementNotArchivedException;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.exception.UserNotActiveException;
import com.dzhanrafetov.melifera.model.Advertisement;
import com.dzhanrafetov.melifera.model.Category;
import com.dzhanrafetov.melifera.model.User;
import com.dzhanrafetov.melifera.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class AdvertisementService {


    private final AdvertisementRepository repository;
    private final AdvertisementDtoConverter converter;
    private final AdvertisementUserDtoConverter advertisementUserDtoConverter;

    private final UserService userService;
    private final CategoryService categoryService;

    public AdvertisementService(AdvertisementRepository advertisementRepository,
                                AdvertisementDtoConverter converter,
                                AdvertisementUserDtoConverter advertisementUserDtoConverter,
                                UserService userService,
                                CategoryService categoryService) {
        this.repository = advertisementRepository;
        this.converter = converter;
        this.advertisementUserDtoConverter = advertisementUserDtoConverter;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request) {
        User user = userService.findUserById(userService.getCurrentUser().getId());

        Category category = categoryService.findCategoryById(request.getCategoryId());

        if (user.getActive()) {
            Advertisement advertisement =
                    new Advertisement(
                            request.getTitle(),
                            request.getDescription(),
                            request.getPrice(),
                            getLocalDateTimeNow(),
                            getLocalDateTimeNow(),
                            false,
                            user,
                            category);

            return converter.convert(repository.save(advertisement));
        } else {

            throw new UserNotActiveException("You can't create advertisement when your account is not activated!");
        }
    }


    public List<AdvertisementDto> getAdvertisements() {
        return converter.convert(repository.findAll());
    }

    public List<AdvertisementUserDto> getAdvertisementsUser() {
        return advertisementUserDtoConverter.convert(repository.findAll());
    }

    public List<AdvertisementDto> getActiveAdvertisements() {
        return converter.convert(repository.findByIsArchivedFalse());
    }

    public List<AdvertisementUserDto> getActiveAdvertisementsUser() {
        return advertisementUserDtoConverter.convert(repository.findByIsArchivedFalse());
    }


    public List<AdvertisementUserDto> getArchivedAdvertisementsUser() {
        return advertisementUserDtoConverter.convert(repository.findByIsArchivedTrue());
    }


    public List<AdvertisementUserDto> getActiveAdvertisementsByCategoryIdV1(Long categoryId) {
        return advertisementUserDtoConverter.convert(repository.findByIsArchivedFalseAndCategoryId(categoryId));
    }

    //tuka sum
    public List<AdvertisementDto> getActiveAdvertisementsAddedByUser() {
        User user = userService.findUserById(userService.getCurrentUser().getId());
        return converter.convert(repository.
                findByIsArchivedFalseAndUserId(user.getId()));
    }

    public List<AdvertisementDto> getArchivedAdvertisementsAddedByUser() {
        User user = userService.findUserById(userService.getCurrentUser().getId());
        return converter.convert(repository.
                findByIsArchivedTrueAndUserId(user.getId()));
    }

    public AdvertisementDto updateAdvertisement(String id, UpdateAdvertisementRequest request) {

        User user = userService.findUserById(userService.getCurrentUser().getId());
        Advertisement advertisement = findAdvertisementByIdAndUser(id, user.getId());

        Category category = categoryService.findCategoryById(advertisement.getCategory().getId());
        Advertisement updateAdvertisement =
                new Advertisement(
                        advertisement.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getPrice(),
                        advertisement.getCreationDate(),
                        getLocalDateTimeNow(),
                        advertisement.getArchived(),
                        user,
                        category);
        return converter.convert(repository.save(updateAdvertisement));
    }

    public Advertisement findAdvertisementByIdAndUser(String id, Long user_id) {
        return repository.findAdvertisementByIdAndUserId(id, user_id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Advertisement couldn't found by id:  " + id));
    }


    public AdvertisementDto deleteAdvertisement(String id) {
        Advertisement advertisement = findAdvertisementById(id);
        if (advertisement.getArchived()) {
            repository.deleteById(id);
        } else {
            throw new
                    AdvertisementNotArchivedException("You can't delete advertisement" +
                    " when the advertisement is not archived !");

        }
        return null;
    }

    public Advertisement findAdvertisementById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Advertisement couldn't found by id:  " + id));
    }


    public List<Advertisement> findAdvertisementUserById(Long id) {
        return repository.findAdvertisementByUserId(id);
    }

    public List<AdvertisementDto> getAdvertisementsAddedByUser() {
        return converter.convert(findAdvertisementUserById
                (userService.getCurrentUser().getId()));

    }

    public List<AdvertisementUserDto> getAdvertisementAddedByUser_V2() {
        return advertisementUserDtoConverter.convert(findAdvertisementUserById
                (userService.getCurrentUser().getId()));

    }

    public AdvertisementDto getAdvertisementById(String id) {
        Advertisement advertisement = findAdvertisementById(id);
        return converter.convert(advertisement);
    }


    public AdvertisementUserDto getAdvertisementUserById(String id) {
        Advertisement advertisement = findAdvertisementById(id);
        return advertisementUserDtoConverter.convert(advertisement);
    }

    private LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now();
    }


    public AdvertisementUserDto archiveAdvertisement(String adv_id) {
        Advertisement advertisement = findAdvertisementById(adv_id);

        changeArchiveAdvertisement(advertisement.getUser().getId(), adv_id, true);
        return null;
    }


    public AdvertisementDto activateAdvertisement(String adv_id) {
        Advertisement advertisement = findAdvertisementById(adv_id);

        changeArchiveAdvertisement(advertisement.getUser().getId(), adv_id, false);

        return null;
    }

    public AdvertisementUserDto archiveAdvertisementByUser(String adv_id) {
        User user = userService.findUserById(userService.getCurrentUser().getId());
        return changeArchiveAdvertisement(user.getId(), adv_id, true);

    }


    public AdvertisementUserDto changeArchiveAdvertisement(Long user_id, String adv_id, Boolean archived) {
        Advertisement advertisement = findAdvertisementByIdAndUser(adv_id, user_id);

        Advertisement archiveAdvertisement =
                new Advertisement(
                        advertisement.getId(),
                        advertisement.getTitle(),
                        advertisement.getDescription(),
                        advertisement.getPrice(),
                        advertisement.getCreationDate(),
                        getLocalDateTimeNow(),
                        archived,
                        advertisement.getUser(),
                        advertisement.getCategory()
                );

        return advertisementUserDtoConverter.convert(repository.save(archiveAdvertisement));

    }


}









