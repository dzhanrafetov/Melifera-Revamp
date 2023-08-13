package com.dzhanrafetov.melifera.dto.api_dtos.cityapi_dto_converters;

import com.dzhanrafetov.melifera.dto.api_dtos.CityApiDto;
import com.dzhanrafetov.melifera.dto.user_dtos.user_dto.UserDto;
import com.dzhanrafetov.melifera.model.cityapi_model.CityApi;
import com.dzhanrafetov.melifera.model.user_model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public CityApiDto convertToDto(int id, String name) {
        return new CityApiDto(id, name);
    }
}
