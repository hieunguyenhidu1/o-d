package com.example.ScronAndTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulerService {
    //là Bean Scheduler gốc của Spring, lên lịch các Runnable
    @Autowired
    private TaskScheduler taskScheduler;

    //là biến để giữ tham chiếu đến job hiện tại đang chạy.
    private ScheduledFuture<?> scheduledFuture;

    // Cron expression hiện tại (ví dụ khởi tạo mặc định)
    private String cronExpression = "0 0 * * * *"; // chạy mỗi giờ

    // Job thực tế
    private final Runnable job = () -> System.out.println("Running job at " + java.time.LocalDateTime.now());

    // Bắt đầu scheduler
    public void startJob() {
        stopJob(); // tránh job cũ chạy song song
        scheduledFuture = taskScheduler.schedule(job, new CronTrigger(cronExpression));
    }

    // Dừng job
    public void stopJob() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
    }

    // Update cron mới và restart job
    public void updateCron(String newCron) {
        this.cronExpression = newCron;
        startJob(); // khởi động lại với cron mới
    }
}
