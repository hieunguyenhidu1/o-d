package com.example.ScronAndTransaction.controller;

import com.example.ScronAndTransaction.service.DynamicSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private DynamicSchedulerService schedulerService;

    @PostMapping("/start")
    public String start() {
        schedulerService.startJob();
        return "Job started";
    }

    @PostMapping("/stop")
    public String stop() {
        schedulerService.stopJob();
        return "Job stopped";
    }

    @PostMapping("/update")
    public String update(@RequestParam String cron) {
        schedulerService.updateCron(cron);
        return "Job updated with cron: " + cron;
    }
}
