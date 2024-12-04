package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${labs.gateway.host}") String host,
			@Value("${labs.bookservice.url}") String bookUrl,
			@Value("${labs.genreservice.url}") String genreUrl
	) {
		return builder.routes()
				.route("books", route -> route
						.host(host)
						.and()
						.path("/api/books", "/api/books/{id}", "/api/books/genres/{specieId}")
						.and()
						.method("GET", "POST", "DELETE")
						.uri(bookUrl))
				.route("genres", route -> route
						.host(host)
						.and()
						.path("/api/genres", "/api/genres/{id}")
						.and()
						.method("GET", "POST", "DELETE")
						.uri(genreUrl))
				.build();
	}
}