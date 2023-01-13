package Aufgabe_13;

import java.net.*;
import java.io.*;

public class ChatServer {

    public static final int port = 4999;
    public static final int maxLength = 65507;
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}

        while (true){
            try {
                byte[] output = new byte[maxLength];
                DatagramSocket s = new DatagramSocket(port);
                DatagramPacket outP = new DatagramPacket(output, maxLength);
                s.receive(outP);
                int length = outP.getLength();
                String message = null;
                message = new String(output, 0 , length);
                if (!message.equals(null)) {
                    System.out.println(outP.getAddress().toString() + " send: " + message + " to the server");
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {e.printStackTrace();}

        }
    }
}
