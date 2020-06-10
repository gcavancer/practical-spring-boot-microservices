package co.currere.config;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext request) throws IOException {

		if (isPreflightRequest(request)) {
			request.abortWith(Response.ok().build());
			return;
		}
	}

	private static boolean isPreflightRequest(ContainerRequestContext request) {
		return request.getHeaderString("Origin") != null
				&& request.getMethod().equalsIgnoreCase("OPTIONS");
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response)
			throws IOException {

		if (request.getHeaderString("Origin") == null) {
			return;
		}

		if (isPreflightRequest(request)) {
			response.getHeaders().add("Access-Control-Allow-Credentials", "true");
			response.getHeaders().add("Access-Control-Allow-Methods",
					"GET, POST, PUT, DELETE, OPTIONS, HEAD");
			response.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
		}

		response.getHeaders().add("Access-Control-Allow-Origin", "*");
	}
}