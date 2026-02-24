import java.util.HashMap;
import java.util.Map;

public class MapContainer {

    private Map<Class<?>, Object> objects = new HashMap<>();

    public <T> void register(Class<T> type, T instance) {
        objects.put(type, instance);
    }

    public <T> T get(Class<T> type) {
        return type.cast(objects.get(type));
    }
}
