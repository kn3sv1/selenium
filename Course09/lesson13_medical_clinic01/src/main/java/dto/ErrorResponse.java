package dto;

import java.util.Map;

public class ErrorResponse {
    public static final String ERROR_VALIDATION = "validation";
    public static final String ERROR_MAPPING = "mapping";
    public static final String ERROR_NOT_FOUND = "not_found";

    public String message;
    public Map<String, String> errors;

    public ErrorResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
