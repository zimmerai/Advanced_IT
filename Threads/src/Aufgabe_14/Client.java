package Aufgabe_14;

import java.io.*;
import java.net.*;

public class Client {
    public static final int port = 4999;
    public static final int maxLength = 65507;

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}

        try {
            //Tastatureingabe
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to our File Sharing Platform. Please post or request what you like");
            String input = "";
            byte[] sendMessage;

            while (true) {
                byte[] output = new byte[maxLength];
                DatagramSocket s = new DatagramSocket(21341);
                input = userIn.readLine();
                while (!input.equals("")) {
                    sendMessage = input.getBytes();
                    DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(host).getLoopbackAddress(), port);
                    s.send(packet);
                    input = "";
                    DatagramPacket outP = new DatagramPacket(output, maxLength);
                    s.receive(outP);
                    int length = outP.getLength();
                    String message = null;
                    message = new String(output, 0 , length);
                    if (!message.equals(null)) {
                        System.out.println(message);
                    }
                }
                if (s != null) {
                    s.close();
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
