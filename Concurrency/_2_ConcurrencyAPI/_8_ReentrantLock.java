package _2_ConcurrencyAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _8_ReentrantLock {

    /* Similar to synchronization except has a few more methods */

    /* The ReentrantLock class is a simple monitor that implements the Lock
            interface and supports mutual exclusion. In other words, at most one thread
            is allowed to hold a lock at any given time */

    /* The ReentrantLock class ensures that once a thread has called lock()
            and obtained the lock, all other threads that call lock() will wait until the
            first thread calls unlock() */

    /* Make sure to unlock() only after you lock() to avoid IllegalMonitorStateException */

    private int count = 0;

    private void incrementAndReport(Lock lock) {
        try {
            lock.lock();
            System.out.print((++count) + " ");

        } finally { lock.unlock(); }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        ReentrantLock lock = new ReentrantLock();

        try {
            service = Executors.newFixedThreadPool(20);
            var obj = new _8_ReentrantLock();
            for(int i = 0; i < 10; i++)
                service.submit(() -> obj.incrementAndReport(lock));

        } finally { if (service != null) service.shutdown(); }


        /* tryLock()
            - The tryLock() method will attempt to acquire a lock and immediately
                    return a boolean result indicating whether the lock was obtained.
            - Unlike the lock() method, it does not wait if another thread already holds
                    the lock. It returns immediately, regardless of whether or not a lock is available.
         */
        Lock lock2 = new ReentrantLock();
        if (lock2.tryLock()) {
            try {
                System.out.println("Lock obtained");
            } finally { lock2.unlock(); }
        } else { System.out.println("Unable to acquire lock"); }

        /* tryLock() has an overloaded method
            - The code is the same as before, except this time one of the threads waits up
                    to 10 seconds to acquire the lock. */
        if (lock2.tryLock(10, TimeUnit.SECONDS)) {   // throws InterruptedException
            try {
                System.out.println("Lock obtained");
            } finally { lock2.unlock(); }
        } else { System.out.println("Unable to acquire lock"); }
    }
}
