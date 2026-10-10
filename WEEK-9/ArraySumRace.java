import java.util.concurrent.atomic.AtomicLong;

class SharedTotal {
    long total = 0;

    void add(long value) {
        total += value;
    }

    synchronized void addSynchronized(long value) {
        total += value;
    }
}

public class ArraySumRace {

    static int[] arr = new int[1_000_000];
    static int threadCount = 10;

    public static void main(String[] args) throws Exception {

        // Fill array with 1
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        System.out.println("Expected Total: " + arr.length);

        // 1. Without Synchronization
        SharedTotal total1 = new SharedTotal();

        long start1 = System.nanoTime();

        runThreads((start, end) -> {
            for (int i = start; i < end; i++) {
                total1.add(arr[i]);
            }
        });

        long end1 = System.nanoTime();

        System.out.println("\nWithout Synchronization:");
        System.out.println("Total = " + total1.total);
        System.out.println("Time = " + (end1 - start1) / 1_000_000.0 + " ms");


        // 2. With Synchronization
        SharedTotal total2 = new SharedTotal();

        long start2 = System.nanoTime();

        runThreads((start, end) -> {
            for (int i = start; i < end; i++) {
                total2.addSynchronized(arr[i]);
            }
        });

        long end2 = System.nanoTime();

        System.out.println("\nWith Synchronization:");
        System.out.println("Total = " + total2.total);
        System.out.println("Time = " + (end2 - start2) / 1_000_000.0 + " ms");


        // 3. Using AtomicLong
        AtomicLong total3 = new AtomicLong(0);

        long start3 = System.nanoTime();

        runThreads((start, end) -> {
            for (int i = start; i < end; i++) {
                total3.addAndGet(arr[i]);
            }
        });

        long end3 = System.nanoTime();

        System.out.println("\nUsing AtomicLong:");
        System.out.println("Total = " + total3.get());
        System.out.println("Time = " + (end3 - start3) / 1_000_000.0 + " ms");
    }


    interface Task {
        void execute(int start, int end);
    }


    static void runThreads(Task task) throws Exception {

        Thread[] threads = new Thread[threadCount];

        int partSize = arr.length / threadCount;

        for (int i = 0; i < threadCount; i++) {

            int start = i * partSize;

            int end = (i == threadCount - 1)
                    ? arr.length
                    : start + partSize;

            threads[i] = new Thread(() -> task.execute(start, end));

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}