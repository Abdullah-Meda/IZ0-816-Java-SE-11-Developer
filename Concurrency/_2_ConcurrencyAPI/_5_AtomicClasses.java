package _2_ConcurrencyAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class _5_AtomicClasses {
    private static final AtomicInteger ai = new AtomicInteger(0);

    /* Common atomic methods:
        get()               Retrieves the current value
        set()               Sets the given value, equivalent to the assignment = operator
        getAndSet()         Atomically sets the new value and returns the old value
        incrementAndGet()   For numeric classes, atomic pre‐increment operation equivalent to ++value
        getAndIncrement()   For numeric classes, atomic post‐increment operation equivalent to value++
        decrementAndGet()   For numeric classes, atomic pre‐decrement operation equivalent to ‐‐value
        getAndDecrement()   For numeric classes, atomic post‐decrement operation equivalent to value‐‐
     */

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);

            for (int i = 0; i < 50; i++) {
                service.submit(ai::incrementAndGet);
            }

            /* Other Atomic Class Methods */
            System.out.println(ai.get());
            ai.set(25);
            System.out.println(ai.getAndSet(50));

        } finally { if (service != null) service.shutdown(); }

        boolean isFinished = service.awaitTermination(2, TimeUnit.SECONDS);
        if (isFinished) System.out.println(ai.get());
    }

    /* AtomicLong is quite similar to AtomicInt */
}
