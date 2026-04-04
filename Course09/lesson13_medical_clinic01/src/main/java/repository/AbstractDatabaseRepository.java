package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractDatabaseRepository {
    private final ObjectMapper mapper;

    public AbstractDatabaseRepository() {
        this.mapper = new ObjectMapper();
    }

    abstract protected Path getFile();

    /**
     * convert ArrayList to JSON and store in file
     */
    protected void save(Object value)  {
        // first step print to system out JSON
        // step 2 of refactoring write to file
        try {
            String json = this.mapper.writeValueAsString(value);
            Files.writeString(this.getFile(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <T> - all possible types in method. Can be many through comma.
     * @return
     * @param <T>
     */
    public <T> T load(TypeReference<T> typeRef, String type) {
        // not to throw all time exception here we just hided
        try {
            String json = Files.exists(this.getFile()) ?
                    Files.readString(this.getFile(), StandardCharsets.UTF_8) : "";

            // now we will not have exception for array or for object from file.
            if (json.isEmpty() && type.equals("array")) {
                // if file is empty we return empty ArrayList
                return mapper.readValue("[]", typeRef);
            } else if (json.isEmpty() && type.equals("object")) {
                // if file is empty we return empty Object
                return mapper.readValue("{}", typeRef);
            }

            // we need Generic method because we don't return an object or mapper.
            // this class shouldn't know anything about mapper
            return mapper.readValue(json, typeRef);
            // PUT HERE BREAKPOINT
            // System.out.println(this.appointments.toString());
            //Convert to ArrayList
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
