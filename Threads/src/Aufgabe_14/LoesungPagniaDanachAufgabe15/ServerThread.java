package Aufgabe_14.LoesungPagniaDanachAufgabe15;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerThread extends Thread {

    public MyFile file;
    String command;
    String fileName;
    int lineNr;
    String data;

    DatagramSocket s;

    String host;

    DatagramPacket dp;

    public ServerThread(DatagramPacket dp, String commmand, String message, String PATH, DatagramSocket s, String host) {
        String[] arguments = message.split(",", 3);
        this.dp = dp;
        this.command = commmand;
        this.fileName = arguments[0];
        this.lineNr = Integer.parseInt(arguments[1]);
        if (arguments.length >= 3) {
            this.data = arguments[2];
        }
        MyFile file = new MyFile(PATH + fileName + ".txt");
        this.file = file;
        this.s = s;
        this.host = host;
    }

    @Override
    public void run() {
        String answer = "";
        if (this.command.equals("READ")) {
            System.out.println(this.command);
            answer = file.read(this.lineNr);
            System.out.println(answer);
        } else if (this.command.equals("WRITE")) {
            answer = file.write(this.lineNr, this.data);
        } else {
            answer = "something went wrong";
        }
        while (!answer.equals("")) {
            byte[] sendMessage = answer.getBytes();
            try {
                DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, dp.getAddress(), dp.getPort());
                s.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            answer = "";
        }
    }
}
