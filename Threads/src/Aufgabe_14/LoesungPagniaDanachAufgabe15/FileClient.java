package Aufgabe_14.LoesungPagniaDanachAufgabe15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileClient {
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
                DatagramSocket s = new DatagramSocket();
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
