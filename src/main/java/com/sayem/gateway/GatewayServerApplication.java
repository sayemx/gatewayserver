package com.sayem.gateway;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
	
	
	@Bean
	public RouteLocator sayemRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/sayem/accounts/**")
						.filters(f -> f
								.rewritePath("/sayem/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								)
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/sayem/loans/**")
						.filters(f -> f
								.rewritePath("/sayem/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								)
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/sayem/cards/**")
						.filters(f -> f
								.rewritePath("/sayem/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								)
						.uri("lb://CARDS"))
				.build();
	}

}
