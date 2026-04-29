package dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Unit 8. Java http server parse JSON with Jackson. Read Notion.
 *
 * used to map request JSON to Java object,
 * we will use this class in controller to map request body to this class,
 * and then we will pass this object to service layer,
 * and service layer will create CatModel object from this DTO and add it to repository.
 */
public class CatCreateRequest {
    public UUID id;
    public String name;
    public int age;
    public String color;
    public boolean vaccinated;
    public Map<String, String> attributes;
    public List<String> favoriteFood;
    public String mood;
    public List<String> feedingTimes;
    public boolean sleeps = false;
}
