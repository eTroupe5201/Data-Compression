package dataCompression.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.print.DocFlavor.STRING;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dataCompression.BinaryStdIn;
import dataCompression.HuffmanCompress;


class HuffmanCompressTest {

	Path outputPath, inputPath, expectedPath;
	File inputFile, outputFile, expectedFile;
	
	
	@TempDir 
	Path tempDir;
	
		@Test
	    public void test() throws IOException {
		//   FileHelper helper = new FileHelper();
		 
			Path inputFilePath = tempDir.resolve("inputTestFile.txt");// create inputpath with temp directory
			Files.writeString(inputFilePath, "ABRACADABRA!");// create test file for input

			Path outputFilePath = tempDir.resolve("outputTestFile.txt");// create outputpath with temp directory
			Files.writeString(outputFilePath, "");// create blank file for output
			
			Path expectedPath = tempDir.resolve("expectedFile.txt");// create outputpath with temp directory
			Files.writeString(expectedPath, "PJ\"CCPª@  ÖÔ");// create blank file for output
			
			expectedFile = expectedPath.toFile();
			inputFile = inputFilePath.toFile();// set the inputfile to the inputfilepath
			outputFile = outputFilePath.toFile();// set the outputFile to the outputFilePath

		    HuffmanCompress.compress(inputFile, outputFile); // i need to get it from here
		   
			assertSame(outputFile, HuffmanCompress.global_output);
			assertSame(inputFile, HuffmanCompress.global_input);

			assertTrue(Files.exists(outputFilePath));
			assertTrue(Files.exists(inputFilePath));
			
			assertTrue(outputFile.exists());
			assertTrue(inputFile.exists());

			//Verify that content from RunLengthExpand's output file is the same as the expected content
	 		assertEquals(dump(expectedFile),dump(outputFile));

		}
	    public static String dump(File file) throws IOException {
	    	System.setIn(new FileInputStream(file)); //set file to standard input stream
	    	String BITS = "";
	    	int bitsPerLine = 16;
	        int count;
	        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
	            if (bitsPerLine == 0) {
	                BinaryStdIn.readBoolean();
	                continue;
	            }
	            else if (count != 0 && count % bitsPerLine == 0) System.out.println();
	            if (BinaryStdIn.readBoolean()) BITS += "1";
	            else                           BITS += "0";
	        }
	        if (bitsPerLine != 0) System.out.println();
	 
	        BinaryStdIn.close();
			return BITS;
	    }

}
