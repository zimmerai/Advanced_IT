import java.net.DatagramPacket;
import java.util.LinkedList;

public class DispatcherQueue {
    private static LinkedList<DatagramPacket> messages = new LinkedList<DatagramPacket>();

    public synchronized void addMessage(DatagramPacket message){
        messages.add(message);
        notify();
    }

    public synchronized DatagramPacket removeMessage(){
        //Warten, wenn die Queue leer ist
        while (messages.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return messages.remove();
    }


}
