package org.acme;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@ApplicationScoped
public class WorldClockChecks {

    @Produces
    @ApplicationScoped
    @Readiness
    HealthCheck readness() {
        return () -> HealthCheckResponse.named("Greeetings").up().build();
    }

    @Produces
    @ApplicationScoped
    @Liveness
    HealthCheck liveness() {
        return () -> HealthCheckResponse.named("World Clock Api").status(isWorldClockApiUp()).build();
    }

    private boolean isWorldClockApiUp() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("worldclockapi.com", 80), 1000);
            return true;
        } catch (IOException E) {
            return false;
        }
    }

}
