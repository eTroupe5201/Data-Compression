package dataCompression.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import dataCompression.RunLengthCompress;
import dataCompression.RunLengthExpand;

class RunLengthCompressTest2 {
	Path outputPath, inputPath;
	File inputFile, outputFile;
	
	
	@TempDir 
	Path tempDir;
	
		@Test
	    public void test() throws IOException {
		//   FileHelper helper = new FileHelper();
		 
			Path inputFilePath = tempDir.resolve("inputTest1.txt");// create inputpath with temp directory
			Files.writeString(inputFilePath, "it was the best of times it was the worst of times");// create test file for input

			Path outputFilePath = tempDir.resolve("outputTest1.txt");// create outputpath with temp directory
			Files.writeString(outputFilePath, "");// create blank file for output
			
			assertTrue(Files.exists(outputFilePath));
			assertTrue(Files.exists(inputFilePath));
			
			inputFile = inputFilePath.toFile();// set the inputfile to the inputfilepath
			outputFile = outputFilePath.toFile();// set the outputFile to the outputFilePath
			
			assertTrue(outputFile.exists());
			assertTrue(inputFile.exists());
			
			RunLengthCompress.compress(inputFile, outputFile); // i need to get it from here

			assertSame(outputFile, RunLengthCompress.output);
			assertSame(inputFile, RunLengthCompress.input);
			
			List<String> content2 = new ArrayList<>();//create a new list to test the content of the output
			content2.add("");//fix

			//Verify that content from RunLengthExpand's output file is the same as the expected content
	 		assertTrue(content2.equals(Files.readAllLines(RunLengthCompress.output.toPath())));

		
		}
}