public class Patient {
    private String id;
    private String name;
    private Doctor doctor;

    public Patient(String id, String name, Doctor doctor) {
        this.id = id;
        this.name = name;
        this.doctor = doctor;
    }

    public String  getId() {
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
