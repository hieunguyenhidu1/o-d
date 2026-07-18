import model.Booking;
import context.RevenueStatisticsContext;
import strategy.RevenueByTimeSlotStrategy;
import strategy.RevenueByWeekdayStrategy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Booking> bookings = Arrays.asList(
            new Booking(LocalDateTime.of(2025, 4, 1, 9, 0), 200000),
            new Booking(LocalDateTime.of(2025, 4, 1, 14, 0), 300000),
            new Booking(LocalDateTime.of(2025, 4, 2, 19, 0), 250000),
            new Booking(LocalDateTime.of(2025, 4, 2, 10, 0), 150000),
            new Booking(LocalDateTime.of(2025, 4, 3, 18, 30), 350000)
        );

        RevenueStatisticsContext context = new RevenueStatisticsContext();

        // 1. Thống kê theo khung giờ
        context.setStrategy(new RevenueByTimeSlotStrategy());
        Map<String, Double> byTimeSlot = context.execute(bookings);
        System.out.println("🔹 Thống kê theo khung giờ:");
        byTimeSlot.forEach((slot, total) ->
            System.out.println(slot + ": " + total + " VNĐ")
        );

        System.out.println();

        // 2. Thống kê theo ngày trong tuần
        context.setStrategy(new RevenueByWeekdayStrategy());
        Map<String, Double> byWeekday = context.execute(bookings);
        System.out.println("🔹 Thống kê theo ngày trong tuần:");
        byWeekday.forEach((day, total) ->
            System.out.println(day + ": " + total + " VNĐ")
        );
    }
}
