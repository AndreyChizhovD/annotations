import org.json.JSONObject;
import org.reflections.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class JsonSerializer<T> {

    private final Set<Field> publishedFields = new HashSet<>();

    public JsonSerializer(Class<T> serializedClass) {
        var fields = ReflectionUtils.getAllFields(serializedClass);
        for (var field : fields) {
            if (field.getAnnotation(Published.class) != null) {
                publishedFields.add(field);
            }
        }
    }

    public JSONObject serialize(T o) {
        JSONObject result = new JSONObject();
        for (var publishedField : publishedFields) {
            try {
                publishedField.setAccessible(true);
                result.put(publishedField.getName(), publishedField.get(o).toString());
            } catch (Exception ex) {
                System.out.println("Access to field failed " + ex.getMessage());
            }
        }
        return result;
    }
}
