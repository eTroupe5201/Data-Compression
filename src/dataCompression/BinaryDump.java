package dataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JOptionPane;


public class BinaryDump {
	
	static double originalSize;
	static double ratio;
	static String userSize;
	
	public static void dump(File file) throws IOException {
	
		System.setIn(new FileInputStream(file)); //set file to standard input stream
    	int bitsPerLine = 16;
        int count;
        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
            if (bitsPerLine == 0) {
                BinaryStdIn.readBoolean();
                continue;
            }
            else if (count != 0 && count % bitsPerLine == 0) System.out.println();
            if (BinaryStdIn.readBoolean()) System.out.print("1");
            else                           System.out.print("0");
        }
        if (bitsPerLine != 0) System.out.println();
 
       

		BinaryStdIn.close();
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
	
       //System.exit(0); not needed here closing for this
		}
		
  
public static void calculateCompressionRatio(int compressedFileSize) {

userSize = JOptionPane.showInputDialog("What is the original size of the file?"); //ask user for size of original file

originalSize = Double.parseDouble(userSize);//parse user output

ratio = (compressedFileSize / originalSize) * 100; //calculate ratio 

System.out.println("Compression Ratio is "+ String.format("%,.0f",ratio) +"%"); //show user compression ratio

}
   
}