public class Appointment {
    private String id;
    private String name;
    private String phone;
    private String doctor;
    private String reason;

    // JSON library require empty constructor and getters and setters
    public Appointment() {

    }

    public Appointment(String id, String name, String phone, String doctor, String reason) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.doctor = doctor;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Appointment{name='" + name + '}';
    }
}
