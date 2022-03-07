package DataCompression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RLEMain {

	public File compress_out;
	public File expand_out;
	
	public RLEMain(File input, File output) throws IOException {
		
		//Compression Section
		RunLengthCompress.outputFile = output;
		/*BufferedReader in = new BufferedReader( new FileReader(RunLengthCompress.outputFile));
		String line = in.readLine();
		in.close();
		System.out.print(line);*/
		RunLengthCompress.compress(input);
		output = RunLengthCompress.outputFile;
		/*in = new BufferedReader( new FileReader(output));
		line = in.readLine();
		in.close();
		System.out.print(line);*/
		compress_out = output;
		
		//Expansion Section
		File expand = new File("RLE_expand.txt");
		if (expand.createNewFile()) {
			FileWriter myWriter = new FileWriter("RLE_expand.txt");
			myWriter.write("mess");
	        myWriter.close();
	        } /*else {
	        System.out.println("File already exists.");
	        FileWriter myWriter = new FileWriter("RLE_expand.txt");
	        myWriter.write("");
	        BufferedReader out = new BufferedReader( new FileReader(expand));
			String line_out = out.readLine();
			in.close();
			System.out.println(line_out);
	        myWriter.close();
	      } */
		  
		RunLengthExpand.outputFile = expand;
		RunLengthExpand.expand(output);
		expand_out = RunLengthExpand.outputFile;
		
	}
}