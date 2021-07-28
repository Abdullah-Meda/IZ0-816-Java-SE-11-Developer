package _2_ConcurrencyAPI;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _9_CyclicBarrier {

    /* Goal of a Cyclic Barrier */
    /* We could have all of the work completed by a single thread, but this would
       be slow and ignore the fact that we have three other threads standing by to
       help. A better solution would be to have all four threads work concurrently,
       pausing between the end of one set of tasks and the start of the next. */

    private void first() { System.out.println("First"); }
    private void second() { System.out.println("Second"); }

    private void performTask(CyclicBarrier cb) {
        try {
            first();
            cb.await();     // throws BrokenBarrierException, InterruptedException
            /* The program will get here only after 4 threads (defined in declaration)
                    have reached the above line  */
            second();
        } catch (BrokenBarrierException | InterruptedException e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(4);
            _9_CyclicBarrier obj = new _9_CyclicBarrier();
            CyclicBarrier cb = new CyclicBarrier(4);

            for (int i = 0; i < 4; i++) {
                service.submit(() -> obj.performTask(cb));
            }

        } finally { if (service != null) service.shutdown(); }
    }
}
