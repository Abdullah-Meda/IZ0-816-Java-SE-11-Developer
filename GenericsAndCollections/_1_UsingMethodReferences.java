import java.util.*;
import java.util.function.*;

public class _1_UsingMethodReferences {

    /* A method reference and a lambda behave the same way at runtime. You can pretend the compiler turns your
       method references into lambdas for you. There are four formats for method references:
         - Static methods
         - Instance methods on a particular instance
         - Instance methods on a parameter to be determined at runtime
         - Constructors */

    public static void main(String[] args) {

        /* Calling Static Methods */
        Consumer<List<Integer>> methodRef1 = Collections::sort;
        Consumer<List<Integer>> lambda1 = x -> Collections.sort(x);

        /* Calling Instance Methods on a Particular Object */
        var str = "abc";
        Predicate<String> methodRef2 = str::startsWith;
        Predicate<String> lambda2 = s -> str.startsWith(s);

        /* Calling Instance Methods on a Parameter
             - Since the functional interface takes two parameters, Java has to figure out what they represent.
               The first one will always be the instance of the object for instance methods. Any others are to
               be method parameters. */
        BiPredicate<String, String> methodRef3 = String::startsWith;
        BiPredicate<String, String> lambda3 = (s, p) -> s.startsWith(p);

        /* Calling Constructors
             - A constructor reference is a special type of method reference that uses new instead of a method,
               and it instantiates an object. It is common for a constructor reference to use a Supplier as shown
               here: */
        Supplier<List<String>> methodRef4 = ArrayList::new;
        Supplier<List<String>> lambda4 = () -> new ArrayList<>();

    }

    /* Review Method References:
        Type                    Before colon        After colon         Example
        ------                  ------------        -----------         --------
      - Static methods          Class name          Method name         Collections::sort
      - Instance methods on     Instance variable   Method name         str::startsWith
        a particular object     name
      - Instance methods on     Class name          Method name         String::isEmpty
        a parameter
      - Constructor             Class name          new                 ArrayList::new
     */

}
