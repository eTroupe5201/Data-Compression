package dataCompression;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BinaryDump {
    
	public static void dump(File file) throws IOException {
	
    	System.setIn(new FileInputStream(file)); //set file to standard input stream	
		
    	int count = 0; //init count
		
    	while(!BinaryStdIn.isEmpty()) { 
			BinaryStdIn.readBoolean();   
			count++;
		}
		
		System.out.println (count + " bits");
		
		int answer = JOptionPane.showConfirmDialog(null, "Would you like the compression ratio?");
		
		//added switch to implement ratio calc
		switch(answer) {
		case JOptionPane.YES_OPTION:
			calculateCompressionRatio(count);
			break;
		case JOptionPane.NO_OPTION:
			break;
		case JOptionPane.CANCEL_OPTION:
			break;
		default:
			break;}
	
System.exit(0);
		}
		
  
public static void calculateCompressionRatio(int compressedFileSize) {

String userSize = JOptionPane.showInputDialog("What is the original size of the file?"); //ask user for size of original file

double originalSize = Double.parseDouble(userSize);//parse user output

double ratio = (compressedFileSize / originalSize) * 100; //calculate ratio 

System.out.println("Compression Ratio is "+ String.format("%,.0f",ratio) +"%"); //show user compression ratio

}
