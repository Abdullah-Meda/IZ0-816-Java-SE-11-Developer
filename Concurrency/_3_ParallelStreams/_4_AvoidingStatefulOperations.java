package _3_ParallelStreams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _4_AvoidingStatefulOperations {

    /* A stateful lambda expression is one whose result depends on any
       state that might change during the execution of a pipeline

       On the other hand, a stateless lambda expression is one whose result does not depend on
       any state that might change during the execution of a pipeline */

    /* Example of a stateful operations */
    public static List<Integer> addValuesStateful(IntStream source) {
        var data = Collections.synchronizedList(new ArrayList<Integer>());
        // filters even values from the stream and returns them as a list
        source.filter(s -> s % 2 == 0)
                .forEach(i -> data.add(i)); // STATEFUL: DON'T DO THIS!
        return data;
    }

    /* Example of a stateless operations */
    public static List<Integer> addValuesStateless(IntStream source) {
        // filters even values from the stream and returns them as a list
        var data = source.filter(s -> s % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        return data;
    }

    public static void main(String[] args) {
        /* Stateful */
        var list1 = addValuesStateful(IntStream.range(1, 11));
        System.out.println(list1);       // [2, 4, 6, 8, 10]

        var list2 = addValuesStateful(IntStream.range(1, 11).parallel());
        System.out.println(list2);       // [6, 10, 4, 8, 2]

        /* Stateless */
        var list3 = addValuesStateless(IntStream.range(1, 11));
        System.out.println(list3);       // [2, 4, 6, 8, 10]

        var list4 = addValuesStateless(IntStream.range(1, 11).parallel());
        System.out.println(list4);       // [2, 4, 6, 8, 10]

    }

}
