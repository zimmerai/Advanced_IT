package Aufgabe_11;

import java.util.concurrent.Semaphore;

public class Philosoph {

    private char c;
    private String name;

    public Philosoph(char c, String name) {
        this.c = c;
        this.name = name;
    }

    public char getC() {
        return this.c;
    }

    public String getName() {
        return this.name;
    }

}