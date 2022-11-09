package org.acme;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class ReadnessHealtCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse.named("Greeting Up");
        healthCheckResponseBuilder.up();
        if (databaseIsUp()){
            healthCheckResponseBuilder.up();
        } else {
            healthCheckResponseBuilder.down();
            healthCheckResponseBuilder.withData("Fallo", "Base de datos no inicializada");
        }
        return healthCheckResponseBuilder.build();
    }

    private boolean databaseIsUp() {
        return true;
    }
}
