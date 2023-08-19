package com.dzhanrafetov.melifera.dto.converters;


import com.dzhanrafetov.melifera.dto.UserDetailsDto;
import com.dzhanrafetov.melifera.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersDetailsDtoConverter {


    public UserDetailsDto convert(UserDetails from) {
        return new UserDetailsDto(
                from.getPhoneNumber(),
                from.getAddress(),
                from.getCity(),
                from.getState(),
                from.getPostCode(),
                from.getCountry(),
                from.getId());
    }

    public List<UserDetailsDto> convert(List<UserDetails> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }

}