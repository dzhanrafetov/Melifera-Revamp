package com.dzhanrafetov.melifera.service;

import com.dzhanrafetov.melifera.dto.AdvertisementDocumentDto;
import com.dzhanrafetov.melifera.dto.converters.AdvertisementDocumentDtoConverter;
import com.dzhanrafetov.melifera.repository.AdvertisementDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementDocumentService {

    private final AdvertisementDocumentRepository advertisementDocumentRepository;
    private final AdvertisementDocumentDtoConverter advertisementDocumentDtoConverter;

    @Autowired
    public AdvertisementDocumentService(AdvertisementDocumentRepository advertisementDocumentRepository, AdvertisementDocumentDtoConverter advertisementDocumentDtoConverter) {
        this.advertisementDocumentRepository = advertisementDocumentRepository;
        this.advertisementDocumentDtoConverter = advertisementDocumentDtoConverter;
    }

    public List<AdvertisementDocumentDto> searchByTitleAndDescriptionWithFuzziness(String query) {
        return advertisementDocumentDtoConverter.convert(advertisementDocumentRepository.searchByTitleAndDescriptionWithFuzziness(query));
    }


    public List<AdvertisementDocumentDto> searchByTitleWithFuzziness(String query) {
        return advertisementDocumentDtoConverter.convert(advertisementDocumentRepository.searchByTitleWithFuzziness(query));
    }
}