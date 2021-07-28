package _3_ParallelStreams;

import java.util.List;
import java.util.stream.Stream;

public class _1_CreatingParallelStreams {

    /* A parallel stream is a stream that is capable of processing results
       concurrently, using multiple threads */

    public static void main(String[] args) {

        /* METHOD 1: Calling parallel() on existing stream */
        Stream<Integer> s1 = List.of(1, 2, 3, 4, 5).stream();
        Stream<Integer> s2 = s1.parallel();

        /* METHOD 2: Calling parallelStream() on a Collection object */
        Stream<Integer> s3 = List.of(1, 2, 3, 4, 5).parallelStream();

        // A random stream
        Stream<Integer> s4 = List.of(1, 2, 3, 4, 5).stream();

        System.out.println(s1.isParallel());        // true
        System.out.println(s3.isParallel());        // true
        System.out.println(s4.isParallel());        // false


    }

}
