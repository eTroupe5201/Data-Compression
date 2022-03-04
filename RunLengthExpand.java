package DataComp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import DataComp.BinaryStdIn; //DataCompression.BinaryStdIn;
import DataComp.BinaryStdOut;//DataCompression.BinaryStdOut;

public class RunLengthExpand 
{
	private static final int RUN_SIZE    = 256;			//max size of run encoded
	private static final int LENGTH_OF_ENCODING = 8;	//number of digits to encode the size 
	
	public static void expand(String fileIn, String fileOut) throws IOException 
	{
		System.setIn(new FileInputStream(fileIn));
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(fileOut))));
		
		 boolean b = false; 
	        while (!BinaryStdIn.isEmpty()) {
	            int run = BinaryStdIn.readInt(LENGTH_OF_ENCODING); 
	            for (int i = 0; i < run; i++) 	 
	            	BinaryStdOut.write(b);
	            b = !b;
	        }
	        BinaryStdOut.close();
	}
}
