package co.currere.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	@Bean
	public RouteLocator myRoutes(
			RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/api/service/game")
						.filters(f -> f.rewritePath("/api/service/game",
								"/number-guess-service/app/service/game"))
						.uri("http://localhost:8081"))
				.route(p -> p
						.path("/api/results/**")
						.filters(f ->
								f.rewritePath("/api/results",
										"/number-guess-service/app/results"))
						.uri("http://localhost:8081"))
				.route(p -> p
						.path("/api/leaders**")
						.filters(f ->
								f.rewritePath("/api/leaders",
										"/award-service/app/leaders"))
						.uri("http://localhost:8082"))
				.route(p -> p
						.path("/api/stats**")
						.filters(f ->
								f.rewritePath("/api/stats",
										"/award-service/app/stats"))
						.uri("http://localhost:8082"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
