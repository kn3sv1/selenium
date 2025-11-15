import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyObject {

    private String name;
    private Map<String, Object> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;

    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + this.name + '\'' +
                ", data=" + this.data +
                '}';
    }

}