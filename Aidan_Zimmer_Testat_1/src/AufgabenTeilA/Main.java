package AufgabenTeilA;

public class Main {
    //Unterschiedliche Testfälle
    public static void main(String[] args) {

        //Testfall 1 - Lok 0 schneller als Lok 1
//        Lok jokersLok = new Lok(2.0D, "Joker", "Gleisräuber");
//        Lok batmansLok = new Lok(1.0D, "Batman", "BatLok");
//
//        LokThread lok0 = new LokThread(0, jokersLok);
//        LokThread lok1 = new LokThread(1, batmansLok);

        //Testfall 2 - Lok 1 schneller als Lok 0
//        Lok jokersLok = new Lok(1.0D, "Joker", "Gleisräuber");
//        Lok batmansLok = new Lok(2.0D, "Batman", "BatLok");
//
//        LokThread lok0 = new LokThread(0, jokersLok);
//        LokThread lok1 = new LokThread(1, batmansLok);

        //Testfall 3 - beide Loks gleich schnell
        Lok jokersLok = new Lok(1.0D, "Joker", "Gleisräuber");
        Lok batmansLok = new Lok(1.0D, "Batman", "BatLok");

        LokThread lok0 = new LokThread(0, jokersLok);
        LokThread lok1 = new LokThread(1, batmansLok);

        lok0.start();
        lok1.start();
    }
}