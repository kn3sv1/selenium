import java.io.*;

// implements Serializable
class Employee implements Serializable {
    private String name;
    private transient int salary; // transient won't be serialized automatically

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    // Custom serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Serialize non-transient fields
        oos.writeInt(salary);     // Manually serialize transient field
    }

    // Custom deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();  // Deserialize non-transient fields
        salary = ois.readInt();   // Manually deserialize transient field
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}