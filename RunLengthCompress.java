package dataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class RunLengthCompress {
	File file;
	
	private static final int RUN_SIZE    = 256;			//max size of run encoded
	private static final int LENGHTH_OF_ENCODING = 8;	//number of digits to encode the size 
	
	private static InputStream currentStream;
    public static FileOutputStream outputStream;
    public static File outputFile;
	
public RunLengthCompress() {
		// TODO Auto-generated constructor stub
	}
	    public static void CallFileSaver() throws IOException {
    	System.out.println("Called the save file");
    	String requester = "RUN LENGTH COMPRESS";
    	new FileSaver(requester);
    }
	    
    public static void RecieveOutputFile(File fileSaverOutputFile) {
    	System.out.println("File recieved: " + fileSaverOutputFile);
    	outputFile = fileSaverOutputFile; //from file saver 
    }
    
    public static void compress(File file) throws IOException { 	
    	System.out.println(outputFile);
    	
    	if(outputFile == null) {
    	 CallFileSaver();
    	}
    	currentStream = new FileInputStream(file);
    	System.setIn(currentStream);
    	
    	System.out.println("OUTPUT FILE:" + outputFile);
    
    	outputStream = new FileOutputStream(outputFile);
    	System.setOut(new PrintStream(outputStream));
    	
    	System.out.println("output file set to: "+ outputFile.toString());    

    	System.out.println("Should read to Binary Stand in");
    	System.out.println("In Run Length Compress:");

    	 char run = 0; 
	        boolean b = false;
	        boolean old = false; 
	        while (!BinaryStdIn.isEmpty()) { 
	            b = BinaryStdIn.readBoolean();
	            if (b != old) {
	                BinaryStdOut.write(run, LENGHTH_OF_ENCODING); 
	                run = 1; 
	                old = !old;
	            }
	            else { 
	                if (run == RUN_SIZE-1) { 
	                    BinaryStdOut.write(run, LENGHTH_OF_ENCODING);
	                    run = 0;
	                    BinaryStdOut.write(run, LENGHTH_OF_ENCODING);
	                }
	                run++; 
	            } 
	        } 
	        BinaryStdOut.write(run, LENGHTH_OF_ENCODING);
	        BinaryStdOut.close();
	    
        
        System.out.println("In Run Length Compress Again ");
        
    }
}
