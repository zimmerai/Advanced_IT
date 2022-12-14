package AufgabenTeilB;

import AufgabenTeilA.Lok;

public class LokThread extends Thread{
    private int lokId;

    private AufgabenTeilA.Lok lok;

    private final int ownTrack = 1000;
    private final int sharedTrack = 500;

    public LokThread(int lokId, Lok lok){
        this.lokId =lokId;
        this.lok = lok;
    }

    @Override
    public void run() {
        if (lokId == 0) {
            while (true) {
                try {
                    System.out.println(lok.getLokName() + " fährt auf eigener Schiene");
                    Thread.sleep(Math.round(ownTrack / lok.getSpeed()));

                    lok.enterLok0();

                    Thread.sleep(Math.round(sharedTrack / lok.getSpeed()));

                    lok.exitLok0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (true) {
                try {
                    System.out.println(lok.getLokName() + " fährt auf eigener Schiene");
                    Thread.sleep(Math.round(ownTrack / lok.getSpeed()));

                    lok.enterLok1();

                    Thread.sleep(Math.round(sharedTrack / lok.getSpeed()));

                    lok.exitLok1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}