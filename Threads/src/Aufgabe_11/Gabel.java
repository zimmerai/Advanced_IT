package Aufgabe_11;

import java.util.concurrent.Semaphore;

public class Gabel extends Semaphore {
    public Gabel(int wert, boolean fifo) {
        super(wert, fifo);
    }
}