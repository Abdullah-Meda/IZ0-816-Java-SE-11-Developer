package _2_ConcurrencyAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _7_SynchronizedMethods {

    /* We can add the synchronized modifier to any instance method to synchronize
            automatically on the object itself */

    private int count = 0;

    private synchronized void incrementAndReport() {
            System.out.print((++count) + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            var obj = new _7_SynchronizedMethods();
            for(int i = 0; i < 10; i++)
                service.submit(obj::incrementAndReport);

        } finally { if (service != null) service.shutdown(); }
    }

    /* The above uses a synchronized method modifier, whereas the code in the previous file uses the
            synchronized block. Which you use is completely up to you */

    /* We can also apply the synchronized modifier to static methods. */
    public static synchronized void print1() {
        System.out.println("In Print1");
    }

    /* The below is the same as above */
    public static void print2() {
        synchronized (_7_SynchronizedMethods.class) {
            System.out.println("In Print2");
        }
    }

    /* You can use static synchronization if you need to order thread access across all instances,
    rather than a single instance */
}
