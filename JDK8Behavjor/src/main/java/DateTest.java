import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Java8 全新日期API
 * LocalDate ：表示日期，包含年月日，格式为 2019-10-16
 * LocalTime ：表示时间，包含时分秒，格式为 16:38:54.158549300
 * LocalDateTime ：表示日期时间，包含年月日，时分秒，格式为 2018-09-06T15:33:56.750
 * DateTimeFormatter ：日期时间格式化类。
 * Instant：时间戳，表示一个特定的时间瞬间。
 * Duration：用于计算2个时间(LocalTime，时分秒)的距离
 * Period：用于计算2个日期(LocalDate，年月日)的距离
 * ZonedDateTime ：包含时区的时间
 * <p>
 * 温馨提示：Java中使用的历法是ISO 8601日历系统（世界民用历法/公历）。平年有365天，闰年是366
 * Java8 支持其他的历法：
 * ThaiBuddhistDate：泰国佛教历
 * MinguoDate：中华民国历
 * JapaneseDate：日本历
 * HijrahDate：伊斯兰历
 */
public class DateTest {

    public static void main(String[] args) {
        operateLocalDate();
        operateLocalTime();
        operateLocalDateTime();
        operateTimeDiffAndComp();
        operateFormatAndParse();
        operateInstant();
        operateTimeDiff();
        operateTimeSetting();
        operateTimeZone();
    }

    /**
     * LocalDate的常用操作
     */
    public static void operateLocalDate() {
        // 使用LocalDate 创建指定的日期
        LocalDate date1 = LocalDate.of(2022, 5, 17);
        System.out.println("date1 = " + date1);

        // 使用LocalDate  获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        // 使用LocalDate, 获取年、月、日
        System.out.println("年：" + now.getYear());
        System.out.println("月：" + now.getMonth().getValue());
        System.out.println("日：" + now.getDayOfMonth());
        System.out.println("星期：" + now.getDayOfWeek().getValue());
    }

    /**
     * LocalTime的常用操作
     */
    public static void operateLocalTime() {
        // 1.使用LocalTime,创建指定的时间
        LocalTime time = LocalTime.of(5, 26, 33, 23145);
        System.out.println(time);
        // 2.使用LocalTome 获取当前的时间
        LocalTime now = LocalTime.now();
        System.out.println(now);
        // 3.使用LocalTime 获取时、分、秒、毫秒
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());
    }

    /**
     * LocalDateTime 常用操作
     */
    public static void operateLocalDateTime() {
        // 使用LocalDateTime 获取指定时间
        LocalDateTime dateTime = LocalDateTime.of(2022, 05, 17, 12, 12, 33, 213);
        System.out.println(dateTime);

        // 使用LocalDateTime 获取当前的日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 使用LocalDateTime, 获取日期信息
        System.out.println(now.getYear());
        System.out.println(now.getMonth().getValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfWeek().getValue());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());
    }

    /**
     * 日期时间修改和比较
     */
    public static void operateTimeDiffAndComp() {
        /**
         * LocalDateTime 时间修改
         */
        LocalDateTime now = LocalDateTime.now();
        System.out.println("两天后:" + now.plusDays(2));
        System.out.println("10年后:" + now.plusYears(10));
        System.out.println("6个月后:" + now.plusMonths(6));

        System.out.println("10年前=" + now.minusYears(10));
        System.out.println("半年前=" + now.minusMonths(6));
        System.out.println("一周前=" + now.minusDays(7));

        /**
         * LocalDateTime 时间比较
         */
        LocalDate date1 = LocalDate.of(2022, 1, 2);
        LocalDate date2 = LocalDate.of(2022, 1, 3);
        System.out.println(date1.isAfter(date2));//false
        System.out.println(date1.isBefore(date2));//true
        System.out.println(date1.isEqual(date2));//false
    }

    /**
     * 时间格式化和解析
     */
    public static void operateFormatAndParse() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前日期:" + now.format(dateTimeFormatter));

        String dateStr = "1997-05-06 22:45:16";
        LocalDateTime parse = LocalDateTime.parse(dateStr, dateTimeFormatter);
        System.out.println("字符串日期:" + parse);
    }

    /**
     * Instant (时间戳/时间线）:用于统计当前时间距离1970年1月1日 00:00:00的秒或纳秒数
     */
    public static void operateInstant() {
        Instant now = Instant.now();
        System.out.println("距离1970年纳秒数:" + now.getNano());

        // 统计方法执行时间
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Instant after = Instant.now();
        System.out.println("距离1970年纳秒数:" + after.getNano());

        System.out.println("方法耗时：" + (after.getNano() - now.getNano()));

    }

    /**
     * Duration：用来计算两个时间差(LocalTime)
     * Period:用来计算两个日期差(LocalDate)
     */
    public static void operateTimeDiff() {
        // 计算时间差
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(14, 26, 33);

        //通过Duration来计算时间差
        Duration duration = Duration.between(now, time);
        System.out.println("间隔天数:" + duration.toDays());
        System.out.println("间隔小时:" + duration.toHours());
        System.out.println("间隔分钟:" + duration.toMinutes());
        System.out.println("间隔毫秒:" + duration.toMillis());

        //计算日期差
        LocalDate nowDate = LocalDate.now();
        LocalDate date = LocalDate.of(2001, 12, 5);

        Period period = Period.between(date, nowDate);
        System.out.println("间隔年:" + period.getYears());
        System.out.println("间隔月:" + period.getMonths());
        System.out.println("间隔天:" + period.getDays());
    }

    /**
     * TemporalAdjuster:时间校正器
     * TemporalAdjusters:通过该类静态方法提供了大量的常用TemporalAdjuster的实现。
     */
    public static void operateTimeSetting() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now:" + now);

        /**
         * 通过时间校正器，跳转至指定时间。(功能:跳转至下一个月的第一天）
         */
        LocalDateTime next = now.with((temporal) -> {
            LocalDateTime dateTime = (LocalDateTime) temporal;
            LocalDateTime nextMonth = dateTime.plusMonths(1).withDayOfMonth(1);
            return nextMonth;
        });
        System.out.println("next:" + next);
    }

    /**
     * 时区的日期时间类分别为：ZonedDate、ZonedTime、ZonedDateTime。
     * 其中每个时区都对应着 ID，ID的格式为 “区域/城市” 。例如：Asia/Shanghai 等。
     * ZoneId：该类中包含了所有的时区信息
     */
    public static void operateTimeZone() {
        // 遍历所有时区Id
        ZoneId.getAvailableZoneIds().forEach(System.out::println);

        // 获取当前所在时区的时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now =" + now);

        // 获取当前标准时间(格林尼治时间）
        ZonedDateTime bz = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("bz =" + bz);

        // 使用计算机所在时区，创建时间
        ZonedDateTime computerNow=ZonedDateTime.now();
        System.out.println("computerNow =" + computerNow);

        // 使用指定的时区创建日期时间
        ZonedDateTime zoneNow=ZonedDateTime.now(ZoneId.of("America/Marigot"));
        System.out.println("zoneNow="+zoneNow);

    }


}
