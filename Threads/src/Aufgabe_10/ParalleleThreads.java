package Aufgabe_10;

public class ParalleleThreads extends Thread{
    private String threadName;

    public FifoQueue currentQueue;

    public ParalleleThreads(String name, FifoQueue currentQueue){
        setThreadName(name);
        this.currentQueue = currentQueue;
    }

    @Override
    public void run() {
        this.currentQueue.put("Hallo");
        System.out.println(this.currentQueue.get() + " ("+ getThreadName() + ")");
        this.currentQueue.put("Johanna");
        this.currentQueue.put("was");
        System.out.println(this.currentQueue.get() + " ("+ getThreadName() + ")");
        this.currentQueue.put("geht?");
        System.out.println(this.currentQueue.get() + " ("+ getThreadName() + ")");
        System.out.println(this.currentQueue.get() + " ("+ getThreadName() + ")");
        System.out.println(this.currentQueue.get() + " ("+ getThreadName() + ")");
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }


}
