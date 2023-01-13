package Aufgabe_14;

import java.net.*;
import java.io.*;

public class FileServer {

    public static final int port = 4999;
    public static final int maxLength = 65507;

    private static final String PATH = System.getProperty("user.home") + "/Desktop/Messages/";

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}
        String answer = "";
        while (true){
            try(DatagramSocket s = new DatagramSocket(port);) {
                byte[] output = new byte[maxLength];
                DatagramPacket outP = new DatagramPacket(output, maxLength);
                s.receive(outP);
                int length = outP.getLength();
                String message = null;
                message = new String(output, 0 , length);
                String[] arguments = message.split(" ", 2);
                if(arguments[0].equals("READ")){
                    String[] cmdParts = arguments[1].split(",", 2);
                    try {
                        BufferedReader fileIn = new BufferedReader(new FileReader(PATH + cmdParts[0] + ".txt"));
                        //Inhalt aus Datei auslesen und zurückgeben
                        String fileContent = "";
                        for (int i=0; i<Integer.parseInt(cmdParts[1]); i++){
                            fileContent = fileIn.readLine();
                        }
                        answer = "Answer: " + fileContent;
                    } catch (Exception e) {
                        answer = e.getMessage();
                    }
                } else {
                    answer = "something went wrong";
                }
                while (!answer.equals("")) {
                    byte[] sendMessage = answer.getBytes();
                    DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(host).getLoopbackAddress(), 21341);
                    s.send(packet);
                    answer = "";
                }
                if (!message.equals(null)) {
                    System.out.println(outP.getAddress().toString() + " send: " + message + " to the server");
                }
            } catch (IOException e) {e.printStackTrace();}

        }
    }
}
