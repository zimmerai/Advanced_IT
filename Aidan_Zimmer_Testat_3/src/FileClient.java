import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FileClient {
    public static final int port = 4999;
    public static final int maxLength = 65507;

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {host = args[0];}

        try {
            //Tastatureingabe
            System.out.println("Welcome to our File Sharing Platform. Please post or request what you like");
            DatagramSocket s = new DatagramSocket();
            sendMessage("READ data1,1", host, s);
            sendMessage("READ data1,2", host, s);
            sendMessage("READ data1,3", host, s);
            sendMessage("WRITE data1,4,gut!", host, s);
            sendMessage("WRITE data1,4,gut!", host, s);
            sendMessage("WRITE data1,4,gut!", host, s);
            sendMessage("READ data1,5", host, s);
            sendMessage("READ data1,6", host, s);
            sendMessage("READ data1,1", host, s);
            sendMessage("READ data1,2", host, s);
            sendMessage("READ data1,3", host, s);
            while (true) {
                    byte[] output = new byte[maxLength];
                    DatagramPacket outP = new DatagramPacket(output, maxLength);
                    s.receive(outP);
                    String message = null;
                    message = new String(outP.getData(), 0 , outP.getLength());
                    if (!message.equals(null)) {
                        System.out.println(message);
                    }
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    public static void sendMessage(String message, String host, DatagramSocket s) throws IOException {
        byte[] sendMessage = message.getBytes();
        DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(host).getLoopbackAddress(), port);
        s.send(packet);
    }
}
