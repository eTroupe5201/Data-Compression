package dataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class RunLengthExpand {
	private static final int RUN_SIZE    = 256;			//max size of run encoded
	private static final int LENGTH_OF_ENCODING = 8;	//number of digits to encode the size 
        public static File output;
	public static File input;
	
    RunLengthExpand() { }

	public static void expand(File inputFile, File outputFile) throws IOException { 
		
		input = inputFile;
		output = outputFile;
		
		FileInputStream currentStream = new FileInputStream(inputFile);
		System.setIn(currentStream);

		FileOutputStream outputStream = new FileOutputStream(outputFile);
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
	}
}
