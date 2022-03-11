package dataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class RunLengthCompress {
	private static final int RUN_SIZE    = 256;			//max size of run encoded
	private static final int LENGHTH_OF_ENCODING = 8;	//number of digits to encode the size 
	public static File output;
	public static File input;

	
 public static void compress(File inputFile, File outputFile) throws IOException { 	  	
    	
    	output = outputFile;
    	input = inputFile;
    	
		FileInputStream currentStream = new FileInputStream(inputFile);
		System.setIn(currentStream);

		FileOutputStream outputStream = new FileOutputStream(outputFile);
		System.setOut(new PrintStream(outputStream));

    	System.out.println(outputFile);
    	
    	if(outputFile == null) {
    	 CallFileSaver();
    	}
    	currentStream = new FileInputStream(file);
    	System.setIn(currentStream);
    
    	outputStream = new FileOutputStream(outputFile);
    	System.setOut(new PrintStream(outputStream));

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
	        currentStream.close();
	        outputStream.close();
	    
        
    }
}
