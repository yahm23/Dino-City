package com.triceratops.dinocityserver.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParkScheduler {

    @Scheduled(fixedRate = 60000)
    public void scheduleDailyUpdates() {
        System.out.println("Schedule activity");
    }
}
