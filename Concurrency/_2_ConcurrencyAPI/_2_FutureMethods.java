package _2_ConcurrencyAPI;

import java.util.concurrent.*;

public class _2_FutureMethods {

    /*
        boolean isDone()
        boolean isCancelled()
        boolean cancel(boolean mayInterruptIfRunning)
        V get()
        V get(long timeout, TimeUnit unit)
     */

    public static void main(String[] args) {
        ExecutorService service = null;

        Callable<String> task = () -> "In Callable task";

        try {
            service = Executors.newSingleThreadExecutor();

            Future<String> f1 = service.submit(task);
            System.out.println(f1.get()); // waits endlessly until result is available
            // comment above line to make code below work
            if (!f1.isDone()) {
                f1.cancel(true);
                if (f1.isCancelled()) System.out.println("task has been cancelled");
            }
            System.out.println(f1.isCancelled());
        } catch (ExecutionException | InterruptedException e) { e.printStackTrace();
        } finally { if (service != null) service.shutdown(); }
    }
}
