import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Unit6UniqueWord {
    public void run() {
        // PART 1: CONVERT TEXT TO STRUCTURED DATA WITHOUT SPACES COMMA AS ARRAY LIST OF WORDS
        String text = "Hello from Anita and Anita, and from Angie from fro";
        ArrayList<String> indexes = new ArrayList<>();
        String word = "";
        for (int i = 0; i < text.length(); i++) {
            if (
                    text.charAt(i) == ' '
                            || text.charAt(i) == ','
                            || text.charAt(i) == '.'
                            || text.charAt(i) == '?'
                            || text.charAt(i) == '!'
            ) {
                // each word before space we add to array list, so we fill this variable letter
                // by letter and reset variable to empty for the next word.
                 if (!word.isEmpty()) {
                     indexes.add(word);
                 }
                word = "";
            } else {
                word = word + text.charAt(i);
            }
        }

        // the to String method adds comma after eac word
        System.out.println(Arrays.toString(indexes.toArray()));


        // PART 2: USE ARRAY LIST OF WORDS FROM FIST PART TO COMPLETE THIS PART

        // find amount of unique words and print on screen
        // in this HashMap String is unique words and Integer is amount
        HashMap<String, Integer> uniqueWords = new HashMap<>();

        // Step 1: what I do with ArrayList 99% of cases?
        // 99% of cases we start from for loop and go through each element
        for (int i = 0; i < indexes.size(); i++) {
            // 99% of cases what we do inside for?
            // we write if
            // we need to populate HashMap with each word.
            // if word - already exists in HashMap? We should add + 1
            if (uniqueWords.containsKey(indexes.get(i))) {
                String key = indexes.get(i);
                Integer value = uniqueWords.get(key);
                uniqueWords.put(key, value + 1);
            } else {
                // if key doesn't exist in Map? We put value inside:
                String key = indexes.get(i);
                uniqueWords.put(key, 1);
            }
        }
        // not to print many times we print outside of for loop
        System.out.println(uniqueWords);

    }
}
