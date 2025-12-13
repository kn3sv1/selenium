import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class AppointmentRepository {
    private ArrayList<Appointment> appointments;
    private ObjectMapper mapper;


    public AppointmentRepository() {
        this.appointments = new ArrayList<>();
        this.mapper = new ObjectMapper();
        this.load();
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }


    public void add(Appointment appointment) {
        this.appointments.add(appointment);
        try {
            this.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * load from file JSON and convert to ArrayList
     */
    private void load() {
        // not to throw all time exception here we just hided
        try {
        String json = this.loadFromDatabase();
        this.appointments = mapper.readValue(
                json,
                new TypeReference<ArrayList<Appointment>>() {}
        );
        // PUT HERE BREAKPOINT
        System.out.println(this.appointments.toString());
        //Convert to ArrayList
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * convert ArrayList to JSON and store in file
     */
    public void save() throws IOException {
        // first step print to system out JSON
        // step 2 of refactoring write to file
        String json = this.mapper.writeValueAsString(this.appointments);
        // System.out.println(json);
        this.writeToDatabase(json);
    }

    private void writeToDatabase(String json) throws IOException {
        Path file = Path.of("./database/appointments.json");
        Files.writeString(file, json, StandardCharsets.UTF_8);
    }
    private String loadFromDatabase() throws IOException {
        // todo add logic if no file exists. return empty string!
        Path file = Path.of("./database/appointments.json");
        if (!Files.exists(file)) {
            return "";
        }


        String json = Files.readString(file, StandardCharsets.UTF_8);

        return json;
    }
}
