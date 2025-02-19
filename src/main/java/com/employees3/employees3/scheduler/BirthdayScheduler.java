package com.employees3.employees3.scheduler;

import com.employees3.employees3.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BirthdayScheduler {
    private final SchedulerService schedulerService;

    @Scheduled(fixedDelayString = "PT1M")
    @SchedulerLock(name = "sendBirthdayEmail",lockAtLeastFor = "PT1M",lockAtMostFor = "PT3M")
    public void sendBirthdayEmail(){
        schedulerService.sendBirthdayEmail();
    }
}
