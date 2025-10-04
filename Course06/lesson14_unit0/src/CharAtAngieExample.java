import java.util.ArrayList;

public class CharAtAngieExample {
    public void run() {
        // replace the word "Fluffy" at the last occurrence
        String text = "I have a cat called Fluffy that looks like a Fluffy. He doesn't like to be called Fluffy but Fluffy is his name.";
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {

            int maxLimit = i+5;
            // first validate that i doesn't go more than length of all sentence
            if(
                    maxLimit < text.length()
                    && text.charAt(i) == 'F'
                    && text.charAt(i+1) == 'l'
                    && text.charAt(i+2) == 'u'
                    && text.charAt(i+3) == 'f'
                    && text.charAt(i+4) == 'f'
                    && text.charAt(maxLimit) == 'y'

            ) {
                indexes.add(i);
            }
        }

        int firstIndexes = indexes.get(1);
        System.out.println(firstIndexes);

        String part1 = text.substring(0, firstIndexes);
        String part2 = text.substring(firstIndexes + "Fluffy".length());

        String result = part1 + "Fluffball" + part2;
        System.out.println("Result: " +result);
    }
}
