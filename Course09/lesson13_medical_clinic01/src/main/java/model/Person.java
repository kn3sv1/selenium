package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import dto.PersonRequest;

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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void update(PersonRequest dto) {
        this.name = dto.name;
        this.address = dto.address;
        this.phoneNumber = dto.phoneNumber;
    }
}
