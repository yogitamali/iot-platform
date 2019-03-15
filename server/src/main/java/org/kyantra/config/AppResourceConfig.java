package org.kyantra.config;

import javax.ws.rs.ApplicationPath;

// Deployment of a JAX-RS application using @ApplicationPath with Servlet 3.0
// Descriptor-less deployment
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationPath("resources")
public class AppResourceConfig extends ResourceConfig {
    public AppResourceConfig() {
        System.out.println("ok");
        packages("org.kyantra.");
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
        System.out.println("working");
    }


}