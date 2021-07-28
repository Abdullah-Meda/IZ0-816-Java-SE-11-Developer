package _3_ParallelStreams;

import java.util.List;

public class _2_ParallelDecomposition {

    /* A parallel decomposition is the process of taking a task, breaking it up into smaller
       pieces that can be performed concurrently, and then reassembling the results */

    private static int doWork(int input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) { e.printStackTrace(); }
        return input;
    }

    public static void main(String[] args) {

        /* Using a Serial Stream */
        long start1 = System.currentTimeMillis();
        List.of(1, 2, 3, 4, 5)
                .stream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        var timeTaken1 = (System.currentTimeMillis() - start1) / 1000;
        System.out.println("Time: " + timeTaken1 + "s");

        /* Using a Parallel Stream */
        long start2 = System.currentTimeMillis();
        List.of(1, 2, 3, 4, 5)
                .parallelStream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));   // numbers are not printed in order
        System.out.println();

        var timeTaken2 = (System.currentTimeMillis() - start2) / 1000;
        System.out.println("Time: " + timeTaken2 + "s");

    }

}
