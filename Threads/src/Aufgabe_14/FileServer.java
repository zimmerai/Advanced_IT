package Aufgabe_14;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class FileServer {

    public static final int port = 4999;
    public static final int maxLength = 65507;

    private static final String PATH = System.getProperty("user.home") + "/Desktop/Messages/";

    public FileServer(){
        //noch nichts bis jetzt
    }
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}
        FileServer server = new FileServer();
        server.run(host);
    }
    public void run(String host) {
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
                   answer = handleRead(arguments[1]);
                } else if (arguments[0].equals("WRITE")) {
                    answer = handleWrite(arguments[1]);
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

    private String handleWrite(String command) {
        String[] arguments = command.split(",", 3);
        try{
            //Inhalt aus Datei auslesen und zurückgeben
            ArrayList<String> fileContents = new ArrayList<String>();
            BufferedReader fileIn = new BufferedReader(new FileReader(PATH + arguments[0] + ".txt"));
            String content = "";
            for (int i=0; i < Integer.parseInt(arguments[1]); i++){
                System.out.println(content);
                content = fileIn.readLine();
                if (content == null)
                    content = "";
                fileContents.add(content);
            }
            PrintWriter fileOut = new PrintWriter(new FileWriter(PATH + arguments[0] + ".txt"));
            for (String value : fileContents){
                fileOut.println(value);
                fileOut.flush();
            }
            fileOut.println(arguments[2]);
            fileOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
        return "File was updated successfully";
    }

    public String handleRead(String command){
        String[] cmdParts = command.split(",", 2);
        try(BufferedReader fileIn = new BufferedReader(new FileReader(PATH + cmdParts[0] + ".txt"))) {
            //Inhalt aus Datei auslesen und zurückgeben
            String fileContent = "";
            for (int i=0; i<Integer.parseInt(cmdParts[1]); i++){
                fileContent = fileIn.readLine();
            }
            if (fileContent == null){
                return "There is nothing saved on this line";
            } else {
                return "Answer: " + fileContent;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
