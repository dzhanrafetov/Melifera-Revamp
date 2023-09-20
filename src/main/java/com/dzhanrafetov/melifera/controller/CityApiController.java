package com.dzhanrafetov.melifera.controller;

import com.dzhanrafetov.melifera.dto.CityApiDto;
import com.dzhanrafetov.melifera.service.CityApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
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