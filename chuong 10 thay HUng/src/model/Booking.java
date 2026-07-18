package model;

import java.time.LocalDateTime;

public class Booking {
    private LocalDateTime startTime;
    private double revenue;

    public Booking(LocalDateTime startTime, double revenue) {
        this.startTime = startTime;
        this.revenue = revenue;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public double getRevenue() {
        return revenue;
    }
}