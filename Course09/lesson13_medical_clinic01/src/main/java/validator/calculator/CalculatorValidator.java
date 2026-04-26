package validator.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorValidator {
    private Map<String, String> errors = new HashMap<>();
    public Map<String, String> validate(Map<String, String> form) {
        // http://localhost:8080/calculator?a=5&operation=-
        // http://localhost:8080/calculator?b=5&operation=-
        // http://localhost:8080/calculator?a=5&b=5
        // http://localhost:8080/calculator?a=5
        // http://localhost:8080/calculator?b=5


        if (!form.containsKey("a") || form.get("a").isEmpty() || !form.get("a").matches("\\d+")) {
            this.errors.put("a", "Parameter a is required");
        }

        if (!form.containsKey("b") || form.get("b").isEmpty() || !form.get("b").matches("\\d+")) {
            this.errors.put("b", "Parameter b is required");
        } else if (form.get("b").equals("0") && form.containsKey("operation") && form.get("operation").equals("/")) {
            this.errors.put("b", "Parameter b cannot be zero for division");
        }

        if (!form.containsKey("operation")
                || form.get("operation").isEmpty()
                || !List.of("+", "-", "*", "/").contains(form.get("operation"))
        ) {
            this.errors.put("operation", "Operation doesn't exist.");
        }


        //this.errors.put("message", "Message is too short");
        return this.errors;
    }
}
