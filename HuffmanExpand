package DataCompression.Huffman;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import DataCompression.BinaryStdIn;
import DataCompression.BinaryStdOut;

public class HuffmanExpand {
		public static void expand(String fileIn, String fileOut) throws FileNotFoundException {
			System.setIn(new FileInputStream(fileIn));
			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(fileOut))));
					
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
