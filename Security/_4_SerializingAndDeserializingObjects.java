import java.io.*;

public class _4_SerializingAndDeserializingObjects {

    public static void main(String[] args) throws Exception {

        var file = new File("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer\\Security\\_0_test.txt");
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(new Employee("Abdullah", "ABD3303", 18));
        }
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Employee employee = (Employee) in.readObject();
            System.out.println(employee.ssn);
        } catch (Exception e) { }





//        employee.writeObject(new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file))));
//        employee.readObject(new ObjectInputStream(new BufferedInputStream(new FileInputStream(file))));

    }

}

class Employee implements Serializable {
    public String name;
    public String ssn;
    private int age;

    private static final ObjectStreamField[] serialPersistentFields =
            { new ObjectStreamField("name", String.class),
              new ObjectStreamField("ssn", String.class)   };

    public Employee(String name, String ssn, int age) {
        this.name = name;
        this.ssn = ssn;
        this.age = age;
    }

    private static String encrypt(String input) {
        return input.concat("HASHED");
    }

    private static String decrypt(String input) {
        return input.replace("HASHED", "");
    }

    public void writeObject(ObjectOutputStream out) throws Exception {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("name", name);
        fields.put("ssn", encrypt(ssn));
        out.writeFields();
    }

    public void readObject(ObjectInputStream in) throws Exception {
        ObjectInputStream.GetField fields = in.readFields();
        this.name = (String) fields.get("name", null);
        this.ssn = (String) fields.get("ssn", null);
    }
}
