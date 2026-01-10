import java.time.DayOfWeek;
import java.util.*;

public class Doctor {
    public static final UUID UUID_1 = UUID.randomUUID();
    public static final UUID UUID_2 = UUID.randomUUID();
    public static final UUID UUID_3 = UUID.randomUUID();
    public static final UUID UUID_4 = UUID.randomUUID();
    public static final UUID UUID_5 = UUID.randomUUID();
    public static final UUID UUID_6 = UUID.randomUUID();
    public static final UUID UUID_7 = UUID.randomUUID();
    public static final UUID UUID_8 = UUID.randomUUID();
    public static final UUID UUID_9 = UUID.randomUUID();
    public static final UUID UUID_10 = UUID.randomUUID();

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Map<DayOfWeek, DoctorSchedule> schedule;
    private List<AbsentDate> absentDates;
    private List<DoctorAppointment> appointments;

    // For Maven/Json parsing - Object Mapper, without this it will throw an error
    public Doctor() {

    }

    public Doctor(UUID id, String firstName, String lastName, Integer age, Map<DayOfWeek, DoctorSchedule> schedule, List<DoctorAppointment> appointments, List<AbsentDate> absentDates) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.schedule = schedule;
        this.appointments = appointments;
        this.absentDates = absentDates;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<DayOfWeek, DoctorSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, DoctorSchedule> schedule) {
        this.schedule = schedule;
    }

    public List<DoctorAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<DoctorAppointment> appointments) {
        this.appointments = appointments;
    }

    public List<AbsentDate> getAbsentDates() {
        return absentDates;
    }

    public void setAbsentDates(List<AbsentDate> absentDates) {
        this.absentDates = absentDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor user = (Doctor) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
