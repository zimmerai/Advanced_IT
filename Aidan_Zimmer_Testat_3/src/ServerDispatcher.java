import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerDispatcher {
    private static final int port = 4999;
    private static final int maxLength = 65507;
    private static final String PATH = System.getProperty("user.home") + "/Desktop/Messages/";
    private DispatcherQueue queue;
    private WorkerThread[] threadBuffer;
    private  MyFile[] fileBuffer;


    public ServerDispatcher(DatagramSocket s, String host, int size) {
        //Erstellen der Monitor Queue
        DispatcherQueue queue = new DispatcherQueue();
        this.queue = queue;
        //Setup für die Worker Threads und die zwei Files im Fileserver
        //Es existieren nur zwei Files im Server data1.txt und data2.txt
        MyFile data1 = new MyFile(PATH + "data1" + ".txt");
        MyFile data2 = new MyFile(PATH + "data2" + ".txt");
        MyFile[] fileBuffer = {data1,data2};
        this.fileBuffer = fileBuffer;
        //Erstellen der Workerpools und übergeben der gemeinsamen Queue
        this.threadBuffer = new WorkerThread[size];
        for (int i = 0; i<size; i++){
            this.threadBuffer[i] = new WorkerThread(i, s, host, queue, fileBuffer);
            //Threadpool starten
            threadBuffer[i].start();
        }
    }

    public static void main(String[] args) {
        //Starte Dispatcher
        String host = "localhost";
        if (args.length > 0) {host = args[0];}
        try {
            DatagramSocket s = new DatagramSocket(port);
            ServerDispatcher dispatcher = new ServerDispatcher(s,host, 6);
            while (true) {
                try {
                    byte[] output = new byte[maxLength];
                    //erhalte Datagrampacket
                    DatagramPacket dp = new DatagramPacket(output, output.length);
                    s.receive(dp);
                    String message = "";
                    message = new String(dp.getData(), 0, dp.getLength());
                    if (!message.equals("")) {
                        System.out.println(dp.getAddress().toString() + " send: " + message + " to the server");
                        //Herausfinden welche File gewählt wurde
                        String[] arguments = message.split(" ", 2);
                        String[] messageParts = arguments[1].split(",", 3);
                        String fileName = messageParts[0];
                        //Wenn die richtigen Filenamen angegeben werden führe es aus
                        if (fileName.equals("data1") | fileName.equals("data2")){
                            dispatcher.queue.addMessage(dp);
                        } else {
                            byte[] sendMessage = "This file doesn't exist".getBytes();
                            try {
                                DatagramPacket packet = new DatagramPacket(sendMessage, sendMessage.length, dp.getAddress(), dp.getPort());
                                s.send(packet);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
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
