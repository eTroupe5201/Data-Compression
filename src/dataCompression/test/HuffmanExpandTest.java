package dataCompression.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dataCompression.HuffmanCompress;

class HuffmanExpandTest {


	Path outputPath, inputPath, expectedPath;
	File inputFile, outputFile, expectedFile;
	
	
	@TempDir 
	Path tempDir;
	
		@Test
	    public void test() throws IOException {
			
			Path inputFilePath = tempDir.resolve("inputTestFile.txt");// create inputpath with temp directory
			Files.writeString(inputFilePath, "ABRACADABRA!");// create test file for input

			Path outputFilePath = tempDir.resolve("outputTestFile.txt");// create outputpath with temp directory
			Files.writeString(outputFilePath, "");// create blank file for output
			
			Path expectedPath = tempDir.resolve("expectedFile.txt");// create outputpath with temp directory
			Files.writeString(expectedPath,"PJCCPª@");// create blank file for output

			expectedFile = expectedPath.toFile();// set the inputfile to the inputfilepath
			inputFile = inputFilePath.toFile();// set the inputfile to the inputfilepath
			outputFile = outputFilePath.toFile();// set the outputFile to the outputFilePath

			HuffmanCompress.compress(inputFile, outputFile); // i need to get it from here

			assertSame(outputFile, HuffmanCompress.global_output);
			assertSame(inputFile, HuffmanCompress.global_input);

			assertTrue(Files.exists(outputFilePath));
			assertTrue(Files.exists(inputFilePath));
			
			assertTrue(outputFile.exists());
			assertTrue(inputFile.exists());
			
		
		}
}
