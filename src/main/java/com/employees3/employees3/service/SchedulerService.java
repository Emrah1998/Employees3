package com.employees3.employees3.service;

import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    public void sendBirthdayEmail(){
        System.out.println("Happy Birthday!");
    }
}
