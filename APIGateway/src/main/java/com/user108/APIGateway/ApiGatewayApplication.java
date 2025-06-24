package com.user108.APIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

//	@Bean
//    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//                        .route("CitizenInteractionService",r->r.path("/citizenInteractionService/**")
//                                .uri("http://localhost:8083/"))
//                        .route("UserService",r->r.path("/userService/**")
//                                .uri("http://localhost:8082/")).build();
//    }
}
