package DataCompression;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HuffMain {
	public File compress_out;
	public File expand_out;
	
	public HuffMain(File input, File output) throws IOException {
		
		//Compression Section
		HuffmanCompress.outputFile = output;
		/*BufferedReader in = new BufferedReader( new FileReader(HuffmanCompress.outputFile));
		String line = in.readLine();
		in.close();
		System.out.print(line);*/
		HuffmanCompress.compress(input);
		output = HuffmanCompress.outputFile;
		/*in = new BufferedReader( new FileReader(output));
		line = in.readLine();
		in.close();
		System.out.print(line);*/
		compress_out = output;
		
		//Expansion Section
		File expand = new File("Huff_expand.txt");
		if (expand.createNewFile()) {
			FileWriter myWriter = new FileWriter("Huff_expand.txt");
			myWriter.write("mess");
	        myWriter.close();
	        } /*else {
	        System.out.println("File already exists.");
	        FileWriter myWriter = new FileWriter("Huff_expand.txt");
	        myWriter.write("");
	        BufferedReader out = new BufferedReader( new FileReader(expand));
			String line_out = out.readLine();
			in.close();
			System.out.println(line_out);
	        myWriter.close();
	      } */
		  
		HuffmanExpand.outputFile = expand;
		HuffmanExpand.expand(output);
		expand_out = HuffmanExpand.outputFile;
	}
}