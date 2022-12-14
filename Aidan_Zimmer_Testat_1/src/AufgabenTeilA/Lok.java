package AufgabenTeilA;

import java.util.concurrent.Semaphore;

public class Lok {
    private double speed;


    private String player;
    private String lokName;

    private static Semaphore lok1Sem = new Semaphore(0, true);
    private static Semaphore lok0Sem = new Semaphore(1, true); //size auf 1 weil wir nur einen wartendenden Zug vor geteilter Schiene haben

    public Lok(double speed, String player, String lokName){
        this.speed = speed;
        this.player = player;
        this.lokName = lokName;
    }

    public void enterLok0() throws InterruptedException {
        System.out.println("    "  + player+ " will seine Lock (" + lokName + ") auf die geteilte Schiene fahren lassen.");
        lok0Sem.acquire();
        System.out.println("        "+lokName + " fährt auf geteilter Schiene. Yippieeeee!");
    }

    public void exitLok0() throws InterruptedException {
        System.out.println(lokName + " verlässt geteilte Schiene :(");
        lok1Sem.release();
    }

    public void enterLok1() throws InterruptedException {
        System.out.println("    "  + player+ " will seine Lock (" + lokName + ") auf die geteilte Schiene fahren lassen.");
        lok1Sem.acquire();
        System.out.println("        "  +lokName + " fährt auf geteilter Schiene. Yippieeeee!");
    }

    public void exitLok1() throws InterruptedException {
        System.out.println(lokName + " verlässt geteilte Schiene :(");
        lok0Sem.release();
    }

    public double getSpeed() {
        return speed;
    }

    public String getLokName() {
        return lokName;
    }
}
