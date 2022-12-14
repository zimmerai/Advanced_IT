package AufgabenTeilA;

public class Main {
    public static void main(String[] args) {
        Lok jokersLok = new Lok(2.0D, "Joker", "Gleisräuber");
        Lok batmansLok = new Lok(1.0D, "Batman", "BatLok");

        LokThread lok0 = new LokThread(0, jokersLok);
        LokThread lok1 = new LokThread(1, batmansLok);

        lok0.start();
        lok1.start();
    }
}