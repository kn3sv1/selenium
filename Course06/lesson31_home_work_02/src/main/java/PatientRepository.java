import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientRepository {
    List<Patient> patients;

    public PatientRepository() {
        this.patients = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.patients.add(new Patient(UUID.randomUUID().toString(), "Angie Neophytou", new Doctor("1", "Andreas", "Dr Andreas Pantazis", null)));
        this.patients.add(new Patient(UUID.randomUUID().toString(), "Roman Satanovski ", new Doctor("2", "George", "Dr George Pashas", null)));
        this.patients.add(new Patient(UUID.randomUUID().toString(), "George Neophytou", new Doctor("3", "Andros", "Dr Andros Charalambous", null)));
        this.patients.add(new Patient(UUID.randomUUID().toString(), "Katerina Diomidous", new Doctor("4", "Andreas", "Dr Andreas Pantazis", null)));
    }

    public List<Patient> getCustomers() {
        return patients;
    }

    public Patient findById(String id) {
        //TODO: refactor after to lambda to have shorter code
        Patient patient = null;
        for(Patient patient1 : this.getCustomers()) {
            if(patient1.getId().equals(id)) {
                patient = patient1;
            }
        }
        return patient;
    }

    public void update(Patient patient) {
        for (int i = 0; i < this.patients.size(); i++) {
            if (this.patients.get(i).getId().equals(patient.getId())) {
                this.patients.set(i, patient);
            }
        }
        this.patients.add(patient);
    }

    public void deletePatient(String id) {
        for(Patient patient : this.patients) {
            if(patient.getId().equals(id)) {
                this.patients.remove(patient);
            }
        }
    }
}
