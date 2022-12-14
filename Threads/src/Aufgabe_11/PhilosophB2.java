package Aufgabe_11;

public class PhilosophB2 {
    private int zustand;
    private String name;

    private int id;
    private boolean kannEssen;

    public PhilosophB2(int id,int zustand, String name) {
        this.kannEssen = true;
        this.zustand = zustand;
        this.id = id;
        this.name = name;
    }

    public int getZustand() {
        return zustand;
    }

    public void setZustand(int zustand) {
        this.zustand = zustand;
    }

    public String getName() {
        return name;
    }

    public boolean isKannEssen() {
        return kannEssen;
    }

    public void setKannEssen(boolean kannEssen) {
        this.kannEssen = kannEssen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
