package com.dzhanrafetov.melifera.dto.converters;


import com.dzhanrafetov.melifera.dto.UserDto;
import com.dzhanrafetov.melifera.model.User;
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
