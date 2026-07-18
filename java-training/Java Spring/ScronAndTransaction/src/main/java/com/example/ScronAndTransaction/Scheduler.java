package com.example.ScronAndTransaction;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    //***** giay, phut, gio, ngay trong thang, thang, ngay trong tuan(*, */X, ?)
    // fixedDelay(thuc thi xong, cho them X ms moi chay tiep)
    // fixedRate(cu sau X ms chay tiep, khong can biet thuc thi xong chua)
    // initialDelay(ket hop vs 2 cai tren, delay X ms thi chay lan dau)

    //sau moi 5s
    @Scheduled(fixedDelay = 1 * 1000 * 5)
    @SchedulerLock(name = "my_scheduled_task", lockAtLeastFor = "30s", lockAtMostFor = "59s")
    // lockAtLeastFor: Thời gian tối thiểu giữ lock, kể cả khi job chạy xong sớm.
    // lockAtMostFor: Thời gian tối đa giữ lock, để tránh deadlock nếu job bị lỗi, crash hoặc bị kill.
    public void postTime(){
        System.out.println("1. Bay gio la "+java.time.LocalDateTime.now());
    }

//    @Scheduled(fixedDelay = 1 * 1000 * 5)
////    @SchedulerLock(name = "my_scheduled_task", lockAtLeastFor = "30s", lockAtMostFor = "59s")
//    // lockAtLeastFor: Thời gian tối thiểu giữ lock, kể cả khi job chạy xong sớm.
//    // lockAtMostFor: Thời gian tối đa giữ lock, để tránh deadlock nếu job bị lỗi, crash hoặc bị kill.
//    public void postTime2(){
//        System.out.println("2. Bay gio la "+java.time.LocalDateTime.now());
//    }

    // moi 12h trua hang thang
    @Scheduled(cron = "0 0 12 * * ?")
    public void postHaveLaunch(){
        System.out.println("Bot, Đi ăn trưa thôi :)))");
    }


    @Scheduled(cron = "0 0 12 10 * ?")
    public void post1(){
        System.out.println("12h trua ngay 10 hang thang");
    }
}
