import java.io.IOException;
import java.util.Arrays;

public class CatSerializer {
    private DataBase dataBase;
    private Cat cat;

    public CatSerializer(DataBase dataBase, Cat cat) {
        this.dataBase = dataBase;
        this.cat = cat;
    }


    /**
     *  we need to join all properties from Cat to string using verticle se
     */
    public void serialize() throws IOException {
        String[] n = convertIntToString(this.cat.getFavoriteNumbers());

        String s = this.cat.getName()
                + "|" + this.cat.getAge()
                + "|" + this.cat.isDomestic()
                + "|" + this.cat.getPrice()
                + "|" + String.join(",", this.cat.getHobbies())
                + "|" + String.join(",", n);

        System.out.println(s);
        this.dataBase.writeToFile("cat-serialized.txt", s);

    }

    public void desirialize() throws IOException {
        String s = this.dataBase.readFromFile("cat-serialized.txt");
        System.out.println(s);



        // don't forget to trim everything after like \n - in the end of line
        String[] data = s.split("\\|");
        System.out.println(Arrays.toString(data));

        String[] hobbies = data[4].trim().split(",");
       // int[] numbers = data[5].trim().split(",");

        int[] n = convertStringToInt(data[5].trim().split(","));

        Cat ginger = new Cat(
                data[0].trim(),
                Integer.parseInt(data[1].trim()),
                Boolean.parseBoolean(data[2].trim()),
                Double.parseDouble(data[3].trim()),
                hobbies,
                // new String[]{}
                n
        );

        //the toString() method of the Cat object is automatically called when passed as an argument to the println method.
        System.out.println(ginger);
    }

    private String[] convertIntToString(int[] n) {
        String[] s = new String[n.length];
        for (int i = 0; i < n.length; i++) {
            // convert number to string
            s[i] = String.valueOf(n[i]);
        }
        return s;
    }

    private int[] convertStringToInt(String[] n) {
        int[] s = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            // convert number to string
            //this is mistake - never do this
            //s[i] = Integer.parseInt(String.valueOf(n[i]));

            //convert string to number
            s[i] = Integer.parseInt(n[i]);
        }
        return s;
    }
}
