import java.io.*;

public class MySerialization {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("John Doe", 5000);

        // Serialize
        //FileOutputStream means where it will serialize stream of bytes to which file.
        FileOutputStream fos = new FileOutputStream("employee.ser");
        // ObjectOutputStream converts object to stream of bytes, argument takes file - where to write this
        // stream of bytes.
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(emp);
        oos.close(); //closes FileOutputStream

        //FileOutputStream writes stream of bytes to file
        // ObjectOutputStream converts object to stream of bytes.
        // oos.writeObject(emp); - This method converts object that we pass to stream of bytes and writes to FileOutputStream


        // Reverse steps:
        // Deserialize
        //FileInputStream means from which file it will read stream of bytes and deserialize them.
        FileInputStream fis = new FileInputStream("employee.ser");
        //ObjectInputStream - opposite from stream to object.
        ObjectInputStream ois = new ObjectInputStream(fis);
        // casts the stream of bytes to class type Employee
        Employee emp2 = (Employee) ois.readObject();
        ois.close();

        //  public String toString() {
        System.out.println("Deserialized Employee: " + emp2);
    }
}
