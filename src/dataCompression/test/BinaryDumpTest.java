package dataCompression.test;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dataCompression.BinaryStdIn;
import dataCompression.RunLengthCompress;

class BinaryDumpTest {


		Path originalPath, compressedPath;
		File originalFile, compressedFile;

		@TempDir 
		Path tempDir;

		
			@Test
		    public void test() throws Exception  {
				
				//Create a temp input path with temp directory and write to that path the content of the new file
				Path originalpath = tempDir.resolve("tinyTextTest.txt");
				Files.writeString(originalpath, "it was the best of times it was the worst of times");
				
				Path compressedPath = tempDir.resolve("compressed.txt");
				Files.writeString(compressedPath, "");
				
				//Set Temp Files To Temp Path
				originalFile = originalpath.toFile();
				compressedFile = compressedPath.toFile();
				
				//send to runLength
				RunLengthCompress.compress(originalFile, compressedFile);
				
				//Receive size from test dump
				double originalSize = Testdump(originalFile);
				double compressedSize = Testdump(RunLengthCompress.output);
			
				
				//Obtain result and assert that it is the same as the expected ratio
				double ratio = (compressedSize / originalSize) * 100; //calculate ratio 	
				
				//format to match real BinaryDump.dump
				double formatRatio = Double.parseDouble(String.format("%,.0f", ratio));
	
				assertEquals(formatRatio, 400.0, 0.0d);//depreciated for doubles had to add .0d
			
				//https://www.baeldung.com/java-comparing-doubles
			
			}
			
			public static int Testdump(File file) throws IOException {
				
		    	System.setIn(new FileInputStream(file)); //set file to standard input stream	

		    	int count = 0; //init count
				
		    	while(!BinaryStdIn.isEmpty()) { 
					BinaryStdIn.readBoolean();   
					count++;
		    	}

		    	BinaryStdIn.close();//wasn't closed needed to add this to close stream, possibly because of the removal of main from binaryStdIn
				return count;
				}
				
		  
}

