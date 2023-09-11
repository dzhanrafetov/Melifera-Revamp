package com.dzhanrafetov.melifera.dto.converters;

import com.dzhanrafetov.melifera.dto.AdvertisementDocumentDto;
import com.dzhanrafetov.melifera.model.AdvertisementDocument;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementDocumentDtoConverter {

    public AdvertisementDocumentDto convert(AdvertisementDocument from) {
        return new AdvertisementDocumentDto(
                from.getId(),
                from.getTitle(),
                from.getDescription(),
                from.getPrice(),
                from.getCreationDate(),
                from.getLastModifiedDate(),
                from.getArchived(),
                from.getCategoryId(),
                from.getUserId()
        );

    }


    public List<AdvertisementDocumentDto> convert(List<AdvertisementDocument> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }


}
