package AufgabenTeilB;

import java.util.concurrent.Semaphore;

public class Lok {

    //Die Arrays werden direkt initialisiert und nicht im Kostruktor
    private boolean[] waiting = {false,false};

    //isTrackFree garantiert, dass nicht gleichzeitig befahren wird
    private boolean isTrackFree = true;

    //nextLok garantiert die abwechselnde Fahrt
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

    /*
    Wenn der Track frei ist UND die Lok an der Reihe ist, wird released sonst wird die Lok auf waiting gesetzt.
    Da diese Aktionen teilweise nicht atomar sind, brauchen wir dieses mutex Semaphore.
    Erst nach dem acquire steht der Konsolen Output, falls die Lok warten muss, wir bei Befreiung direkt die Schiene befahren, somit ist der Output hier korrekt.
     */
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
    /*
    Hier werden ebenfalls nicht atomare Abfragen mit dem mutex geschützt.
    Es können zwei Fälle auftreten: Die zweite Lok wartet bereits, hier wird einfach released und die zweite Lok darf auf die gemeinsame Schiene
    Oder die Lok wartet noch nicht in dem Fall wird der nextLok index auf die nächste Lok gelegt und die isTrackFree Flag auf true, sodass das Eintrittsprotokoll ermöglicht wird.
     */
    public void exitLok0() throws InterruptedException {
        System.out.println(lokName + " verlässt geteilte Schiene :(");
        mutex.acquire();
        if (waiting[1] == true) {
            privSem[1].release();
        } else {
            isTrackFree = true;
            nextLok = 1;
        }
        mutex.release();
    }

    //Kommentare wie oben :D
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
        if (waiting[0] == true) {
            privSem[0].release();
        } else {
            nextLok = 0;
            isTrackFree = true;
        }
        mutex.release();
    }

    public double getSpeed() {
        return speed;
    }

    public String getLokName() {
        return lokName;
    }
}
