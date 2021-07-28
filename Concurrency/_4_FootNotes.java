public class _4_FootNotes {

    /* NOTES from Explanations of Answers to End of Chapter Questions:

        - All methods are capable of throwing unchecked exceptions. Runnable and Callable statements
          both do not take any arguments. Only Callable is capable of throwing checked exceptions.
          Both Runnable and Callable are functional interfaces that can be implemented with a
          lambda expression. Finally, Runnable returns void and Callable returns a generic type.

        - The tryLock() method returns immediately with a value of false if the lock cannot be acquired.
          Unlike lock(), it does not wait for a lock to become available. This code fails to check the
          return value, resulting in the protected code being entered regardless of whether the lock is
          obtained. On other executions (when tryLock() returns false at least once), the unlock() method
          will throw an IllegalMonitorStateException at runtime

        - ScheduledExecutorService extends ExecutorService which means all methods are inherited

        - If a task is submitted to a thread executor, and the thread executor does not have any
          available threads, the call to the task will return immediately with the task being queued
          internally by the thread executor

        - The CopyOnWriteArrrayList class is designed to preserve the original list on iteration

        - Note that calling parallel() on an already parallel stream is allowed, and it may in fact
          return the same object.

        - If a thread executor is never shut down, the code will run but it will never terminate at
          runtime!

        - The findFirst() method guarantees the first element in the stream will be returned, whether
          it is serial or parallel

        - By itself, concurrency does not guarantee which task will be completed first

        - Furthermore, applications with numerous resource requests will often be stuck waiting for a
          resource, which allows other tasks to run. Therefore, they tend to benefit more from concurrency than CPU-intensive tasks

        - Concurrency may in fact make an application slower if it is truly single-threaded in nature.
          Keep in mind that there is a cost associated with allocating additional memory and CPU time to
          manage the concurrent process

        - The return type of performCount() is void, so submit() is interpreted as being applied to a
          Runnable expression. While submit(Runnable) does return a Future<?>, calling get() on it always
          returns null. The performCount() method can also throw a runtime exception, which will then be
          thrown by the get() call as an ExecutionException
     */

}
