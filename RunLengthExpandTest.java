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

class RunLengthExpandTest {

	Path outputPath, inputPath;
	File InputFile, OutputFile;
	
	
	@TempDir 
	Path tempDir;
	
		@Test
	    public void test() throws IOException {
			
			//Create a temp input path with temp directory and write to that path the content of the new file
			Path inputFilePath = tempDir.resolve("inputTestFile.txt");
			Files.writeString(inputFilePath, "");
			
			//Create temp output path with temp directory and create a blank file for output
			Path outputFilePath = tempDir.resolve("outputTestFile.txt");
			Files.writeString(outputFilePath, "");

			//Set Temp Files To Temp Path
			InputFile = inputFilePath.toFile();
			OutputFile = outputFilePath.toFile();

			List<String> content = new ArrayList<>();//create a new list to test the content of the output
			content.add("ABRACADABRA!");
			
			//send temp files to RunLengthExpand
			RunLengthExpand.expand(InputFile, OutputFile);
			
			//check to make sure temporary files that were created and they match the output/input files of RunLengthExpand
			assertSame(OutputFile, RunLengthExpand.output);
			assertSame(InputFile, RunLengthExpand.input);
			
			//Verify that content the from RunLengthExpand's output file is the same as the expected content
			assertTrue(content.equals(Files.readAllLines(RunLengthExpand.output.toPath())));
			
		}
		

}
