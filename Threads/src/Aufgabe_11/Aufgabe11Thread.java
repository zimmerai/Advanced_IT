package Aufgabe_11;

public class Aufgabe11Thread extends Thread {

    private int id;
    private Philosoph p;
    private Gabel links, rechts;

    public Aufgabe11Thread(int id, Philosoph p, Gabel links, Gabel rechts) {
        this.id = id;
        this.p = p;
        this.links = links;
        this.rechts = rechts;
    }

    public void run() {

        System.out.println(p.getName() + " (" + p.getC() + ") will essen!");

        try {
            links.acquire();
            rechts.acquire();

            System.out.println("        " + p.getName() + " (" + p.getC() + ") isst!");

            Thread.sleep(Math.round(Math.random()*10000));

            System.out.println("                " + p.getName() + " (" + p.getC() + ") fertig");

            links.release();
            rechts.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

