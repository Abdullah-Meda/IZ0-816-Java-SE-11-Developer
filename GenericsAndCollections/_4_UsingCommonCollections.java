import java.util.*;

public class _4_UsingCommonCollections {

    /* A collection is a group of objects contained in a single object. The Java Collections Framework is a set of
       classes in java.util for storing collections.

       There are four main interfaces in the Java Collections Framework.
          - List:   A list is an ordered collection of elements that allows duplicate entries. Elements in a list
                    can be accessed by an int index.
          - Set:    A set is a collection that does not allow duplicate entries.
          - Queue:  A queue is a collection that orders its elements in a specific order for processing. A typical
                    queue  processes its elements in a first‐in, first‐out  order, but other orderings are possible.
          - Map:    A map is a collection that maps keys to values, with no duplicate keys allowed. The elements in
                    a map are key/value pairs.


        Notice that Map doesn't implement the Collection interface. It is considered part of the Java Collections
        Framework, even though it isn't technically a Collection. It is a collection (note the lowercase), though,
        in that it contains a group of objects. The reason why maps are treated differently is that they need
        different methods due to being key/value pairs */

    /* COMMON COLLECTION METHODS */
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

        /* boolean add(E element) */
        System.out.println(list.add("Sparrow"));            // true
        System.out.println(list.add("Sparrow"));            // true

        System.out.println(set.add("Sparrow"));             // true
        System.out.println(set.add("Sparrow"));             // false

        /* boolean remove(Object object) */
        System.out.println(list.remove("Sparrow"));      // true
        System.out.println(set.remove("Pigeon"));        // false

        /* boolean isEmpty()
           int size() */
        System.out.println(list.isEmpty());                 // false
        System.out.println(set.size());                     // 1

        /* boolean contains(Object object) */
        System.out.println(list.contains("Sparrow"));       // true
        System.out.println(list.contains("Nightingale"));   // false

        /* boolean removeIf(Predicate<? super E> filter) */
        set.add("Pigeon");
        set.add("Budgie");
        System.out.println(set);                            // [Sparrow, Pigeon, Budgie]

        set.removeIf(x -> x.startsWith("P"));               // Pigeon starts with "P", so it is removed
        System.out.println(set);                            // [Sparrow, Budgie]

        /* void forEach(Consumer<? super T> action) */
        list.forEach(System.out::println);                  // Sparrow

        /* void clear() */
        list.clear();
        set.clear();

    }

}
