import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WorkerThread extends Thread {

    public MyFile file = null;
    String command = "";
    int lineNr = -1;
    String data = "";

    DatagramSocket s;

    String host;


    DatagramPacket dp;

    DispatcherQueue queue;

    MyFile[] fileBuffer;

    int id;
    public WorkerThread(int id, DatagramSocket s, String host, DispatcherQueue queue, MyFile[] fileBuffer) {
        this.fileBuffer = fileBuffer;
        this.s = s;
        this.host = host;
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            this.setDatagrammPacketMessageAndFile(this.queue.removeMessage());
            String answer = "";
            while (this.command.equals("")) {
            } //warten bis Thread genutzt wird
            if (this.command.equals("READ")) {
                answer = file.read(this.lineNr, this.id);
                this.command = "";
            } else if (this.command.equals("WRITE")) {
                System.out.println(this.data);
                if (this.data.equals("")){
                    answer = "No data was given";
                } else {
                    answer = file.write(this.lineNr, this.data, this.id);
                    this.command = "";
                }
            } else {
                answer = "something went wrong";
                this.command = "";
            }
            //Antwort senden, wenn Antwort existiert
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

    public void setDatagrammPacketMessageAndFile(DatagramPacket dp) {
        String message = new String(dp.getData(),0, dp.getLength());
        String[] messages = message.split(" ", 2);
        String[] arguments = messages[1].split(",", 3);
            this.dp = dp;
            this.lineNr = Integer.parseInt(arguments[1]);
            if (arguments.length >= 3) {
                this.data = arguments[2];
            }
            this.command = messages[0];
            this.file = arguments[1] == "data1" ? this.fileBuffer[0] : this.fileBuffer[1];

    }
}
