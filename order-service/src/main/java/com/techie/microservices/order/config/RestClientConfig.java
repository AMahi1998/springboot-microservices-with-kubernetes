package com.techie.microservices.order.config;

import com.techie.microservices.order.client.InventoryClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@RequiredArgsConstructor
@Configuration
public class RestClientConfig {
    @Value("${inventory.url}")
    private String inventoryServiceUrl;
    private final ObservationRegistry observationRegistry;
    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient=RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .observationRegistry(observationRegistry)
                //.requestFactory(getClientRequestFactory())
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);

    }
   // private ClientHttpRequestFactory getClientRequestFactory(){

//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(Duration.ofSeconds(3));
//        factory.setReadTimeout(Duration.ofSeconds(3));
//        return factory;
//        // Alternative way to create ClientHttpRequestFactory using ClientHttpRequestFactories
//        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings= ClientHttpRequestFactorySettings.DEFAULTS
//                .withConnectTimeout(Duration.ofSeconds(3))
//                .withReadTimeout(Duration.ofSeconds(3));
//        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
//    }
}
