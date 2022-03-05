package dataCompression;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class HuffmanExpand {
	
	  private static InputStream currentStream;
	  public static FileOutputStream outputStream;
	  public static BufferedOutputStream bufferedOutputStream;
	  public static File outputFile;
	
	  public static void CallFileSaver() throws IOException {
	   
	    	String requester = "HUFFMAN EXPAND";
	    	new FileSaver(requester);
	    }
		    
	    public static void RecieveOutputFile(File file) {//from file saver
	    
	    	outputFile = file; 
	    }
	
		public static void expand(File file) throws IOException {
			
			if(outputFile == null) {
		    	 CallFileSaver();
		    	}
		    	
		    	currentStream = new FileInputStream(file);
		    	System.setIn(currentStream);
		    	
		    	System.out.println("OUTPUT FILE:" + outputFile);
		    
		    	outputStream = new FileOutputStream(outputFile);
		    	
		    	bufferedOutputStream = new BufferedOutputStream(outputStream);
		    	
		    	System.setOut(new PrintStream(outputStream));
		    	
	
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


