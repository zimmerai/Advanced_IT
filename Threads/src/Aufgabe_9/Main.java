package Aufgabe_9;

public class Main {
    public static void main(String[] args) {
        System.out.println("With Big Array");
        BigArray bigArray = new BigArray(100000000);
        bigArray.getSum();

        System.out.println("With Threads");
        WorkerThreads workers = new WorkerThreads(128,bigArray);
    }
}
