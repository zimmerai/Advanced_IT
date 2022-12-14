package AufgabenTeilB;

import AufgabenTeilA.Lok;
import AufgabenTeilA.LokThread;

public class Main {
    public static void main(String[] args) {
        AufgabenTeilA.Lok jokersLok = new AufgabenTeilA.Lok(2.0D, "Joker", "Gleisräuber");
        AufgabenTeilA.Lok batmansLok = new Lok(1.0D, "Batman", "BatLok");

        AufgabenTeilA.LokThread lok0 = new AufgabenTeilA.LokThread(0, jokersLok);
        AufgabenTeilA.LokThread lok1 = new LokThread(1, batmansLok);

        lok0.start();
        lok1.start();
    }
}