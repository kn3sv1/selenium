package utils;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class QueryParser {
    public Map<String, String> parse(HttpExchange exchange) {
        URI uri = exchange.getRequestURI();
        String query = uri.getQuery(); // e.g. "name=John&age=30"

        return parseQuery(query);
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;

        for (String pair : query.split("&")) {
            String[] entry = pair.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
