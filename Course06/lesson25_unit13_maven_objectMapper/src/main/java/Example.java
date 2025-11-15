import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Example {
    public static void main(String[] args) throws Exception {
        NewsItem item = new NewsItem(
                1,
                "Hello",
                "Text",
                "Description",
                "image.jpg",
                LocalDateTime.now(),
                5,
                "News"
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // 2. Create JavaTimeModule and add custom serializer
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module); // support Java 8+ date/time
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO-8601 format


        // Convert Java object to JSON
        String json = mapper.writeValueAsString(item);
        System.out.println(json);
    }
}
