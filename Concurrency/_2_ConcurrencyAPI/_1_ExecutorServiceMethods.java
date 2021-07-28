package _2_ConcurrencyAPI;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class _1_ExecutorServiceMethods {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;

        Runnable task1 = () -> System.out.println("In task 1");
        Callable<String> task2 = () -> "In task 2";

        try {
            service = Executors.newSingleThreadExecutor();

            /* execute() */
            service.execute(task1);

            /* submit() */
            Future<?> f1 = service.submit(task1);
                System.out.println(f1.get());   // prints null
                // .get() throws ExecutionException & InterruptedException
            Future<String> f2 = service.submit(task2);
                System.out.println(f2.get(5, TimeUnit.SECONDS));
                // throws an additional TimeoutException

            /* invokeAll() throws InterruptedException */
            /* NOTE the use of a Collection as the parameter */
            List<Future<String>> f3 = service.invokeAll(Arrays.asList(task2, task2, task2));
                for (Future<String> f : f3) System.out.println(f.get());

            /* invokeAny() throws InterruptedException and ExecutionException */
            /* executes a collection of tasks and returns the result of one of the tasks that
                    successfully completes execution, cancelling all unfinished tasks */
            /* NOTE this methods doesn't return a Future<T> */
            String str = service.invokeAny(Arrays.asList(task2, task2, task2));
                System.out.println(str);

            /* The ExecutorService interface also includes overloaded versions of invokeAll() and
                    invokeAny() that take a timeout value and TimeUnit parameter */

            // Adding extra tasks
            List<Future<String>> f4 = service.invokeAll(Arrays.asList(task2, task2, task2, task2, task2, task2));

        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (service != null) service.shutdown();
            /* service.shutDown
                - Rejects new tasks
                - Continues executing current tasks */
            /* Lastly, shutdownNow() returns a List<Runnable> of tasks that were submitted
                    to the thread executor but that were never started */
        }

        /* awaitTermination() */
        service.awaitTermination(10 , TimeUnit.SECONDS);

        /* isTerminated() */
        if (service.isTerminated()) System.out.println("Finished!");
        else System.out.println("Atleast 1 task is running!");
    }
}
