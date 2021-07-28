package _2_ConcurrencyAPI;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class _10_ConcurrentCollections {

    /* A memory consistency error occurs when two threads have inconsistent views of what
       should be the same data */

    /* You should use a concurrent collection class anytime that you are going to
       have multiple threads modify a collections object outside a synchronized
       block or method, even if you don't expect a concurrency problem */

    /* Concurrent Collection Classes
                 Class Name           Ordered?        Sorted?
            ConcurrentHashMap           No              No
            ConcurrentLinkedQueue       Yes             No
            ConcurrentSkipListSet       Yes             Yes
            ConcurrentSkipListMap       Yes             Yes
            CopyOnWriteArrayList        Yes             No
            CopyOnWriteArraySet         No              N0
            LinkedBlockingQueue         Yes             No
     */

    public static void main(String[] args) {

        /* ConcurrentHashMap  ~ Similar to HashMap */
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("Zebra", 52);
        System.out.println(map.get("Zebra"));

        /* ConcurrentLinkedQueue ~ Similar to LinkedList */
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
        System.out.println(queue.peek());
        System.out.println(queue.poll());

        /* ConcurrentSkipListSet ~ Similar to TreeSet */
        Set<String> set = new ConcurrentSkipListSet<>();
        set.add("one");
        set.add("two");
        System.out.println(set.stream().collect(Collectors.joining(", ")));

        /* ConcurrentSkipListMap ~ Similar to TreeMap */
        ConcurrentMap<Integer, String> map2 = new ConcurrentSkipListMap<>();
        map2.put(1, "One");
        map2.put(2, "Two");
        System.out.println(map2.get(2));

        /* In CopyOnWriteCollections, any iterator established prior to a modification will not see
           the changes, but instead it will iterate over the original elements prior to the modification */
        /* CopyOnWriteArrayList */
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer num : list)        // <---- Iterator on list [1, 2]
            list.add(3);
        System.out.println(list);       // <--- Prints updated list [1, 2, 3, 3]

        /* Example 2 for CopyOnWriteArrayList */
        List<String> birds = new CopyOnWriteArrayList<>();
        birds.add("hawk");
        birds.add("hawk");
        birds.add("hawk");

        for (String bird : birds)
            birds.remove(bird);
        System.out.println(birds.size()); // 0


        /* CopyOnWriteArraySet */
        Set<Character> set2 = new CopyOnWriteArraySet<>(List.of('A', 'B'));
        for (Character ch : set2)
            set2.add('C');
        System.out.println(set2);       // [A, B, C] because a Set doesn't allow duplicates

        /* BlockingQueue */
        /* The BlockingQueue is just like a regular Queue, except that it
           includes methods that will wait a specific amount of time to complete an
           operation.

            Extra Methods:
                - boolean offer(E e, long timeout, TimeUnit unit)
                    Adds an item to the queue, waiting the specified time and returning false if
                    the time elapses before space is available
                - boolean poll(long timeout, TimeUnit unit)
                    Retrieves and removes an item from the queue, waiting the specified time and
                    returning null if the time elapses before the item is available
         */
        try {
            BlockingQueue<Integer> queue2 = new LinkedBlockingQueue<>();
            queue2.offer(1);
            queue2.offer(2, 2, TimeUnit.SECONDS);       // throws InterruptedException
            System.out.println(queue2.poll());
            System.out.println(queue2.poll(15, TimeUnit.MILLISECONDS));

        } catch (InterruptedException e) { e.printStackTrace(); }

        // -------------------------------------------------------------

        /* Methods to convert non-concurrent collections to concurrent collections:
             - synchronizedCollection(Collection<T> c)
             - synchronizedList(List<T> list)
             - synchronizedMap(Map<K,V> m)
             - synchronizedNavigableMap(NavigableMap<K,V> m)
             - synchronizedNavigableSet(NavigableSet<T> s)
             - synchronizedSet(Set<T> s)
             - synchronizedSortedMap(SortedMap<K,V> m)
             - synchronizedSortedSet(SortedSet<T> s)
         */
        /* Example: */
        Set<Integer> set3 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        var skipListSet = Collections.synchronizedSet(set3);
        for (Integer num : set3) set3.add(6);
        System.out.println(set3);
        /* The above loop throws a ConcurrentModificationException, whereas our
           example that used ConcurrentHashMap did not. Other than iterating over
           the collection, the objects returned by the methods in the above methods are
           safe from memory consistency errors and can be used among multiple threads. */

    }

}
