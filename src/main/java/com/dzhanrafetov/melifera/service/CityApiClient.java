package com.dzhanrafetov.melifera.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "cityApiClient", url = "https://countriesnow.space/api/v0.1/countries")
public interface CityApiClient {

    @GetMapping()
    Map<String, Object>  getCountries();

}
