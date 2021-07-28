package _3_ParallelStreams;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _3_ParallelReductions {

    public static void main(String[] args) {

        /* Performing Order-Based Tasks */
        System.out.println(List.of(1, 2, 3, 4, 5, 6)
            .stream()
            .findAny().get()
        );

        System.out.println(List.of(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .findAny().get()
        );

        /* Any stream operation that is based on order, including findFirst(),
           limit(), or skip(), may actually perform more slowly in a parallel
           environment. This is a result of a parallel processing task being forced to
           coordinate all of its threads in a synchronized‐like fashion */

        /* For serial streams, using an unordered version has no effect, but on
           parallel streams, the results can greatly improve performance. */
        List.of(1,2,3,4,5,6).stream().unordered().parallel();

        // -----------------------------------------------------------------------

        /* Combining results with reduce() */
        System.out.println(List.of("W", "O", "L", "F")
                .parallelStream()
                .reduce("", (s1, c) -> s1 + c, (s2, s3) -> s2 + s3)
        );

        /* make sure that the accumulator and combiner work regardless of the order they are
           called in */

        // -------------------------------------------------------------

        /* Combining results with collect() */
        Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,
                Set::add,
                Set::addAll);
        System.out.println(set);    // [f, l, o, w]

        // -------------------------------------------------------------

        /* Performing a parallel reduction on a Collector */
        /* Requirements for Parallel Reduction with collect():
            - The stream is parallel.
            - The parameter of the collect() operation has the Characteristics.CONCURRENT
              characteristic.
            - Either the stream is unordered or the collector has the characteristic
              Characteristics.UNORDERED.

            Two such methods are:
                - toConcurrentMap()
                - groupingByConcurrent()

            Here are example of both:
         */
        /* toConcurrentMap() */
        Stream<String> stream1 = Stream.of("One", "Two", "Three", "Four", "Five").parallel();
        ConcurrentMap<Integer, String> cMap = stream1.collect(Collectors.toConcurrentMap(
                String::length,
                s -> s,
                (s1, s2) -> s1 + "," + s2
        ));
        System.out.println(cMap);       // {3=Two,One, 4=Five,Four, 5=Three}

        /* groupingByConcurrent() */
        Stream<String> stream2 = Stream.of("One", "Two", "Three", "Four", "Five").parallel();
        ConcurrentMap<Integer, List<String>> cMap2 = stream2.collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(cMap2);      // {3=[Two, One], 4=[Five, Four], 5=[Three]}

    }

}
