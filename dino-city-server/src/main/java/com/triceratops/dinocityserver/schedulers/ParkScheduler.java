package com.triceratops.dinocityserver.schedulers;

import com.triceratops.dinocityserver.services.ParkService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParkScheduler {
    ParkService parkService;

    @Scheduled(fixedRate = 60000)
    public void scheduleDailyUpdates() {
//        System.out.println("Schedule activity");
        parkService.updateAllParks();
    }
}
