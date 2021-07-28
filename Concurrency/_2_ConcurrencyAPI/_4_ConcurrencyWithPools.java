package _2_ConcurrencyAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class _4_ConcurrencyWithPools {

    public static void main(String[] args) {
        ExecutorService es = null;
        ScheduledExecutorService ses = null;

        int numOfCores = Runtime.getRuntime().availableProcessors();

        try {
            /* Creates a single‐threaded executor that uses a single worker thread operating
                    off an unbounded queue. Results are processed sequentially in the order
                    in which they are submitted */
            es = Executors.newSingleThreadExecutor();

            /* Creates a single‐threaded executor that can schedule commands to run after a
                    given delay or to execute periodically */
            ses = Executors.newSingleThreadScheduledExecutor();

            /* Creates a thread pool that creates new threads as needed but will reuse previously
                    constructed threads when they are available */
            es = Executors.newCachedThreadPool();

            /* Creates a thread pool that reuses a fixed number of threads operating off a shared
                    unbounded queue */
            es = Executors.newFixedThreadPool(numOfCores);

            /* Creates a thread pool that can schedule commands to run after a given delay or to
                    execute periodically */
            ses = Executors.newScheduledThreadPool(numOfCores);

            /* The difference between a single‐thread and a pooled‐thread executor is what
               happens when a task is already running. While a single‐thread executor will
               wait for a thread to become available before running the next task, a pooled‐
               thread executor can execute the next task concurrently. If the pool runs out
               of available threads, the task will be queued by the thread executor and wait
               to be completed. */

        } finally {
            if (es != null) es.shutdown();
            if (ses != null) ses.shutdown();
        }
    }
}
