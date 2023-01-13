package Aufgabe_13;

import java.io.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    public static final int port = 4999;
    public static final int maxLength = 65507;

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}

        try {
            //Tastatureingabe
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to our Chat Platform. Please post what's on you mind");
            String input = "";
            byte[] sendMessage;

            while (true) {
                DatagramSocket s = new DatagramSocket();
                input = userIn.readLine();
                while (!input.equals("")) {
                    sendMessage = input.getBytes();
                    DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(host).getLoopbackAddress(), port);
                    s.send(packet);
                    input = "";
                }
                if (s != null) {
                    s.close();
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
