package model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import dto.CatRequest;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(
        fieldVisibility = ANY,
        getterVisibility = NONE,
        isGetterVisibility = NONE
)
public class Cat {
    private UUID id;
    private String name;
    private int age;
    private String color;
    private boolean vaccinated;
    private Map<String, String> attributes;
    private List<String> favoriteFood;
    private String mood;
    private List<String> feedingTimes;
    private boolean sleeps = false;

    public Cat() {
        this.attributes = new HashMap<>();
        this.feedingTimes = new ArrayList<>();
    }

    public Cat(
            UUID id,
            String name,
            int age,
            String color,
            boolean vaccinated,
            Map<String, String> attributes,
            List<String> favoriteFood,
            String mood
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.vaccinated = vaccinated;
        // Returns an unmodifiable Map containing the elements of the given Collection
        this.attributes = Map.copyOf(attributes);
        //this.attributes = attributes;
        // Returns an unmodifiable List containing the elements of the given Collection
        this.favoriteFood = List.copyOf(favoriteFood);
        //this.favoriteFood = favoriteFood;
        this.mood = mood;

        // initialize feedingTimes as empty list, because we will add feeding times when we feed cat, and we don't want to have null pointer exception.
        this.feedingTimes = new ArrayList<>();
    }

    private void addFeedingTime(String time) {
        this.feedingTimes.add(time);
    }

    public void sleep() {
        this.sleeps = true;
    }

    public void wakeUp() {
        this.sleeps = false;
    }

    /**
     * feed cat only if it's not sleeping, otherwise throw exception.
     * addFeedingTime - better not to use publicly because we will have logical error that cat sleeps, and we feed it.
     * TODO::: refactor List<String> feedingTimes to Map<String, String> food - time as key, food as value.
     */
    public void eatAtTime(String time) {
        if (!this.sleeps) {
            this.addFeedingTime(time);
        } else {
            throw new RuntimeException("Cat is sleeping, can't feed now");
        }
    }

    public String speak() {
        return "Meow: " + this.name;
    }

    public String cry(String to) {
        return "Cry: " + this.name + " to " + to;
    }

    /**
     * if mood contains words "bite", "scratch", "attack" or "aggressive" (case-insensitive), then cat is aggressive.
     * regex is separate lesson. Here we just use for business logic and unit test.
     */
    public boolean isAggressive() {
        return this.mood != null &&
                this.mood.matches("(?i).*\\b(bite|scratch|attack|aggressive)\\b.*");
    }

    /**
     * if attributes contain key "idDoor" and its value contains words "indoor", "apartment" or "house cat" (case-insensitive), then cat is indoor.
     */
    public boolean isIndoorCat() {
        return this.attributes.get("idDoor") != null &&
                this.attributes.get("idDoor").matches("(?i).*\\b(indoor|apartment|house cat)\\b.*");
    }

    public void update(CatRequest dto) {
        // here we validate to allow or not allow whatever we want.
        this.name = dto.name;
        this.age = dto.age;
        this.color = dto.color;
        this.vaccinated = dto.vaccinated;
        // we don't want to allow to change attributes and favorite food from outside of class, because it's final and we return unmodifiable map and list.
        this.attributes = Map.copyOf(dto.attributes);
        this.feedingTimes = List.copyOf(dto.feedingTimes);
        this.favoriteFood = List.copyOf(dto.favoriteFood);
        this.mood = dto.mood;

    }

    // GETTERS

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    /**
     * Nobody will be able to change attributes of cat from outside of class, because we return unmodifiable map.
     */
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    /**
     * Nobody will be able to change favorite food of cat from outside of class, because we return unmodifiable list.
     */
    public List<String> getFavoriteFoods() {
        return Collections.unmodifiableList(favoriteFood);
    }

    public String getMood() {
        return mood;
    }

    public List<String> getFeedingTimes() {
        return feedingTimes;
    }

    public boolean isSleeps() {
        return sleeps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
