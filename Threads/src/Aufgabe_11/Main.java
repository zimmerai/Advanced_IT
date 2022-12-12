package Aufgabe_11;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1, true);

        Semaphore[] schlange = new Semaphore[5];
        for (int i = 0; i < 5; i++){
            schlange[i] = new Semaphore(0, true);
        }

        PhilosophB2 S = new PhilosophB2(0,'S', "Sokrates");
        PhilosophB2 K = new PhilosophB2(1,'K', "Kant");
        PhilosophB2 F = new PhilosophB2(2,'F', "Freud");
        PhilosophB2 P = new PhilosophB2(3,'P', "Platon");
        PhilosophB2 A = new PhilosophB2(4,'A', "Aristoteles");

        PhilosophB2[] philosophs = {S,K,F,P,A};

        Aufgabe11B2Thread s = new Aufgabe11B2Thread(schlange,philosophs, mutex, S, A, K);
        Aufgabe11B2Thread k = new Aufgabe11B2Thread(schlange,philosophs, mutex, K, S, F);
        Aufgabe11B2Thread f = new Aufgabe11B2Thread(schlange,philosophs, mutex, F, K, P);
        Aufgabe11B2Thread p = new Aufgabe11B2Thread(schlange,philosophs, mutex, P, F, A);
        Aufgabe11B2Thread a = new Aufgabe11B2Thread(schlange,philosophs, mutex, A, P, S);

        s.start();
        k.start();
        f.start();
        p.start();
        a.start();

    }

}
