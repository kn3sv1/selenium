import java.util.ArrayList;

public class CharAtExample {
    public void run() {
        String text = "Hello from Anita and from Angie from fro";
        ArrayList<Integer> indexes = new ArrayList<>();

        //System.out.println(text.charAt(9)); // 'H'
        for (int i = 0; i < text.length(); i++) {
            //System.out.print(text.charAt(i) + " ");

            // let's find if we have word "from"
            int maxLimit = i+3;
            if(
                    maxLimit < text.length()
                    && text.charAt(i) == 'f'
                    && text.charAt(i+1) == 'r'
                    && text.charAt(i+2) == 'o'
                    && text.charAt(maxLimit) == 'm'
            ) {
                System.out.println("We found index start at: " + i);
                indexes.add(i);
            }
        }

        // We have all Indexes - now let's replace before last word "from"
        int indexBeforeLast = indexes.get(indexes.size() - 2);
        System.out.println("indexBeforeLast: " + indexBeforeLast);


        // we split text by index number
        String part1 = text.substring(0, indexBeforeLast);
        // second part not from indexBeforeLast but from indexBeforeLast + 4
        String part2 = text.substring(indexBeforeLast + "from".length());

        System.out.println("part1: '" + part1 + "'");
        System.out.println("part2: '" + part2 + "'");

        String result = part1 + "STUPID" + part2;
        System.out.println("result: '" + result + "'");
    }
}
