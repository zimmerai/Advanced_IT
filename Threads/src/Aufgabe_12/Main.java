package Aufgabe_12;

import Aufgabe_11.PhilosophB2;

public class Main {
    public static void main(String[] args) {
        Gabel g1 = new Gabel();
        Gabel g2 = new Gabel();
        Gabel g3 = new Gabel();
        Gabel g4 = new Gabel();
        Gabel g5 = new Gabel();

        Philosoph S = new Philosoph(g1, g2, "Sokrates");
        Philosoph K = new Philosoph(g2,g3, "Kant");
        Philosoph F = new Philosoph(g3,g4, "Freud");
        Philosoph P = new Philosoph(g4,g5, "Platon");
        Philosoph A = new Philosoph(g5,g1, "Aristoteles");

        S.start();
        K.start();
        F.start();
        P.start();
        A.start();
    }

}
