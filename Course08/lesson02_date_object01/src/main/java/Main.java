import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws ParseException {
        //angieBirthDay();
        //angieBirthDayModern();
        //homework1();
        //homework2();
        //homework3();
        //homework4();
        //homework5();
        //homework8();
        homework9();
    }

    private static void homework9() {
        User user = new User("Angie", "Neophytou", LocalDate.of(1984, 1, 28));
        LocalDate currentDate = LocalDate.of(2026, 1, 6);
        int age = user.getAge(currentDate);
        int daysUntilBirthday = user.getDaysUntilNextBirthday(currentDate);
        System.out.println(user.getFirstName() + " " + user.getLastName() + " is " + age + " years old.");
        System.out.println("Days until next birthday: " + daysUntilBirthday);
    }

    private static void homework8() {
        BannerRepository bannerRepository = new BannerRepository();
        //Instant now = Instant.now();
        Instant now = Instant.parse("2024-11-29T00:00:00Z");
        System.out.println("Current time (UTC): " + now);
        System.out.println("Active banners at current time:");
        for (Banner banner : bannerRepository.findBy(now)) {
            System.out.println("- " + banner.getName() + ": " + banner.getContent());
        }
    }

    private static void homework5() {
        Instant instant = Instant.now();
        System.out.println(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        String formatted = formatter.format(instant);
        System.out.println(formatted);
    }

//    public LocalDateTime getStartTimeLocal(Instant startTime) {
//        return LocalDateTime.ofInstant(startTime, ZoneId.systemDefault());
//    }

    /**
     * user submitted "2026-03-15 14:30" in HTML form without time zone info
     * we need to convert it to Instant to store in database
     * and then retrieve it from database and convert back to local time zone to show in HTML form
     */
    private static void homework4() {
        // Suppose we submit a form with date and time in local time zone
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // we parsed string to LocalDateTime without time zone info
        LocalDateTime doctorAppointment = LocalDateTime.parse("2026-03-15 14:30", formatter);
        Instant instant = doctorAppointment.atZone(ZoneId.systemDefault()).toInstant();
        // in UTC - London time zone it will be 14:30 - 2 hours = 12:30
        System.out.println("Converted back to Instant (UTC): " + instant); // 2026-03-15T12:30:00Z
        // now we can store instant in database

        // part 2 - retrieve from database and convert to local time zone to show in HTML form
        Instant retrievedFromDb = instant; // suppose we retrieved the same instant from database
        LocalDateTime localDateTime = LocalDateTime.ofInstant(retrievedFromDb, ZoneId.systemDefault());
        System.out.println("Converted back to LocalDateTime in local time zone: " + localDateTime); // 2026-03-15T14:30
    }

    private static void homework3() {
//        Instant now = Instant.now();
//        //Instant addedSeconds = now.plusSeconds(60*60);
//        Instant addedSeconds = now.minusSeconds(60*60);
//        System.out.println(now);
//        System.out.println(addedSeconds);

        System.out.println(ZoneId.getAvailableZoneIds());
        System.setProperty("user.timezone", "Europe/Athens");
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Athens"));
        System.out.println(ZoneId.systemDefault()); // should print Europe/Athens

        // why we used Instant?
        // because Instant is always in UTC time zone and not affected by local time zone
        // and we can easily add seconds to it without worrying about time zones and daylight saving time
        // its very convenient for calculations and in databases we store timestamps in UTC time zone
        Instant now = Instant.now();
        System.out.println("UTC now is " + now);
        long timestampSeconds = now.getEpochSecond();
        System.out.println("timestampSeconds since epoch: " + timestampSeconds); // timestampSeconds since epoch: 1767718799

        // add 10 days: 10 days * 24 hours * 60 minutes * 60 seconds
        // we calculate like this because 30 or 31 days can be in a month so we just add 10 days in seconds
        // and after will convert to ZonedDateTime to see the result in local time zone
        Instant addedDays = Instant.ofEpochSecond(timestampSeconds + 30L * 24 * 60 * 60);


        // show in HTML form in Cyprus time zone. Time zone is important only for displaying to user, but not for calculations
        ZonedDateTime cyprusTime = addedDays.atZone(ZoneId.systemDefault()); // uses Europe/Athens
        System.out.println(cyprusTime);

        ZonedDateTime time = addedDays.atZone(TimeZone.getTimeZone("Europe/Berlin").toZoneId()); // uses Europe/Athens
        System.out.println("berlin time: " + time);

        time = addedDays.atZone(TimeZone.getTimeZone("Europe/Moscow").toZoneId()); // uses Europe/Athens
        System.out.println("Moscow time: " + time);

        time = addedDays.atZone(TimeZone.getTimeZone("America/New_York").toZoneId()); // uses Europe/Athens
        System.out.println("America/New_York time: " + time);
    }

    private static void homework2() {
        // form data for example
        String currentDate = "2026-02-01";
        String showBannerFromDate = "2026-01-01"; // 1st of January 2026
        String showBannerToDate = "2026-01-31"; // 31st of January 2026
        LocalDate current = LocalDate.parse(currentDate);
        LocalDate from = LocalDate.parse(showBannerFromDate);
        LocalDate to = LocalDate.parse(showBannerToDate);
        // compare dates
        if ((current.isEqual(from) || current.isAfter(from)) &&
                (current.isEqual(to) || current.isBefore(to))) {
            System.out.println("Show Banner");
        } else {
            System.out.println("Do not show Banner");
        }
    }

    private static void homework1() {
        // form data for example
        String currentDate = "2026-01-07";
        String showBannerFromDate = "2026-01-01"; // 1st of January 2026
        String showBannerToDate = "2026-01-31"; // 31st of January 2026
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // parse all dates to Date objects for comparison as Date not as strings
            // because string comparison is different from date comparison
            // because Date is calendar aware and string is not
            // because formatter can parse string to Date object
            Date current = formatter.parse(currentDate);
            Date from = formatter.parse(showBannerFromDate);
            Date to = formatter.parse(showBannerToDate);
            // compare dates
            if (current.compareTo(from) >= 0 && current.compareTo(to) <= 0) {
                System.out.println("Show Banner");
            } else {
                System.out.println("Do not show Banner");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private static void angieBirthDayModern() {
        // generate from 1985-01-28
        // Z - means UTC time zone
        Instant angie = Instant.parse("1984-01-28T00:00:00Z");
        String day = "";
        // generate day of week from angie
        int dayOfWeek = angie.atZone(java.time.ZoneId.systemDefault()).getDayOfWeek().getValue();
        switch (dayOfWeek) {
            case 1:
                day = "I was born on Monday";
                break;
            case 2:
                day = "I was born on Tuesday";
                break;
            case 3:
                day = "I was born on Wednesday";
                break;
            case 4:
                day = "I was born on Thursday";
                break;
            case 5:
                day = "I was born on Friday";
                break;
            case 6:
                day = "I was born on Saturday";
                break;
            case 7:
                day = "I was born on Sunday";
                break;
        }

        System.out.println("Angie's birthday is: " + day);
    }

    private static void angieBirthDay() throws ParseException {
        // 1-st way of creating OBJECT Date
        //Date angie = new Date(84, 0, 28); // months are 0-based in Date class, days are 1-based

        // 2-nd way of creating OBJECT Date. ANGIE Prefer parse.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // this what we use to parse string to Date - Appointment Doctor project
        Date angie = formatter.parse("1984-01-28"); // parses string to Date

        String day = "";
        if (angie.getDay() == 0) {
            day = "I was born on Sunday";
        } else if (angie.getDay() == 1) {
            day = "I was born on Monday";
        } else if (angie.getDay() == 2) {
            day = "I was born on Teusday";
        } else if (angie.getDay() == 3) {
            day = "I was born on Wednesday";
        } else if (angie.getDay() == 4) {
            day = "I was born on Thursday";
        } else if (angie.getDay() == 5) {
            day = "I was born on Friday";
        } else if (angie.getDay() == 6) {
            day = "I was born on Saturday";
        }

        System.out.println("Angie's birthday is: " + angie);
        System.out.println("Date of birth: " + day);
    }
}
