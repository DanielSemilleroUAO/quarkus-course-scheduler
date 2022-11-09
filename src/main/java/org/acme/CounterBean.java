package org.acme;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class CounterBean {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Scheduled(every = "{increment.every}")
    void print(){
        System.out.println(atomicInteger.incrementAndGet());
    }

}
