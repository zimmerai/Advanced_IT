package Aufgabe_14.LoesungPagniaDanachAufgabe15;

import java.io.*;

public class MyFile {
	String fileName;

	public MyFile (String fileName){
		this.fileName = fileName;
	}

	public String read (int lineNo){
		String answer = "*** ERROR 910: cannot open file for reading"; 
		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader(fileName)); 
			String s = "*** ERROR 911: READ failed - line not found in file";
			for (int i = 0; (i<lineNo) && (s != null); i++)
				s = f.readLine();
			if (s != null)
				answer = s;
			else
				answer = "*** ERROR 911: READ failed - line not found in file"; 
		} catch (Exception e) {e.printStackTrace();}
		if (f != null){
			try{ f.close(); } catch(Exception e){e.printStackTrace();}
		}
		return answer;
	}//read
	
	public String write (int lineNo, String data){
		String answer = "*** ERROR 920: cannot open file for writing"; 
		BufferedReader inFile = null;
		PrintWriter outFile = null;
		boolean found = false; // is lineNo in inFile?

		try{
			inFile = new BufferedReader(new FileReader(fileName)); 
			outFile = new PrintWriter(new FileWriter(fileName+".temp"));
			answer = "*** ERROR 921: WRITE failed - line not found in file"; 
			String s = "";
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
		} catch(Exception e){ e.printStackTrace(); }
		if (inFile != null){
			try{ inFile.close(); } catch(Exception e){e.printStackTrace();}
		}
		if (outFile != null){
			try{ outFile.close(); } catch(Exception e){e.printStackTrace();}
		}
		if (found){
		    answer = data;	
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
}//class
