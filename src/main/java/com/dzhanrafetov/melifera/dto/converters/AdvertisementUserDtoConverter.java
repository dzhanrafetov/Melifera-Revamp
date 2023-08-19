package com.dzhanrafetov.melifera.dto.converters;


import com.dzhanrafetov.melifera.dto.AdvertisementUserDto;
import com.dzhanrafetov.melifera.model.Advertisement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementUserDtoConverter {
    private final AdvertisementDtoConverter advertisementDtoConverter;
    private final ImageDtoConverter imageDtoConverter;
    private final UsersDetailsDtoConverter usersDetailsDtoConverter;

    public AdvertisementUserDtoConverter(AdvertisementDtoConverter advertisementDtoConverter, ImageDtoConverter imageDtoConverter, UsersDetailsDtoConverter usersDetailsDtoConverter) {
        this.advertisementDtoConverter = advertisementDtoConverter;
        this.imageDtoConverter = imageDtoConverter;
        this.usersDetailsDtoConverter = usersDetailsDtoConverter;
    }

    public AdvertisementUserDto convert(Advertisement from) {
        return new AdvertisementUserDto(
                from.getId(),
                from.getTitle(),
                from.getDescription(),
                from.getCategory().getCategoryName(),
                from.getPrice(),
                from.getCreationDate(),
                from.getLastModifiedDate(),
                from.getUser().getUsername(),
                from.getUser().getMail(),
                imageDtoConverter.convert(new ArrayList<>(from.getImagesSet())),
                advertisementDtoConverter.convert(new ArrayList<>
                        (from.getUser().getAdvertisementSet())),
                usersDetailsDtoConverter.convert(new ArrayList<>(from.getUser().getUserDetailsSet()))
        );



    }
    public List<AdvertisementUserDto> convert(List<Advertisement> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }

}
