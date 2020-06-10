package co.currere;

import co.currere.service.NumberGuessService;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/app")
public class NumberGuessApplication extends ResourceConfig {

	public NumberGuessApplication() {
		register(NumberGuessService.class);
		packages("co.currere");
	}
}