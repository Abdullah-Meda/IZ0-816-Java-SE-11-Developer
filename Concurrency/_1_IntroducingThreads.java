public class _1_IntroducingThreads {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        /* Three ways to create and start a thread */
            /* Method 1: Provide a lambda expression to the Thread constructor. */
            new Thread(() -> System.out.println("In Thread")).start();
            /* Method 2: Provide a Runnable object Thread constructor. */
            new Thread(new PrintData()).start();
            /* Method 3: Create a class that extends Thread and overrides the run() method */
            new ReadInventoryThread().start();

        /* Polling with sleep() */
        new Thread(() -> { for(int i = 0; i < 500; i++) counter++; }).start();
        while(counter < 100) {
            System.out.println("Not reached yet");
            Thread.sleep(1000); // 1 SECOND
        }
        System.out.println("Reached!");

    }
}

class PrintData implements Runnable{
    @Override public void run() { System.out.println("In PrintData"); }
}

class ReadInventoryThread extends Thread {
    @Override public void run() { System.out.println("In ReadInventoryThread"); }
}
