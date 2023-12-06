package org.isa.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${isa.carBrand.url}") String carBrandUrl,
                                     @Value("${isa.carModel.url}") String carModelUrl,
                                     @Value("${isa.gateway.host}") String host) {
        return builder.routes()
                .route("carBrand_route", r -> r
                        .path("/carBrands/**")
                        .uri(carBrandUrl))
                .route("carModel_route", r -> r
                        .path("/carModels/**")
                        .uri(carModelUrl))
                .route("carBrand_id_route", r -> r
                        .path("/carBrands/{id}")
                        .uri(carBrandUrl))
                .route("carModel_id_route", r -> r
                        .path("/carModels/{id}")
                        .uri(carModelUrl))
                .route("fallback_route", r -> r
                        .path("/**")
                        .uri(host))
                .build();
    }
}
