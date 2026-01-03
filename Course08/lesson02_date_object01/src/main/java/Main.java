import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        //angieBirthDay();
        //angieBirthDayModern();
        //homework1();
        homework2();
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
