package cn.fanyetu.java8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {

    /**
     * ZonedDate、ZonedTime、ZonedDateTime
     */
    @Test
    public void test6(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();// 获取所有支持的时区信息

        zoneIds.forEach(System.out::println);

        LocalDateTime ldt1 = LocalDateTime.now(ZoneId.of("America/Costa_Rica"));
        System.out.println(ldt1);

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Chongqing"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Chongqing"));
        System.out.println(zdt);
    }

    /**
     * DateTimeFormatter 日期时间格式化
     */
    @Test
    public void test5(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        System.out.println(ldt.format(dtf));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        String str = dtf2.format(ldt);
        System.out.println(str);

        LocalDateTime ldt2 = LocalDateTime.parse(str, dtf2);
        System.out.println(ldt2);
    }

    /**
     * TemporalAdjuster 时间校正器
     */
    @Test
    public void test4() {
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        // 调整到本月的10号
        LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
        System.out.println(ldt2);

        // 调整到下个周日
        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义的调整，调整到下个工作日
        LocalDateTime ldt5 = ldt1.with((t) -> {
            LocalDateTime ldt4 = (LocalDateTime) t;

            DayOfWeek dow = ldt4.getDayOfWeek();
            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    /**
     * Duration 时间之间的间隔
     * Period 日期之间的间隔
     */
    @Test
    public void test3() throws InterruptedException {
        // Duration
        Instant inst1 = Instant.now();

        Thread.sleep(1000);

        Instant inst2 = Instant.now();

        Duration duration = Duration.between(inst1, inst2);
        System.out.println(duration); // ISO-8601的展示方式
        System.out.println(duration.toMillis());

        // Period
        LocalDate ld1 = LocalDate.now();

        LocalDate ld2 = LocalDate.of(2019, 12, 20);

        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println(period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "日");

    }

    /**
     * Instant 时间戳
     */
    @Test
    public void test2() {
        Instant inst1 = Instant.now();
        System.out.println(inst1); // 这里显示的是格林威治时间，如果要显示本地时间需要对其进行时区的偏移
        System.out.println(inst1.toEpochMilli());

        OffsetDateTime odt = inst1.atOffset(ZoneOffset.ofHours(8));// 偏移8个
        System.out.println(odt);

        Instant inst2 = Instant.ofEpochSecond(1000);
        System.out.println(inst2);
        System.out.println(inst2.toEpochMilli());
    }

    /**
     * LocalDate,LocalTime,LocalDateTime
     */
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2017, 12, 28, 13, 56, 30);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusMonths(10);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
    }
}
