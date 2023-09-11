package com.dzhanrafetov.melifera.repository;

import com.dzhanrafetov.melifera.model.AdvertisementDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AdvertisementDocumentRepository extends ElasticsearchRepository<AdvertisementDocument, String> {
    @Query("{\"bool\": {\"should\": [" +
            "{\"fuzzy\": {\"title\": {\"value\": \"?0\", \"fuzziness\": \"AUTO\"}}}," +
            " {\"fuzzy\": {\"description\": {\"value\": \"?0\", \"fuzziness\": \"AUTO\"}}}]}}")
    List<AdvertisementDocument> searchByTitleAndDescriptionWithFuzziness(String query);


    @Query("{\"fuzzy\": {\"title\": {\"value\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<AdvertisementDocument> searchByTitleWithFuzziness(String query);


}