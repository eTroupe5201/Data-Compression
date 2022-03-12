package dataCompression.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dataCompression.RunLengthExpand;

class RunLengthExpandTest4 {
	
	Path outputPath2, inputPath2;
	File InputFile2, OutputFile2;
	
	@TempDir 
	Path tempDir;
	
	@Test
    public void test2() throws IOException {
		
		//Create a temp input path with temp directory and write to that path the content of the new file
		Path inputFilePath2 = tempDir.resolve("inputTestFile2.txt");
		Files.writeString(inputFilePath2,"");
		
		//Create temp output path with temp directory and create a blank file for output
		Path outputFilePath2 = tempDir.resolve("outputTestFile2.txt");
		Files.writeString(outputFilePath2, "");

		//Set Temp Files To Temp Path
		InputFile2 = inputFilePath2.toFile();
		OutputFile2 = outputFilePath2.toFile();

		List<String> content2 = new ArrayList<>();//create a new list to test the content of the output
		content2.add("it was the best of times it was the worst of times");
		
		//send temp files to RunLengthExpand
		RunLengthExpand.expand(InputFile2, OutputFile2);
		
		//check to make sure temporary files that were created match the output/input files of RunLengthExpand
		assertSame(OutputFile2, RunLengthExpand.output);
		assertSame(InputFile2, RunLengthExpand.input);
		
		
		//Verify that content from RunLengthExpand's output file is the same as the expected content
 		assertTrue(content2.equals(Files.readAllLines(RunLengthExpand.output.toPath())));
		
	}
}
