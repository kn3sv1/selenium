public class Unit1HtmlHighlighter {
    public static void main(String[] args) {
        String htmlInput = "<p>Contact us at 123-456-7890 or visit our office.</p>";
        String highlighted = highlightPhoneNumbers(htmlInput);
        System.out.println(highlighted);


        // debug only function
        String htmlInput2 = "123-456-7890";
        Boolean isDigit = isPhoneNumber(htmlInput2, 0);
        System.out.println(isDigit);
    }

    public static String highlightPhoneNumbers(String html) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < html.length()) {
            // Check if we have a potential phone number starting here
            if (i + 12 <= html.length() && isPhoneNumber(html, i)) {
                // Wrap the phone number in a span tag
                String phone = html.substring(i, i + 12);
                result.append("<span class=\"highlight\">").append(phone).append("</span>");
                i += 12; // Skip past the phone number
            } else {
                // Append current character as-is
                result.append(html.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    // Check if substring at position i is in the form XXX-XXX-XXXX (basic check)
    public static boolean isPhoneNumber(String text, int index) {
        if (index + 12 > text.length()) return false;

        for (int j = 0; j < 12; j++) {
            char c = text.charAt(index + j);
            if (j == 3 || j == 7) {
                if (c != '-') return false;
            } else {
                if (!Character.isDigit(c)) return false;
            }
        }
        return true;
    }
}
