import java.util.Set;

public class _6_TheSetInterface {

    /* You use a set when you don't want to allow duplicate entries */

    /* A HashSet stores its elements in a hash table, which means the keys are a hash and the values are an
       Object. This means that it uses the hashCode() method of the objects to retrieve them more efficiently */

    public static void main(String[] args) {

        /* Like List, you can create an immutable Set in one line or make a copy of an existing one. */
        Set<Character> letters = Set.of('z', 'e', 'n');
        Set<Character> copy = Set.copyOf(letters);

        /* Working with Set methods */
        Set<Integer> set = new java.util.HashSet<>(Set.of(1, 2, 3, 4, 5));
        set.add(6);
        set.forEach(System.out::println);

    }

}
