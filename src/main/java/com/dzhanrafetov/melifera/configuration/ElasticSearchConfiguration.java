package com.dzhanrafetov.melifera.configuration;//package com.dzhanrafetov.melifera.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
    @EnableElasticsearchRepositories
    public class ElasticSearchConfiguration {
    //
////        @Bean
////        public ElasticsearchRestTemplate elasticsearchTemplate() {
////            ClientConfiguration clientConfiguration = ClientConfiguration.builder()
////                    .connectedTo("localhost:9200")
////                    .build();
////
////            return new ElasticsearchRestTemplate(RestClients.create(clientConfiguration).rest());
////        }
//
//        @Bean
//        public RestHighLevelClient elasticsearchClient() {
//            ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                    .connectedTo(elasticsearchHost + ":" + elasticsearchPort)
//                    .build();
//
//            return RestClients.create(clientConfiguration).rest();
//        }
//    }
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return new ElasticsearchRestTemplate(RestClients.create(clientConfiguration).rest());
    }
}