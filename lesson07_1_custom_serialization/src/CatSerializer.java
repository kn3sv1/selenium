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
        String s = this.cat.getName()
                + "|" + this.cat.getAge()
                + "|" + this.cat.isDomestic()
                + "|" + this.cat.getPrice();

        System.out.println(s);
        this.dataBase.writeToFile("cat-serialized.txt", s);

    }

    public void desirialize() throws IOException {
        String s = this.dataBase.readFromFile("cat-serialized.txt");
        System.out.println(s);



        // don't forget to trim everything after like \n - in the end of line
        String[] data = s.split("\\|");
        System.out.println(Arrays.toString(data));


        Cat ginger = new Cat(data[0].trim(), Integer.parseInt(data[1].trim()), Boolean.parseBoolean(data[2].trim()), Double.parseDouble(data[3].trim()));

        System.out.println(ginger);
    }
}
