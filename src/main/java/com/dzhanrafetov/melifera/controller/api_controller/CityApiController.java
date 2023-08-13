package com.dzhanrafetov.melifera.controller.api_controller;

import com.dzhanrafetov.melifera.dto.api_dtos.CityApiDto;
import com.dzhanrafetov.melifera.model.cityapi_model.CityApi;
import com.dzhanrafetov.melifera.service.api_service.CityApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityApiController {

    private final CityApiService cityService;

    public CityApiController(CityApiService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/bulgarianCities")
    public List <CityApiDto>  getBulgarianCities() {
        return cityService.getBulgarianCities();
    }
}