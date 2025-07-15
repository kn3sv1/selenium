public class Main {
    private DataBase dataBase;
    private Cat cat;

    public static void main(String[] args) throws Exception {
        DataBase fileStorage = new DataBase();
        Cat ginger = new Cat("Ginger", 3, true, 2000.00);

        CatSerializer s = new CatSerializer(fileStorage, ginger);
        //s.serialize();
        s.desirialize();

    }
}
