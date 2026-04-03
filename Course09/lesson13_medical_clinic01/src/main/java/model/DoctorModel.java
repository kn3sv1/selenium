package model;

import java.util.UUID;

public class DoctorModel {
    public static final UUID UUID_1 = UUID.randomUUID();
    public static final UUID UUID_2 = UUID.randomUUID();
    public static final UUID UUID_3 = UUID.randomUUID();
    public static final UUID UUID_4 = UUID.randomUUID();
    public static final UUID UUID_5 = UUID.randomUUID();
    public static final UUID UUID_6 = UUID.randomUUID();

    private UUID id;
    private String firstName;
    private String lastName;
    private String profession;
    private String photo;

    public DoctorModel() {

    }

    public DoctorModel(UUID id, String firstName, String lastName, String profession, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
        this.photo = photo;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
