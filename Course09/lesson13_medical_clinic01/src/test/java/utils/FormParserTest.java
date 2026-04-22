package utils;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class FormParserTest {
    private final FormParser parser = new FormParser();

    // basic test
    @Test
    void shouldParseSimpleKeyValuePairs() {
        String body = "name=Angie&age=42";

        Map<String, String> result = parser.parse(body);

        assertEquals(2, result.size());
        assertEquals("Angie", result.get("name"));
        assertEquals("42", result.get("age"));
    }

    // test URL decoding
    @Test
    void shouldDecodeUrlEncodedValues() {
        String body = "name=Angie%20Neo&color=light%20green";

        Map<String, String> result = parser.parse(body);

        assertEquals("Angie Neo", result.get("name"));
        assertEquals("light green", result.get("color"));
    }

    // ignore invalid pairs
    @Test
    void shouldIgnoreInvalidPairs() {
        String body = "name=Maria&invalidpair&age=20";

        Map<String, String> result = parser.parse(body);

        assertEquals(2, result.size());
        assertFalse(result.containsKey("invalidpair"));
    }

    // empty input
    @Test
    void shouldHandleEmptyInput() {
        String body = "";

        Map<String, String> result = parser.parse(body);

        assertTrue(result.isEmpty());
    }

    // missing value
    @Test
    void shouldIgnoreKeyWithoutValue() {
        String body = "name=&age=30";

        Map<String, String> result = parser.parse(body);

        assertNull(result.get("name"));
        assertEquals("30", result.get("age"));
    }

    // duplicate keys (override behaviour)
    @Test
    void shouldOverrideDuplicateKeys() {
        String body = "name=Jack&name=Jane";

        Map<String, String> result = parser.parse(body);

        assertEquals(1, result.size());

        // last one wins - overrides the first one.
        assertEquals("Jane", result.get("name"));
    }

    // null input
    // improve the method in FormParser class to handle null safely.
    @Test
    void shouldThrowExceptionWhenBodyIsNull() {
        String body = "name=&age=30";

        Map<String, String> result = parser.parse(body);

        assertThrows(NullPointerException.class, () -> parser.parse(result.get("name")));
    }

    // another way
//    @Test
//    void shouldThrowExceptionWhenBodyIsNull() {
//        assertThrows(NullPointerException.class, () -> parser.parse(null));
//    }
}
