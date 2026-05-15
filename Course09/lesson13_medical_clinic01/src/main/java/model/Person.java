package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;
import java.util.UUID;

@JsonAutoDetect(
        fieldVisibility = ANY,
        getterVisibility = NONE,
        isGetterVisibility = NONE
)

public class Person {
    private UUID id;
    private String name;
    private String address;
    private int phoneNumber;

    public Person() {
    }

    public Person(UUID id, String name, String address, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


}
