package Aufgabe_10;

public class Main {
    public static void main(String[] args) {
        FifoQueue test = new FifoQueue(null);

        ParalleleThreads t1 = new ParalleleThreads("memer", test);
        ParalleleThreads t2 = new ParalleleThreads("mamer", test);
        System.out.println("-----------normal-----------");
        test.put("Hallo");
        System.out.println(test.get());
        test.put("Johanna");
        test.put("was");
        System.out.println(test.get());
        test.put("geht?");
        System.out.println(test.get());
        System.out.println(test.get());
        System.out.println("-----------mit Threads-----------");
        t1.start();
        t2.start();
    }
}
