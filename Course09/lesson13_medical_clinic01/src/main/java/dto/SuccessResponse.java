package dto;

import java.util.Map;

public class SuccessResponse {
    public static final String SUCCESS_CREATED = "created";
    public static final String SUCCESS_UPDATED = "updated";
    public static final String SUCCESS_DELETED = "deleted";

    public String message;
    public Map<String, String> data;

    public SuccessResponse(String message, Map<String, String> data) {
        this.message = message;
        this.data = data;
    }
}
