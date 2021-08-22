import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class _10_ComparatorInterface {

    /* Sometimes you want to sort an object that did not implement Comparable, or you want to sort objects in
       different ways at different times - The Comparator */

    /* Comparison of Comparable and Comparator
        Difference                                          Comparable          Comparator
        -------------                                       ----------          ----------
        Package name                                        java.lang           java.util
        Interface must be implemented by class comparing?   Yes                 No
        Method name in interface                            compareTo()         compare()
        Number of parameters                                1                   2
        Common to declare using a lambda                    No                  Yes
     */
    static class Human implements Comparable<Human> {
        private final String name;
        private final int age;

        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Human o) {
            return name.compareTo(o.name);          // compares by name
        }

        @Override
        public String toString() {
            return name + "-" + age;
        }

        public static void main(String[] args) {
            Comparator<Human> byAge = new Comparator<Human>() {
                @Override
                public int compare(Human o1, Human o2) {
                    return o1.age - o2.age;                 // Sorts ascending by age
                }
            };

            /* The above Comparator can be written in the following ways too: */
            Comparator<Human> byAgeAsLambda = (o1, o2) -> {
                return o1.age - o2.age;
            };

            Comparator<Human> byAgeOptimised = Comparator.comparingInt(o -> o.age);

            var humans = new ArrayList<>(Arrays.asList( new Human("Abdullah", 18),
                                                        new Human("Ali", 21),
                                                        new Human("Hammad", 11)));
            Collections.sort(humans);           // [Abdullah-18, Ali-21, Hammad-11]
            Collections.sort(humans, byAge);    // [Hammad-11, Abdullah-18, Ali-21]
        }
    }

    /* Comparing Multiple Fields */

}
