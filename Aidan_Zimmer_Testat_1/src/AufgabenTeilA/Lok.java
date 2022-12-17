package AufgabenTeilA;

import java.util.concurrent.Semaphore;

public class Lok {
    private double speed;


    private String player;
    private String lokName;

    /*
    Diese zwei Semaphoren ähneln dem "full" und "empty" Semaphore aus der Vorlesung.
    Es wird garantiert, dass 0 zuerst fährt, indem lok1sem auf null und lok0sem auf eins initialisiert ist.
    */
    private static Semaphore lok1Sem = new Semaphore(0, true);
    private static Semaphore lok0Sem = new Semaphore(1, true);

    public Lok(double speed, String player, String lokName){
        this.speed = speed;
        this.player = player;
        this.lokName = lokName;
    }

    //Es wird lediglich loksem0 acquired, da wir nur zwei Threads haben. Es wird garantiert, dass die Lok nicht zweimal fahren darf und lässt Lok1 weiterhin im wartenden Zustand
    public void enterLok0() throws InterruptedException {
        System.out.println("    "  + player+ " will seine Lock (" + lokName + ") auf die geteilte Schiene fahren lassen.");
        lok0Sem.acquire();
        System.out.println("        "+lokName + " fährt auf geteilter Schiene. Yippieeeee!");
    }

    //Hier wird nur Lok1 released. Als Nächstes darf demnach NUR Lok1 fahren und Lok0 wird warten
    public void exitLok0() throws InterruptedException {
        System.out.println(lokName + " verlässt geteilte Schiene :(");
        lok1Sem.release();
    }

    //Hier wird nun Lok1 acquired. Es darf jetzt die Lok0 weiterhin nicht fahren.
    // Und weil später in exitLok1 nur Lok0 released wird, wird Lok 1 dazu gezwungen auf Lok0 zu warten im Anschluss
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
