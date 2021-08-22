import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _9_ComparableInterface {

    /* As far as the exam is concerned, NOTE that numbers sort before letters, and uppercase letters sort
       before lowercase letters */

    /* Creating a Comparable Class */
    static class Class implements Comparable<Class> {
        private final String name;

        public Class(String name) { this.name = name; }

        @Override
        public int compareTo(Class o) {
            return name.compareTo(o.name);
        }

        public static void main(String[] args) {
            var list = new ArrayList<>(Arrays.asList(
                    new Class("One"),
                    new Class("Two")));
            Collections.sort(list);             // Compiles only bcoz objects in list implement Comparable
        }
    }

    /* What the compareTo() method returns:
         - The number 0 is returned when the current object is equivalent to the argument to compareTo().
         - A negative number (less than 0) is returned when the current object is smaller than the argument to
           compareTo().
         - A positive number (greater than 0) is returned when the current object is larger than the argument
           to compareTo().
     */

    /* REMEMBER:
         - return name.compareTo(d.name);   // sorts ASCENDING by name which is a String
         - return id – a.id;                // sorts ASCENDING by id which is an int
     */

    /* Casting the compareTo() argument
         - When dealing with legacy code or code that does not use generics, the compareTo() method requires a
           cast since it is passed an Object. */
    static class LegacyClass implements Comparable {
        private String name;

        @Override
        public int compareTo(Object o) {
            LegacyClass legacyClass = (LegacyClass) o;            // cast because no generics
            return name.compareTo(legacyClass.name);
        }
    }

    /* Checking for NULL */
    static class MissingClass implements Comparable<MissingClass> {
        private String name;

        @Override
        public int compareTo(MissingClass o) {
            if (o == null) throw new IllegalArgumentException("Poorly formed class!");
            if      (this.name == null && o.name == null) return 0;
            else if (this.name == null) return -1;
            else if (o.name == null) return 1;
            else    return name.compareTo(o.name);
        }
    }

    /* - If you write a class that implements Comparable, you introduce new business logic for determining
         equality. The compareTo() method returns 0 if two objects are equal, while your equals() method returns
         true if two objects are equal. A natural ordering that uses compareTo() is said to be consistent with
         equals if, and only if, x.equals(y) is true whenever x.compareTo(y) equals 0.

       - Similarly, x.equals(y) must be false whenever x.compareTo(y) is not 0. You are strongly encouraged to
         make your Comparable classes consistent with equals because not all collection classes behave
         predictably if the compareTo() and equals() methods are not consistent.
     */

}
