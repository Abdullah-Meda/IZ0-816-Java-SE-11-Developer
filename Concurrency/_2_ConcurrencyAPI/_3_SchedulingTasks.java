package _2_ConcurrencyAPI;

import java.util.concurrent.*;

public class _3_SchedulingTasks {

    public static void main(String[] args) {
        ScheduledExecutorService service = null;

        Runnable rTask = () -> System.out.println("In Runnable task");
        Callable<String> cTask = () -> "In Callable task";

        try {
            service = Executors.newSingleThreadScheduledExecutor();

            /* schedule(Callable<V> callable, long delay, TimeUnit unit)
               schedule(Runnable command, long delay, TimeUnit unit)      */
            ScheduledFuture<?> sf1 = service.schedule(rTask, 5, TimeUnit.SECONDS);
            ScheduledFuture<String> sf2 = service.schedule(cTask, 1, TimeUnit.SECONDS);
            System.out.println(sf2.get());      // this is printed first
            System.out.println(sf1.get());      // prints null
            /* The first task is scheduled 5 seconds in the future, whereas the second task
                    is scheduled 1 seconds in the future. */

            /* scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) */
            /* creates a new task and submits it to the executor every period, regardless
                    of whether the previous task finished   */
            service.scheduleAtFixedRate(rTask, 5, 3, TimeUnit.SECONDS);

            /* scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) */
            /* creates a new task only after the previous task has finished */
            service.scheduleWithFixedDelay(rTask, 3, 5, TimeUnit.SECONDS);

        } catch (ExecutionException | InterruptedException e) { e.printStackTrace();
        } finally { if (service != null) service.shutdown(); }
    }

}
