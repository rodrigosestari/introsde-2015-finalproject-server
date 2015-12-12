package systemlogic.processcentricservices.rest.start;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("finalprojectrest")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig() {
        packages("systemlogic.processcentricservices.rest.resources");
    }
}
