package com.dzhanrafetov.melifera.controller;


import com.dzhanrafetov.melifera.dto.AdvertisementDto;
import com.dzhanrafetov.melifera.dto.AdvertisementUserDto;
import com.dzhanrafetov.melifera.dto.requests.CreateAdvertisementRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateAdvertisementRequest;
import com.dzhanrafetov.melifera.service.AdvertisementService;
import org.springframework.stereotype.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("all/getActiveAdvertisements")
    public ResponseEntity<List<AdvertisementDto>> getActiveAdvertisements() {
        return ResponseEntity.ok(advertisementService.getActiveAdvertisements());
    }

    @GetMapping("admin/getAllAdvertisementsV1")
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAdvertisements());
    }

    @GetMapping("admin/getAllAdvertisementsV2")
    public ResponseEntity<List<AdvertisementUserDto>> getAllAdvertisementsUser() {
        return ResponseEntity.ok(advertisementService.getAdvertisementsUser());

    }

    @GetMapping("getAdvertisementById/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable String id) {
        return ResponseEntity.ok(advertisementService.getAdvertisementById(id));

    }

    @GetMapping("getAdvertisementsAddedByUser")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisementsAddedByUser() {
        return ResponseEntity.ok(advertisementService.getAdvertisementsAddedByUser());

    }

    @GetMapping("getActiveAdvertisementsAddedByUser")
    public ResponseEntity<List<AdvertisementDto>> getActiveAdvertisementsAddedByUser() {
        return ResponseEntity.ok(advertisementService.getActiveAdvertisementsAddedByUser());

    }

    @GetMapping("getArchivedAdvertisementsAddedByUser")
    public ResponseEntity<List<AdvertisementDto>> getArchivedAdvertisementsAddedByUser() {
        return ResponseEntity.ok(advertisementService.getArchivedAdvertisementsAddedByUser());

    }

    @GetMapping("getAdvertisementAddedByUser_V2")
    public ResponseEntity<List<AdvertisementUserDto>> getAdvertisementAddedByUser_V2() {
        return ResponseEntity.ok(advertisementService.getAdvertisementAddedByUser_V2());

    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement
            (@Valid @RequestBody CreateAdvertisementRequest advertisementRequest) {
        return ResponseEntity.ok
                (advertisementService.
                        createAdvertisement(advertisementRequest));

    }

    @PutMapping("updateAdvertisementById/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement
            (@PathVariable String id,
             @Valid @RequestBody UpdateAdvertisementRequest updateAdvertisementRequest) {
        return ResponseEntity.ok
                (advertisementService.updateAdvertisement(id, updateAdvertisementRequest));
    }

    @PatchMapping("archiveAdvertisementByUser/{id}")
    public ResponseEntity<Void> archiveAdvertisementByUser(@PathVariable("id") String id) {
        advertisementService.archiveAdvertisementByUser(id);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("admin/archiveAdvertisementById/{advId}")
    public ResponseEntity<Void> archiveAdvertisement(@PathVariable("advId") String advId) {
        advertisementService.archiveAdvertisement(advId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("admin/activateAdvertisementById/{advId}")
    public ResponseEntity<Void> activateAdvertisement(@PathVariable("advId") String advId) {
        advertisementService.activateAdvertisement(advId);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("admin/deleteAdvertisementById/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable("id") String id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("all/getAdvertisementUserById/{id}")
    public ResponseEntity<AdvertisementUserDto> getAdvertisementUserById(@PathVariable String id) {
        return ResponseEntity.ok(advertisementService.getAdvertisementUserById(id));

    }


    @GetMapping("all/getActiveAdvertisementsByCategoryId-v1/{categoryId}")
    public ResponseEntity<List<AdvertisementUserDto>> getActiveAdvertisementsByCategoryIdV1(@PathVariable Long categoryId) {
        return ResponseEntity.ok(advertisementService.getActiveAdvertisementsByCategoryIdV1(categoryId));
    }

}
