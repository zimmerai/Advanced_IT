import java.io.*;
import java.net.*;
import java.util.UUID;

public class pasteBinServer {
    public static final int serverPort = 12345;
    //falls Sie nicht die selbe Ordnung im Dateisystem sollte diese code hoffentlich ihren Messages Ordner finden
    private static final String PATH = System.getProperty("user.home") + "/Desktop/Messages/";

    public static void main(String[] args) {
        ServerSocket server = null;
        String inputMsg = null;
        String answer = null;
        //Erstellen von Server
        try {
            server = new ServerSocket(serverPort);
            System.out.println("Pastebin Server gestarted (Port: " + serverPort + ")!");
        } catch (IOException e) {
            System.err.println(e);
        }
        //Endlosschleife für den Server
        while (true){
            //hier ein try with ressource um das auszuprobieren, was Paul in der Vorlesung vorgeschlagen hat
            //Dem Anschein nach funktioniert das
            try (Socket s = server.accept();
                 BufferedReader networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter networkOut = new PrintWriter(s.getOutputStream());){

                inputMsg = networkIn.readLine();
                //Wenn ein Input erkannt wird, ermittle die Antwort gemäß der Nachricht
                if(inputMsg != null){
                    answer = handleInput(inputMsg);
                    networkOut.println(answer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String handleInput(String input){
        //UUID sollte hoffentlich genug Variation für Filenamen bieten
        UUID fileId = UUID.randomUUID();
        //Message in zwei Teile spalten (Befehl und Nachricht)
        String[] args = input.split(" ", 2);
        //Standard Nachricht, falls Eingabefehler getroffen werden
        String answer = "Syntax Error! The request needs to follow one of the following formats: \"SAVE <message>\" or \"GET <key>\"";
         if (args[0].equals("SAVE")) {
            //File Klasse erstellen, aber erst File wirklich erstellen, wenn sie noch nicht existiert, sonst einfach anhängen
            File newFile = new File(PATH, fileId.toString()+".txt");
            if (!newFile.exists()) {
                //File erstellen, da noch nicht existent
                try {
                    newFile.createNewFile();
                } catch (IOException e) {
                    //Fehlerausgabe
                    e.printStackTrace();
                }
            }
            try {
                PrintWriter writer = new PrintWriter(newFile);
                writer.println(args[1]);
                answer = "KEY " + fileId;
                writer.flush();
                //nach dem schreiben schließen
                writer.close();
            } catch (IOException e) {
                //Fehlerausgabe und Antwort
                e.printStackTrace();
                answer = "Es gab ein Problem beim Server, bitte starten Sie neu";
            }
        } else if (args[0].equals("GET")) {
            try {
                BufferedReader networkIn = new BufferedReader(new FileReader(PATH + args[1] + ".txt"));
                //Inhalt aus Datei auslesen und zurückgeben
                String fileContent = networkIn.readLine();
                if (fileContent != null) {
                    answer = "OK " + fileContent;
                }
            } catch (FileNotFoundException e) {
                //Fehlerausgabe und Antwort für Eingabefehler
                e.printStackTrace();
                answer = "Es gab ein Problem beim Auslesen der Nachricht. Bitte überprüfen sie nochmal die Korrektheit ihrer Schlüsseleingabe";
            } catch (IOException e) {
                //Fehlerausgabe und Antwort für Serverfehler
                e.printStackTrace();
                answer = "Es gab ein Problem beim Server, bitte starten Sie neu";
            }
        }

        return answer;

    }
}
