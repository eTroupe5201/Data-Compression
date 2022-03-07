package DataCompressionTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.Rule;

import DataCompression.RLEMain;
import DataCompression.BinaryDump;
import DataCompression.filesEqual;


public class RLEtest {
	public File inputFile;
	public File outputFile;
	public File expected;
	private RLEMain test;
	
	@Before
	public void setup() throws IOException {
		try {
		      inputFile = new File("Sample.txt");
		      if (inputFile.createNewFile()) {
		        FileWriter myWriter = new FileWriter("Sample.txt");
		        myWriter.write("agcttttcaacgggcc");
		        myWriter.close();
		      } else {
		        FileWriter myWriter = new FileWriter("Sample.txt");
		        myWriter.write("agcttttcaacgggcc");
		        myWriter.close();
		        /*BufferedReader in = new BufferedReader( new FileReader(inputFile));
				String line = in.readLine();
				in.close();
				System.out.println(line);*/
		      }
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
		      outputFile = new File("Sample_RLE.txt");
		      if (outputFile.createNewFile()) {
		        FileWriter myWriter = new FileWriter("Sample_RLE.txt");
		        myWriter.write("mess");
		        myWriter.close();
		      } /*else {
		        System.out.println("File already exists.");
		        FileWriter myWriter = new FileWriter("Sample_RLE.txt");
		        myWriter.write("mess");
		        BufferedReader in = new BufferedReader( new FileReader(outputFile));
				String line = in.readLine();
				in.close();
				System.out.println(line);
		        myWriter.close();
		      }*/
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		try {
			expected = new File("RLE_expected.txt");
			FileWriter myWriter;
			myWriter = new FileWriter("RLE_expected.txt");
			myWriter.write("a1g1c1t4c1a2c1g3c2");
	        /*BufferedReader in = new BufferedReader( new FileReader(expected));
			String line = in.readLine();
			in.close();
			System.out.println(line);*/
	        myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test = new RLEMain(inputFile,outputFile);
        
	}
	
	
//TEST RUN LENGTH COMPRESS
	@Test
	public void RLECompress() throws IOException {
		

		/*BufferedReader in = new BufferedReader( new FileReader(outputFile));
		String line = in.readLine();
		in.close();
		System.out.println(line);*/

		assertTrue(filesEqual.filesEqual(expected, test.compress_out));

	}

	//TEST RUN LENGTH EXPAND
	@Test
	public void RLEExpand() throws IOException {
		assertTrue(filesEqual.filesEqual(test.expand_out, inputFile));
	}
	
	
	
}
