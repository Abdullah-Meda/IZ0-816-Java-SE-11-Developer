import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class _4_SerializingAndDeserializingObjects {

    public static void main(String[] args) throws Exception {

        /* Customizing the Serialization process
             - Look at Employee class
             - The serialPersistentFields, readObject() and writeObject() allow custom members to be
               serialized regardless of transient keyword or being a static member */

        /* Pre/Post Serialization Processing
             - Instead of a public constructor which may cause duplicate records, use a private constructor
               and a factory method where you check if the record is already available */

        /* Applying readResolve()
             - When someone reads the data from the disk, it deserializes it into a new object, not the one in
               memory pool.
             - Enter the readResolve() method. When this method is present, it is run after the readObject()
               method and is capable of replacing the reference of the object returned by deserialization
             - If the object is not in memory, it is added to the pool and returned. Otherwise, the version in
               memory is updated, and its reference is returned.
             - Notice that we added the synchronized modifier to this method. Java allows any method modifiers
               (except static) for the readResolve() method including any access modifier */

        /* Applying writeReplace()
             - Now, what if we want to write an Employee record to disk but we don't completely trust the
               instance we are holding
             - The writeReplace() method is run before writeObject() and allows us to replace the object that
               gets serialized. */
    }

    /* Methods for serialization and deserialization
        Return type         Method Parameters         Description
        ----------------------------------------------------------------------------------------
        - Object              writeReplace()          None Allows replacement of object before serialization
        - void                writeObject()           ObjectInputStream Serializes optionally using PutField
        - void                readObject()            ObjectOutputStream Deserializes optionally using GetField
        - Object              readResolve()           None Allows replacement of object after deserialization */

}

class Employee implements Serializable {
    public String name;
    public String ssn;
    private int age;

    public Employee(String name, String ssn, int age) {
        this.name = name;
        this.ssn = ssn;
        this.age = age;
    }

    private static String encrypt(String input) { return (input + "HASHED"); }

    private static String decrypt(String input) { return input.replace("HASHED", ""); }

    // MUST be private static final
    private static final ObjectStreamField[] serialPersistentFields =
            { new ObjectStreamField("name", String.class),
              new ObjectStreamField("ssn", String.class)   };

    // MUST be private
    private void writeObject(ObjectOutputStream s) throws Exception {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("name", name);
        fields.put("ssn", encrypt(ssn));
        s.writeFields();
    }

    // MUST be private
    private void readObject(ObjectInputStream s) throws Exception {
        ObjectInputStream.GetField fields = s.readFields();
        this.name = (String)fields.get("name", null);
        this.ssn = decrypt((String)fields.get("ssn", null));
    }

    public static void main(String[] args) throws IOException {
        var file = new File("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer\\Security\\_0_test.txt");
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(new Employee("Abdullah", "ABD3303", 18));
        }
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Employee employee = (Employee) in.readObject();
            System.out.println(employee.name);
            System.out.println(employee.ssn);
            System.out.println(employee.age);
        } catch (Exception e) { }
    }

    // -----------------------------------------------------------------

    private Employee() { }
    private static final Map<String, Employee> pool = new ConcurrentHashMap<>();

    public synchronized static Employee getEmployee(String name) {
        if (pool.get(name) == null) {
            var e = new Employee();
            e.name = name;
            pool.put(name, e);
        }
        return pool.get(name);
    }

    // -----------------------------------------------------------------

    public synchronized Object readResolve() throws ObjectStreamException {
        var existingEmployee = pool.get(name);
        if (pool.get(name) == null) {
            // New employee not in memory
            pool.put(name, this);
            return this;
        } else {
            // Existing user already in memory
            existingEmployee.name = this.name;
            existingEmployee.ssn = this.ssn;
            return existingEmployee;
        }
    }

    // -----------------------------------------------------------------

    public synchronized Object writeReplace() throws ObjectStreamException {
        var e = pool.get(name);
        return (e != null) ? e : this;
    }

}