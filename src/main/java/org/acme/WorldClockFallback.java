package org.acme;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class WorldClockFallback implements FallbackHandler<WorldClock> {

    @Override
    public WorldClock handle(ExecutionContext executionContext) {
        WorldClock worldClock = new WorldClock();
        worldClock.setCurrentDateTime("No Time");
        return worldClock;
    }

}
