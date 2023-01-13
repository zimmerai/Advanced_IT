package AufgabenTeilB;

import AufgabenTeilA.Lok;
import AufgabenTeilA.LokThread;

public class Main {
    //Unterschiedliche Testfälle
    public static void main(String[] args) {
        //Testfall 1 - Lok 0 schneller als Lok 1
//        AufgabenTeilA.Lok jokersLok = new AufgabenTeilA.Lok(2.0D, "Joker", "Gleisräuber");
//        AufgabenTeilA.Lok batmansLok = new Lok(1.0D, "Batman", "BatLok");
//
//        AufgabenTeilA.LokThread lok0 = new AufgabenTeilA.LokThread(0, jokersLok);
//        AufgabenTeilA.LokThread lok1 = new LokThread(1, batmansLok);

        //Testfall 2 - Lok 1 schneller als Lok 0
//        AufgabenTeilA.Lok jokersLok = new AufgabenTeilA.Lok(1.0D, "Joker", "Gleisräuber");
//        AufgabenTeilA.Lok batmansLok = new Lok(2.0D, "Batman", "BatLok");
//
//        AufgabenTeilA.LokThread lok0 = new AufgabenTeilA.LokThread(0, jokersLok);
//        AufgabenTeilA.LokThread lok1 = new LokThread(1, batmansLok);

        //Testfall 3 - beide Loks gleich schnell
        AufgabenTeilA.Lok jokersLok = new AufgabenTeilA.Lok(1.0D, "Joker", "Gleisräuber");
        AufgabenTeilA.Lok batmansLok = new Lok(1.0D, "Batman", "BatLok");

        AufgabenTeilA.LokThread lok0 = new AufgabenTeilA.LokThread(0, jokersLok);
        AufgabenTeilA.LokThread lok1 = new LokThread(1, batmansLok);

        lok0.start();
        lok1.start();
    }
}