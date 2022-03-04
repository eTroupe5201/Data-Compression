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

public class RunLengthCompress 
{
	private static final int RUN_SIZE    = 256;			//max size of run encoded
	private static final int LENGHTH_OF_ENCODING = 8;	//number of digits to encode the size 
	
	public static void compress(String fileIn, String fileOut) throws IOException 
	{
		System.setIn(new FileInputStream(fileIn));
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(fileOut))));
		

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
	    }
	
}
