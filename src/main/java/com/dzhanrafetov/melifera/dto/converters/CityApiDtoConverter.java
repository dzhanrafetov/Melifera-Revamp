package com.dzhanrafetov.melifera.dto.converters;

import com.dzhanrafetov.melifera.dto.CityApiDto;
import com.dzhanrafetov.melifera.model.CityApi;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityApiDtoConverter {



    public CityApiDto convert(CityApi from){
        return new CityApiDto(
                from.getId(),
                from.getName());
    }


    public List<CityApiDto> convert(List<CityApi> fromList){
        return fromList.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<CityApiDto> convertToDtoList(List<String> cityNames) {
        return cityNames.stream()
                .map(cityName -> new CityApi(cityNames.indexOf(cityName) + 1, cityName)) // Assign unique IDs
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
