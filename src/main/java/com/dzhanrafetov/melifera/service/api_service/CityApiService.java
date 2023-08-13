package com.dzhanrafetov.melifera.service.api_service;

import com.dzhanrafetov.melifera.dto.api_dtos.CityApiDto;
import com.dzhanrafetov.melifera.dto.api_dtos.cityapi_dto_converters.CityApiDtoConverter;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.model.cityapi_model.CityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CityApiService {

    private final RestTemplate restTemplate;
    private final CityApiDtoConverter converter;

    @Autowired
    public CityApiService(RestTemplate restTemplate, CityApiDtoConverter converter) {
        this.restTemplate = restTemplate;
        this.converter = converter;
    }

    public List<CityApiDto> getBulgarianCities() {

            String apiUrl = "https://countriesnow.space/api/v0.1/countries";

            ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);

            List<CityApiDto> bulgarianCities = new ArrayList<>();

            Map responseData = response.getBody();
            assert responseData != null;
            if (responseData.containsKey("data")) {
                List<Map<String, Object>> countries = (List<Map<String, Object>>) responseData.get("data");
                for (Map<String, Object> country : countries) {
                    if (country.containsKey("country") && country.containsKey("cities")) {
                        String countryName = (String) country.get("country");
                        List<String> cities = (List<String>) country.get("cities");

                        if ("Bulgaria".equalsIgnoreCase(countryName)) {
                            for (int i = 0; i < cities.size(); i++) {
                                String cityName = cities.get(i);
                                CityApi cityApi = new CityApi(i + 1, cityName);
                                CityApiDto cityApiDto = converter.convert(cityApi);
                                bulgarianCities.add(cityApiDto);
                            }
                        }
                    }
                }


            return bulgarianCities;
        }else throw new NotFoundException("Not Found Any Cities " );
        }

}
