import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* THIS IS A CONTINUATION OF _4_IOStreamClasses */

    /* Serialization is the process of converting an in‐memory object to a byte stream.
       Likewise, deserialization is the process of converting from a byte stream into an object */

    /* To serialize an object using the I/O API, the object must implement the
       java.io.Serializable interface. The Serializable interface is a marker interface */

/* Any field that is marked transient will NOT be saved to a stream when the class is serialized */

    /* How to Make a Class Serializable:
         - The class must be marked Serializable.
         - Every instance member of the class is serializable, marked transient, or
           has a null value at the time of serialization. */

/* Here is a valid serializable class (This is a helper class, scroll down for more): */
class Gorilla implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Boolean friendly;
    private transient String favoriteFood;

    public Gorilla(String name, int age, Boolean friendly) {
        this.name = name;
        this.age = age;
        this.friendly = friendly;
    }

    public String toString() { return "[ name=" + name + ", age=" + age + ", friendly=" + friendly + " ]"; }
}

/* Deserialization:
     - When you deserialize an object, the constructor of the serialized class, along
       with any instance initializers, is not called when the object is created.
     - Java will call the no‐arg constructor of the first nonserializable parent class it can
       find in the class hierarchy
 */

public class _5_SerializingData {

    /* ObjectInputStream / ObjectOutputStream */

        /* Serialization - The following method serializes a List of Gorilla objects to a file */
        private static void saveToFile(List<Gorilla> gorillas, File dataFile) {
            try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {

                for (Gorilla gorilla : gorillas) out.writeObject(gorilla);

            } catch (IOException e) { e.printStackTrace(); }
        }

        /* Deserialization - This method deserializes the data file into a list of Objects */
        private static List<Gorilla> readFromFile(File dataFile) {
            var gorillas = new ArrayList<Gorilla>();
            try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {

                while (true) {      // throws EOFException when objects to be read are done
                    var object = in.readObject();
                    if (object instanceof Gorilla) gorillas.add((Gorilla) object);
                }

            } catch (IOException | ClassNotFoundException e) { /* File end reached */ }

            return gorillas;
        }

    public static void main(String[] args) {
        var gorillas = new ArrayList<Gorilla>();
        gorillas.add(new Gorilla("Grodd", 5, false));
        gorillas.add(new Gorilla("Ishmael", 8, true));
        File dataFile = new File("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer\\IO\\_0_FilesToWriteTo\\_5_DataFile.txt");  // my path, you may change it to yours

        saveToFile(gorillas, dataFile); // check _5_DataFile.txt in this package
        var gorillasFromFile = readFromFile(dataFile);
        System.out.println(gorillasFromFile);
    }

    /* CONTINUED IN THE NEXT CLASS FILE */

}