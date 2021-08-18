import java.util.LinkedList;
import java.util.Queue;

public class _7_TheQueueInterface {

    /* - You use a queue when elements are added and removed in a specific order
       - Unless stated otherwise, a queue is assumed to be FIFO (first‐in, first‐out) */

    /* Queue methods:
                                                                                                Throws exception
        Method                  Description                                                         on failure
        ----------              ---------------------                                           -----------------
        boolean add(E e)        Adds an element to the back of the queue and returns true or           Yes
                                throws an exception
        boolean offer(E e)      Adds an element to the back of the queue and returns whether           No
                                successful
        E element()             Returns next element or throws an exception if empty queue             Yes
        E peek()                Returns next element or returns null if empty queue                    No
        E remove()              Removes and returns next element or throws an exception if             Yes
                                empty queue
        E poll()                Removes and returns next element or returns null if empty queue        No
     */

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        System.out.println(queue.offer(10));         // true
        System.out.println(queue.offer(4));          // true
        System.out.println(queue.peek());               // 10
        System.out.println(queue.poll());               // 10
        System.out.println(queue.poll());               // 4
        System.out.println(queue.peek());               // null

    }

}
