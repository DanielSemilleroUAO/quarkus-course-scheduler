package org.acme;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletionStage;

@Path("/api")
@RegisterRestClient(baseUri = "http://worldclockapi.com")
public interface WorldClockService {

    @GET
    @Path("/json/cet/now")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 1, delayUnit = ChronoUnit.SECONDS)
    @Fallback(WorldClockFallback.class)
    WorldClock getNow();

}
