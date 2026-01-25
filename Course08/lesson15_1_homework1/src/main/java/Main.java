import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

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

        // creating individual employee objects and their payment objects
        var employee1 = new Person(Person.UUID_1,"Jack", 2000);
        var employee2 = new Person(Person.UUID_2, "Jane", 1500);
        var employee3 = new Person(Person.UUID_3, "Maria", 1300);

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

        // fetching a list of employees from a repository and creating individual payment objects for each of them
        var listOfEmployees = new PersonRepository();

        Map<String, Consumer<Payment>> paymentActions = Map.of(
                "Sofia", payment -> {
                    payment.getPerson().setThirteenSalary(
                            payment.getPerson().getSalary());
                    payment.pay();
                },
                "Mia", Payment::fail,
                "Harper", Payment::cancel
        );

        for (Person person : listOfEmployees.findAll()) {
            Consumer<Payment> action = paymentActions.get(person.getName());
            if (action == null) continue;

            processPayment(person, action);

            /*
            if (person.getName().equals("Sofia")) {
                person.setThirteenSalary(person.getSalary());
                var PaymentSofia = new Payment(person);
                PaymentSofia.pay();
                person.shoInfo();
                System.out.println("Payment information:");
                PaymentSofia.showInfo();
                PaymentMessagePrinter.printMessage(PaymentSofia.getStatus());
                System.out.println("------------------");
            } else if (person.getName().equals("Mia")) {
                var PaymentMia = new Payment(person);
                PaymentMia.fail();
                person.shoInfo();
                System.out.println("Payment information:");
                PaymentMia.showInfo();
                PaymentMessagePrinter.printMessage(PaymentMia.getStatus());
                System.out.println("------------------");
            } else if (person.getName().equals("Harper")) {
                var PaymentHarper = new Payment(person);
                PaymentHarper.cancel();
                person.shoInfo();
                System.out.println("Payment information:");
                PaymentHarper.showInfo();
                PaymentMessagePrinter.printMessage(PaymentHarper.getStatus());
                System.out.println("------------------");
            }
            */
        }
    }
    private static void processPayment(Person person, Consumer<Payment> action) {
        Payment payment = new Payment(person);

        action.accept(payment);

        person.shoInfo();
        System.out.println("Payment information:");
        payment.showInfo();
        PaymentMessagePrinter.printMessage(payment.getStatus());
        System.out.println("------------------");
    }
}
