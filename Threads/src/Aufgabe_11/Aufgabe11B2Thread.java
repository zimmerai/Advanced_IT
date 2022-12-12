package Aufgabe_11;

import java.util.concurrent.Semaphore;

public class Aufgabe11B2Thread extends Thread{

    public static final int DENKEN = 0;
    public static final int HUNGER = 1;
    public static final int ESSEN = 2;

    private Semaphore mutex;
    
    private PhilosophB2[] philosophen;
    private Semaphore[] schlange;
    private PhilosophB2 me;
    private PhilosophB2 rechterNachbar;
    private PhilosophB2 linkerNachbar;

    public Aufgabe11B2Thread(Semaphore[] schlange, PhilosophB2[] philosophen,Semaphore mutex, PhilosophB2 me, PhilosophB2 linkerNachbar, PhilosophB2 rechterNachbar){
        this.schlange =schlange;
        this.philosophen = philosophen;
        this.mutex = mutex;
        this.me = me;
        this.rechterNachbar = rechterNachbar;
        this.linkerNachbar = linkerNachbar;
    }

    @Override
    public void run() {
        me.setZustand(DENKEN);
        System.out.println(me.getName() + " (" + me.getId() + ") denkt!");

        try{
        Thread.sleep(Math.round(Math.random()*1000));
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(me.getName() + " (" + me.getId() + ") will essen!");
        try {
            mutex.acquire();
            if (me.isKannEssen()) {
                rechterNachbar.setKannEssen(false);
                linkerNachbar.setKannEssen(false);
                schlange[me.getId()].release();
                me.setZustand(ESSEN);
            } else {
                me.setZustand(HUNGER);
            }
            mutex.release();
            schlange[me.getId()].acquire();


            System.out.println("        " + me.getName() + " (" + me.getId() + ") isst!");
            try{
                Thread.sleep(Math.round(Math.random()*1000));
                System.out.println(me.getName() + " (" + me.getId() + ") ist fertig!");
                me.setZustand(DENKEN);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mutex.acquire();
            if (rechterNachbar.getId() == 4){
                if(philosophen[0].getZustand() != ESSEN) {
                    schlange[rechterNachbar.getId()].release();
                    rechterNachbar.setKannEssen(true);
                }
            } else {
                if (philosophen[rechterNachbar.getId()+1].getZustand() != ESSEN) {
                    schlange[rechterNachbar.getId()].release();
                    rechterNachbar.setKannEssen(true);
                }
            }

            if (linkerNachbar.getId() == 0) {
                if(philosophen[4].getZustand() != ESSEN) {
                    schlange[linkerNachbar.getId()].release();
                    linkerNachbar.setKannEssen(true);
                }
            } else {
                if (philosophen[linkerNachbar.getId()-1].getZustand() != ESSEN) {
                    schlange[linkerNachbar.getId()].release();
                    linkerNachbar.setKannEssen(true);
                }
            }
            mutex.release();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
