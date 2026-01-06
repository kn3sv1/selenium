import java.time.LocalDate;

public class User {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public User() {
    }

    public User(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getAge(LocalDate currentDate) {
        int age = currentDate.getYear() - birthDate.getYear();
        if (currentDate.getDayOfYear() < birthDate.getDayOfYear()) {
            age--;
        }
        return age;
    }
    public int getDaysUntilNextBirthday(LocalDate currentDate) {
        LocalDate nextBirthday = birthDate.withYear(currentDate.getYear());
        if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(currentDate, nextBirthday);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
