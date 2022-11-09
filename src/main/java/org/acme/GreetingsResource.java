package org.acme;

import io.opentracing.Tracer;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class GreetingsResource {

    private Long maxTemperature = 50L;

    @Inject
    @RestClient
    WorldClockService worldClockService;

    @Inject
    Tracer tracer;

    @Gauge(name = "Max Temp", description = "Max Temperature", unit = MetricUnits.NONE)
    public Long getMaxTemperature(){
        return maxTemperature;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CircuitBreaker(
            requestVolumeThreshold = 3,
            failureRatio = .75,
            delay = 1000
    )
    @Timed(
            name = "CheckTimeGetNow",
            description = "Time to get current time",
            unit = MetricUnits.MILLISECONDS
    )
    @Counted(name = "Name of Get Time", description = "Number of calls")
    public WorldClock getNow() {
        tracer.activeSpan().setBaggageItem("time", "now");
        return worldClockService.getNow();
    }

}