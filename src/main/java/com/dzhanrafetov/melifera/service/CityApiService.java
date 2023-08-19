package com.dzhanrafetov.melifera.service;

import com.dzhanrafetov.melifera.dto.CityApiDto;
import com.dzhanrafetov.melifera.dto.converters.CityApiDtoConverter;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityApiService {

    private final CityApiClient cityApiClient;
    private final CityApiDtoConverter converter;

    public CityApiService(CityApiClient cityApiClient, CityApiDtoConverter converter) {
        this.cityApiClient = cityApiClient;
        this.converter = converter;
    }

    public List<CityApiDto> getBulgarianCities() {
        Map<String, Object> responseData = cityApiClient.getCountries();

        if (responseData.containsKey("data")) {
            List<Map<String, Object>> countries = (List<Map<String, Object>>) responseData.get("data");
            List<String> bulgarianCities = countries.stream()
                    .filter(country -> "Bulgaria".equalsIgnoreCase((String) country.get("country")))
                    .flatMap(country -> ((List<String>) country.get("cities")).stream())
                    .collect(Collectors.toList());

            return converter.convertToDtoList(bulgarianCities);
        } else {
            throw new NotFoundException("Not Found Any Cities in Bulgaria");
        }
    }
}
