package co.currere;

import co.currere.service.NumberGuessService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/app")
public class NumberGuessApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
    	Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(NumberGuessService.class);
        return set;
    }
}