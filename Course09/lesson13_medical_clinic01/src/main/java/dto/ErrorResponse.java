package dto;

import java.util.Map;

public class ErrorResponse {
    public static final String ERROR_VALIDATION = "validation";
    public static final String ERROR_MAPPING = "mapping";
    public static final String ERROR_NOT_FOUND = "not_found";

    public String message;
    public Map<String, String> errors;

    /**
     * Jackson requires a default constructor to deserialize JSON into this class.
     */
    public ErrorResponse() {
    }

    public ErrorResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
