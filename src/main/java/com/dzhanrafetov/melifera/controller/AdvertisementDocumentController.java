package com.dzhanrafetov.melifera.controller;

import com.dzhanrafetov.melifera.dto.AdvertisementDocumentDto;
import com.dzhanrafetov.melifera.service.AdvertisementDocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/advertisement-documents")
public class AdvertisementDocumentController {

    private final AdvertisementDocumentService advertisementDocumentService;


    public AdvertisementDocumentController(AdvertisementDocumentService advertisementDocumentService) {
        this.advertisementDocumentService = advertisementDocumentService;
    }

    @GetMapping("/search")
    public List<AdvertisementDocumentDto> searchByTitleAndDescriptionWithFuzziness(@RequestParam String query) {
        return advertisementDocumentService.searchByTitleAndDescriptionWithFuzziness(query);
    }
    @GetMapping("/search_title")
    public List<AdvertisementDocumentDto> searchByTitleWithFuzziness(@RequestParam String query) {
        return advertisementDocumentService.searchByTitleWithFuzziness(query);
    }


}
