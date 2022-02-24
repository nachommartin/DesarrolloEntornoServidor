package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login/**")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("POST", "Content-Type","X-Requested-With","accept","Origin",
						"Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				registry.addMapping("/register/**")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("POST", "Content-Type","X-Requested-With","accept","Origin",
						"Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				registry.addMapping("/usuario/**")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("POST", "GET", "Content-Type","X-Requested-With","accept","Origin",
						"Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				registry.addMapping("/juego/**")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST", "Content-Type","X-Requested-With","accept","Origin",
						"Authorization","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				registry.addMapping("/main/**")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
		};
	};
	
	
	}
	
}
