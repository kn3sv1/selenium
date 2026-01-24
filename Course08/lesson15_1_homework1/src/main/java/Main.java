import java.time.DayOfWeek;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DayOfWeek2 a1 = DayOfWeek2.FRIDAY;
        DayOfWeek2 b1 = DayOfWeek2.FRIDAY;
        System.out.println(a1 == b1);
        System.out.println(DayOfWeek2.FRIDAY);

        String a = "Angie";
        String b = "Angie";
        System.out.println(a == b);

        System.out.println("Work on mistakes");

        DailyRoutineService dailyRoutineService = new DailyRoutineService(new DailyRoutineRepository());
        //dailyRoutineService.checkAndUpdateDailyRoutinesStatus();

        Scanner scanner = new Scanner(System.in);
        DayOfWeek day = null;

        while (day == null) {
            System.out.print("Enter day of week (MONDAY–FRIDAY): ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                day = DayOfWeek.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid day. Try again.");
            }
        }

        System.out.println("You chose: " + day);

        List<DailyRoutine> dailyRoutines = dailyRoutineService.getDailyRoutines(day);

        dailyRoutineService.getAmountOfTimes(dailyRoutines);

        System.out.println();
        System.out.println("Employees and their salary:");

        var employee1 = new Person("Jack", 2000);
        var employee2 = new Person("Jane", 1500);
        var employee3 = new Person("Maria", 1300);

        var paymentEmployee1 = new Payment(employee1);
        var paymentEmployee2 = new Payment(employee2);
        var paymentEmployee3 = new Payment(employee3);


        employee1.setThirteenSalary(2000);
        paymentEmployee1.pay();

        paymentEmployee2.fail();

        employee1.shoInfo();
        System.out.println("Payment information:");
        paymentEmployee1.showInfo();
        PaymentMessagePrinter.printMessage(paymentEmployee1.getStatus());
        System.out.println("------------------");
        employee2.shoInfo();
        System.out.println("Payment information:");
        paymentEmployee2.showInfo();
        PaymentMessagePrinter.printMessage(paymentEmployee2.getStatus());
        System.out.println("------------------");
        employee3.shoInfo();
        System.out.println("Payment information:");
        paymentEmployee3.showInfo();
        PaymentMessagePrinter.printMessage(paymentEmployee3.getStatus());
    }
}
