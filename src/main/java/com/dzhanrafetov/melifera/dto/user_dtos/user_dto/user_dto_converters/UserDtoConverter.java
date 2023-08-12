package com.dzhanrafetov.melifera.dto.user_dtos.user_dto.user_dto_converters;


import com.dzhanrafetov.melifera.dto.advertisement_dtos.advertisement_dto.advertisement_dto_converters.AdvertisementDtoConverter;
import com.dzhanrafetov.melifera.dto.user_dtos.user_dto.UserDto;
import com.dzhanrafetov.melifera.dto.user_dtos.userdetails_dto.userdetails_dto_converters.UsersDetailsDtoConverter;
import com.dzhanrafetov.melifera.model.user_model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
private final UsersDetailsDtoConverter usersDetailsDtoConverter;
    private final AdvertisementDtoConverter advertisementDtoConverter;

    public UserDtoConverter(UsersDetailsDtoConverter converter, AdvertisementDtoConverter advertisementDtoConverter) {
        this.usersDetailsDtoConverter = converter;
        this.advertisementDtoConverter = advertisementDtoConverter;
    }


    public UserDto convert(User from){



        return new UserDto(
                from.getId(),
                from.getUsername(),
                from.getPassword(),
                from.getRole(),
                from.getMail(),
                from.getCreationTime(),
                from.getActive(),
                usersDetailsDtoConverter.convert(new ArrayList<>
                        (from.getUserDetailsSet())),
                advertisementDtoConverter.convert(new ArrayList<>
                        (from.getAdvertisementSet())));

    }



    public List<UserDto> convert(List<User> fromList){
        return fromList.stream().map(this::convert).collect(Collectors.toList());
   }
}
