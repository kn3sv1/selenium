package utils;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryParserTest {

    @Test
    void shouldParseQueryParamsCorrectly() throws Exception {
        // given
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestURI())
                .thenReturn(new URI("http://localhost:8080/test?name=John&age=30"));

        QueryParser parser = new QueryParser();

        // when
        Map<String, String> result = parser.parse(exchange);

        // then
        assertEquals(2, result.size());
        assertEquals("John", result.get("name"));
        assertEquals("30", result.get("age"));
    }

    @Test
    void shouldReturnEmptyMapWhenNoQuery() throws Exception {
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestURI())
                .thenReturn(new URI("http://localhost:8080/test"));

        QueryParser parser = new QueryParser();

        Map<String, String> result = parser.parse(exchange);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldHandleEmptyValue() throws Exception {
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestURI())
                .thenReturn(new URI("http://localhost:8080/test?name="));

        QueryParser parser = new QueryParser();

        Map<String, String> result = parser.parse(exchange);

        assertEquals("", result.get("name"));
    }
}