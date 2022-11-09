package org.acme;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Liveness
public class LivenessHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse.named("WorldClockApi");
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress("worldclockapi.com", 80), 1000);
            healthCheckResponseBuilder.up();
        } catch (IOException E){
            healthCheckResponseBuilder.down();
        }
        return healthCheckResponseBuilder.build();
    }
}
