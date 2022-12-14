package AufgabenTeilB;

import java.util.concurrent.Semaphore;

public class Lok {
    private boolean[] waiting = {false,false};
    private boolean isTrackFree = true;
    private int nextLok = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore[] privSem = {new Semaphore(1, true), new Semaphore(0,true)};
    private double speed;
    private String player;
    private String lokName;

    public Lok(int lokId, double speed, String player, String lokName){
        this.speed = speed;
        this.player = player;
        this.lokName = lokName;
    }

    public void enterLok0() throws InterruptedException {
        System.out.println("    "  + player+ " will seine Lock (" + lokName + ") auf die geteilte Schiene fahren lassen.");
        mutex.acquire();
        if ( isTrackFree && nextLok == 0 ) {
            isTrackFree = false;
            privSem[0].release();
        } else {
            waiting[0] = true;
        }
        mutex.release();
        privSem[0].acquire();
        System.out.println("        "  + lokName + " fährt auf geteilter Schiene. Yippieeeee!");

    }

    public void exitLok0() throws InterruptedException {
        System.out.println(lokName + " verlässt geteilte Schiene :(");
        mutex.acquire();
        isTrackFree = true;
        if (waiting[1] == true) {
            privSem[1].release();
        } else {
            nextLok = 1;
        }

    }

    public void enterLok1() throws InterruptedException {
        System.out.println("    "  +player+ " will seine Lock (" + lokName + ") auf die geteilte Schiene fahren lassen.");
        mutex.acquire();
        if ( isTrackFree && nextLok == 1 ) {
            isTrackFree = false;
            privSem[1].release();
        } else {
            waiting[1] = true;
        }
        mutex.release();
        privSem[1].acquire();
        System.out.println("        "  +lokName + " fährt auf geteilter Schiene. Yippieeeee!");
    }

    public void exitLok1() throws InterruptedException {
        System.out.println(lokName + " verlässt geteilte Schiene :(");
        mutex.acquire();
        isTrackFree = true;
        if (waiting[0] == true) {
            privSem[0].release();
        } else {
            nextLok = 0;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public String getLokName() {
        return lokName;
    }
}
