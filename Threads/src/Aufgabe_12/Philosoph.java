package Aufgabe_12;

public class Philosoph extends Thread {
    public Gabel rechteGabel;
    public Gabel linkeGabel;
    public String name;

    public Philosoph(Gabel rechteGabel, Gabel linkeGabel, String name){
        this.linkeGabel = linkeGabel;
        this.rechteGabel = rechteGabel;
        this.name = name;
    }

    public synchronized void entryEat() {
        try {
            while (!this.linkeGabel.isNotUsed() && !this.rechteGabel.isNotUsed()) { wait(); }
            this.linkeGabel.setNotUsed(false);
            this.rechteGabel.setNotUsed(false);
        }catch (Exception e) {
                e.printStackTrace();
            }
        }

    public synchronized void exitEat() {
        this.linkeGabel.setNotUsed(true);
        this.rechteGabel.setNotUsed(true);
        notify();
    }

    public void run() {
        try {
            while (true) {

                System.out.println(this.name + " denkt!");

                Thread.sleep(Math.round(Math.random() * 2000));


                System.out.println(this.name + " will essen!");

                entryEat();

                System.out.println("        " + this.name + " isst!");

                Thread.sleep(Math.round(Math.random() * 1000));

                System.out.println("                " + this.name + " ist fertig");

                exitEat();
            }
            } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }
}
