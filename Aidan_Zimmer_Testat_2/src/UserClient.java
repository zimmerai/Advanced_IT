import java.io.*;
import java.net.*;
import java.util.UUID;

public class UserClient {
    public static final int clientPort = 12345;

    public static void main(String[] args) {
        //lokaler host deswegen auf lokalhost gestartet
        String host = "localhost";
        Socket s = null;
        BufferedReader networkIn = null;
        PrintWriter networkOut = null;

        try {
            //Tastatureingabe und Client starten
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Verbunden! (Port: " + clientPort + ")" + " - Beenden sie den Client mit \"END\"");
            s = new Socket(host, clientPort);
            String input;
            while (true) {
                input = userIn.readLine();
                //aufhören bei END
                if(input.equals("END")){
                    System.out.println("Wird beendet");
                    break;
                }
                networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                networkOut = new PrintWriter(s.getOutputStream());
                //Absenden der Nachricht
                networkOut.println(input);
                networkOut.flush();
                //Antwort ausgeben
                System.out.println(networkIn.readLine());

                //wieder alles aufräumen damit es funktioniert (sonst bricht die App)
                if(networkIn != null)
                    networkIn.close();
                if(networkOut != null)
                    networkOut.close();
                if(s != null)
                    s.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //alle noch offenen Ressourcen schließen bei fehler oder Beendigung
            //Da wir nicht wussten in der Vorlesung, was alles geschlossen werden muss mach ich einfach mal alles
            try {
                if (networkIn != null){
                    networkIn.close();
                }
                if (networkOut != null) {
                    networkOut.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
