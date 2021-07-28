package _2_ConcurrencyAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _6_SynchronizedBlocks {

    /* To synchronize access across multiple threads, each thread must
       have access to the same Object. For example, synchronizing on
       different objects would not actually order the results. */

    private int count = 0;

    private void incrementAndReport() {
        synchronized(this) {
            System.out.print((++count) + " ");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            var obj = new _6_SynchronizedBlocks();
            for(int i = 0; i < 10; i++)
                service.submit(() -> obj.incrementAndReport());

        } finally { if (service != null) service.shutdown(); }
    }
}
