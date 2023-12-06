package org.example.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
public class CarBrandRestApiUrl {
    @Value("${isa.carBrand.url.delete}")
    private String deleteUrl;
    @Value("${isa.carBrand.url.put}")
    private String putUrl;
    @Value("${isa.carBrand.url.post}")
    private String postUrl;
}
