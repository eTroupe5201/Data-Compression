package DataCompressionTests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import DataCompression.HuffMain;
import DataCompression.filesEqual;


public class huffmanTest {

	public File inputFile;
	public File outputFile;
	public File expected;
	private HuffMain test;
	
	public void setup() throws IOException {
		try {
		      inputFile = new File("HuffSample.txt");
		      if (inputFile.createNewFile()) {
		        FileWriter myWriter = new FileWriter("HuffSample.txt");
		        myWriter.write("AABCBAD");
		        myWriter.close();
		      } else {
		        FileWriter myWriter = new FileWriter("HuffSample.txt");
		        myWriter.write("AABCBAD");
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
		      outputFile = new File("Huff_comp.txt");
		      if (outputFile.createNewFile()) {
		        FileWriter myWriter = new FileWriter("Huff_comp.txt");
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
			expected = new File("Huff_expected.txt");
			FileWriter myWriter;
			myWriter = new FileWriter("Huff_expected.txt");
			myWriter.write("1100010001011");
	        /*BufferedReader in = new BufferedReader( new FileReader(expected));
			String line = in.readLine();
			in.close();
			System.out.println(line);*/
	        myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test = new HuffMain(inputFile,outputFile);
        
	}
	
	@Test
    public void compression() throws Exception {
        assertTrue(filesEqual.filesEqual(expected, test.compress_out));
    }

    @Test
    public void expansion() throws Exception {
    	assertTrue(filesEqual.filesEqual(test.expand_out, inputFile));
        
        }
    



}
