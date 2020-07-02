package co.currere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class NumberGuessApplication {

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource= new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.addAllowedMethod("HEAD");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("DELETE");
		corsConfig.addAllowedMethod("PATCH");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(NumberGuessApplication.class, args);
	}
}
