import java.io.*;

public class MyFile {
	String fileName;

	int writerCount = 0;
	boolean activeWriter = false;
	boolean activeReader = false;
	public MyFile (String fileName){
		this.fileName = fileName;
	}

	public String read (int lineNo, int id){
		String answer = "*** ERROR 910: cannot open file for reading"; 
		BufferedReader f = null;
		try {
			System.out.println("Der Thread" + id + " will lesen.");
			f = new BufferedReader(new FileReader(fileName)); 
			String s = "*** ERROR 911: READ failed - line not found in file";
			this.startRead(id);
			System.out.println("Der Thread" + id + " beginnt zu lesen.");
			for (int i = 0; (i<lineNo) && (s != null); i++){
				s = f.readLine();
			}
			this.endRead();
			System.out.println("Der Thread" + id + " ist fertig.");
			if (s != null)
				answer = "The file read: " + s;
			else
				answer = "*** ERROR 911: READ failed - line not found in file"; 
		} catch (Exception e) {e.printStackTrace();}
		if (f != null){
			try{ f.close(); } catch(Exception e){e.printStackTrace();}
		}
		return answer;
	}//read
	
	public String write (int lineNo, String data, int id){
		String answer = "*** ERROR 920: cannot open file for writing"; 
		BufferedReader inFile = null;
		PrintWriter outFile = null;
		boolean found = false; // is lineNo in inFile?

		try{
			System.out.println("Der Thread" + id + " will schreiben.");
			inFile = new BufferedReader(new FileReader(fileName)); 
			outFile = new PrintWriter(new FileWriter(fileName+".temp"));
			answer = "*** ERROR 921: WRITE failed - line not found in file"; 
			String s = "";
			this.startWrite(id);
			System.out.println("Der Thread" + id + " beginnt zu schreiben.");
			for (int i = 0; s != null; i++){
				s = inFile.readLine();
				if (i==lineNo-1){
					found = true;
					outFile.println(data);
				}
				else if (s!= null) {
					outFile.println(s);
				}
			}//for
			this.endWrite();
			System.out.println("Der Thread" + id + " ist fertig.");
		} catch(Exception e){ e.printStackTrace(); }
		if (inFile != null){
			try{ inFile.close(); } catch(Exception e){e.printStackTrace();}
		}
		if (outFile != null){
			try{ outFile.close(); } catch(Exception e){e.printStackTrace();}
		}
		if (found){
		    answer = "The File was written with: " + data;
		    try{
			// note that all files must be closed for renaming!
			File f1 = new File(fileName);
			File f2 = new File(fileName+".temp");
			File f3 = new File(fileName+".bak");
			f3.delete(); // possibly required for subsequent rename
			f1.renameTo(f3); // origin is new backup file
			f2.renameTo(f1); // make changes stable
		    } catch(Exception e){e.printStackTrace();}
		}//if

		return answer;
	}//write

	//Monitor Methoden für Leser-Schreiber Problem

	//Eintrittsprotokoll für K.A lesen
	public synchronized void startRead(int id) throws InterruptedException {
		//nur lesen, wenn nicht geschrieben wird
		while (this.writerCount > 0){
			System.out.println("Der Thread" + id + " wartet.");
			wait();
		}
		this.activeReader=true;
		notifyAll();
	}

	//Austrittsprotokoll für K.A lesen
	public synchronized void endRead() {
		this.activeReader=false;
		notifyAll();
	}

	//Eintrittsprotokoll für K.A schreiben
	public synchronized void  startWrite(int id) throws InterruptedException {
		//Schreiberpriorität
		this.writerCount++;

		//wenn es bereits einen aktiven Schreiber gibt, oder gelesen wird warte
		while (this.activeReader || this.activeWriter){
			System.out.println("Der Thread" + id + " wartet.");
			wait();
		}

		this.activeWriter = true;
	}

	//Austrittsprotokoll für K.A schreiben
	public synchronized void endWrite(){
		this.activeWriter = false;
		this.writerCount--;
		notifyAll();
	}
}//class
