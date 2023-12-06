package org.example.repository.impl;

import org.example.configuration.CarBrandRestApiUrl;
import org.example.dto.GetCarBrandResponse;
import org.example.dto.PutCarBrandRequest;
import org.example.repository.CarBrandRestRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Repository
public class CarBrandRestRepositoryImpl implements CarBrandRestRepository {

    private final RestTemplate restTemplate;
    private final CarBrandRestApiUrl restApiUrl;

    public CarBrandRestRepositoryImpl(RestTemplate restTemplate, CarBrandRestApiUrl restApiUrl) {
        this.restTemplate = restTemplate;
        this.restApiUrl = restApiUrl;
    }

    @Override
    public void delete(UUID id) {
        restTemplate.delete(restApiUrl.getDeleteUrl(), id);
    }

    @Override
    public void updateName(UUID id, PutCarBrandRequest putCarBrandRequest) {
        String url = UriComponentsBuilder.fromUriString(restApiUrl.getPutUrl())
                .pathSegment("{id}")
                .buildAndExpand(id)
                .toUriString();

        restTemplate.put(url, putCarBrandRequest);
    }

    @Override
    public void addCarBrand(GetCarBrandResponse getCarBrandResponse) {
        restTemplate.postForLocation(restApiUrl.getPostUrl(), getCarBrandResponse);
    }
}
