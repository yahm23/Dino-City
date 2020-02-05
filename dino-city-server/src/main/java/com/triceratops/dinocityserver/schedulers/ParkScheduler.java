package com.triceratops.dinocityserver.schedulers;

import com.triceratops.dinocityserver.services.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParkScheduler {

    @Autowired
    ParkService parkService;

    @Scheduled(fixedRate = 60000)
    public void scheduleDailyUpdates() {
        parkService.updateAllParks();
    }
}
