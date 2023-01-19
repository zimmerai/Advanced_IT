package Aufgabe_14.LoesungPagniaDanachAufgabe15;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerDispatcher {
    public static final int port = 4999;
    public static final int maxLength = 65507;

    private static final String PATH = System.getProperty("user.home") + "/Desktop/Messages/";

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}
        try {
            DatagramSocket s = new DatagramSocket(port);
            while (true) {
                try {
                    byte[] output = new byte[maxLength];
                    DatagramPacket outP = new DatagramPacket(output, maxLength);
                    s.receive(outP);
                    int length = outP.getLength();
                    String message = "";
                    message = new String(output, 0, length);
                    if (!message.equals("")) {
                        System.out.println(outP.getAddress().toString() + " send: " + message + " to the server");
                        String[] arguments = message.split(" ", 2);
                        ServerThread newThread = new ServerThread(outP, arguments[0], arguments[1], PATH, s, host);
                        newThread.start();
                        message = "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
