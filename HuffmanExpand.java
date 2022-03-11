package dataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class HuffmanExpand {
	  private static InputStream currentStream;
	  public static FileOutputStream outputStream;
	  public static File global_output;
	  public static File global_input;
	
		public static void expand(File inputFile, File outputFile) throws IOException {
			
			global_output = outputFile;
			global_input = inputFile;
			
			try {
		    	currentStream = new FileInputStream(inputFile);
		    	System.setIn(currentStream);

		    	outputStream = new FileOutputStream(outputFile);
		    	System.setOut(new PrintStream(outputStream));
			} catch (NullPointerException e1) {
				
				e1.printStackTrace();
			}
	
			HuffNode root = bitToTrie();
			int length = BinaryStdIn.readInt();
			char[] output = new char[length];
			
			//decode using the trie: left = 0, right = 1;
			for(int i = 0; i < length; i++) {
				HuffNode n = root;
				while(!n.isLeaf()) {
					if(BinaryStdIn.readBoolean() == false) {
						n = n.left;
					}
					else {
						n = n.right;
					}
				}
				output[i] = n.ch;
			}
			
			for(char ch : output) {
				BinaryStdOut.write(ch);
			}
			//next line
			BinaryStdOut.write("\n");
		    BinaryStdOut.close();
	        currentStream.close();
	        outputStream.close();
	     
			
		}
		private static HuffNode bitToTrie() {
			boolean leaf = BinaryStdIn.readBoolean();
			if(leaf) {
				//create a leaf node with value of char that follows the flag bit
				return new HuffNode(BinaryStdIn.readChar(), -1, null, null);
			}
			else {
				return new HuffNode('\0', -1, bitToTrie(), bitToTrie());
			}
		}
}


