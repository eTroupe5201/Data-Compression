package dataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class RunLengthExpand {
	private static InputStream currentStream;
	private static final int RUN_SIZE    = 256;			//max size of run encoded
	private static final int LENGTH_OF_ENCODING = 8;	//number of digits to encode the size 
    public static FileOutputStream outputStream;
    public static File outputFile;
	
    RunLengthExpand() { }

	    public static void CallFileSaver() throws IOException {
    	String requester = "RUN LENGTH EXPAND";
    	new FileSaver(requester);
    }
	    
    public static void ReceiveOutputFile(File file) {//from file saver?
    	System.out.println("File recieved: " + file);
    	outputFile = file; //from file saver 
    }
   
	public static void expand(File file) throws IOException { 
		
    	if(outputFile == null) {
    	 CallFileSaver();
    	}
    	
    	currentStream = new FileInputStream(file);
    	System.setIn(currentStream);
    
    	outputStream = new FileOutputStream(outputFile);
    	System.setOut(new PrintStream(outputStream));

    	 boolean b = false; 
	        while (!BinaryStdIn.isEmpty()) {
	            int run = BinaryStdIn.readInt(LENGTH_OF_ENCODING); 
	            for (int i = 0; i < run; i++) 	 
	            	BinaryStdOut.write(b);
	            b = !b;
	        }
	        BinaryStdOut.close();
		currentStream.close();
	        outputStream.close();
		System.exit(0);
	}
}
