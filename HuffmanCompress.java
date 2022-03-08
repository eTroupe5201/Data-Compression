package dataCompression;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;



public class HuffmanCompress {
	public static FileOutputStream outputStream;
	private static InputStream currentStream;
	public static File outputFile;
	public static BufferedOutputStream bufferedOutputStream;
    public static void CallFileSaver() throws IOException {
	
	String requester = "HUFFMAN COMPRESS";
	new FileSaver(requester);
}
    
public static void ReceiveOutputFile(File file) {//from file saver?
	System.out.println("File recieved: " + file);
	outputFile = file; //from file saver 
}

	
		public static void compress(File file) throws IOException {
		
			if (outputFile == null) {
				CallFileSaver();
			}

			currentStream = new FileInputStream(file);
	    	System.setIn(currentStream);
	    
	    	outputStream = new FileOutputStream(outputFile);
	    	
	    	try {
				bufferedOutputStream = new BufferedOutputStream(outputStream);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.setOut(new PrintStream(outputStream));
	    	
	  

			String str = BinaryStdIn.readString();
			char[] input = str.toCharArray();

			//count frequency of each character in the input array
			Map<Character, Integer> freq = new HashMap<>();
			for(char c : input) {
				if(freq.get(c) == null) {
					freq.put(c, 0);
				}
				freq.put(c, freq.get(c) + 1);
				
			}
			//build the trie
			HuffNode root = buildTrie(freq);
			
			//code table
			Map<Character, String> table = buildCodeTable(root);
			
			//convert trie to bitstream 
			trieToBit(root);
			
			//length of input
			BinaryStdOut.write(input.length);
			
			//write the encoded input to bitstream using code table
			for(int i = 0; i < input.length; i++) {
				String code = table.get(input[i]);
				for(int j = 0; j < code.length(); j++) {
					if(code.charAt(j) == '0') {
						BinaryStdOut.write(false);
					}
					else if(code.charAt(j) == '1') {
						BinaryStdOut.write(true);
					}
				}
			}
			BinaryStdOut.close();
			
		}
		private static HuffNode buildTrie(Map<Character, Integer> freq) {
			
			PriorityQueue<HuffNode> q = new PriorityQueue<>();
			//initialize a minPQ forest where each char is individual node
			for(char key : freq.keySet()) {
				HuffNode n = new HuffNode(key, freq.get(key), null, null);
				q.add(n);
			}
			
			//build the tree
			HuffNode root = null;
			while(q.size() > 1) {
				//removing the 2 smallest from minPQ
				HuffNode least = q.poll();
				HuffNode secondLeast = q.poll();
				
				//create a new parent node 
				HuffNode parent = new HuffNode('\0', least.freq + secondLeast.freq, least, secondLeast);
				
				//add the parent node back to the PQ
				root = parent;
				q.add(parent);
			}		
			return root;
					
		}
		
		private static Map<Character, String> buildCodeTable(HuffNode root) {
			Map<Character, String> codeTable = new TreeMap<>();
			buildTable(codeTable, root, "");
			return codeTable;
		}
		
		private static void buildTable(Map<Character, String> table, HuffNode n, String s) {
			if(n.isLeaf()) {
				table.put(n.ch, s);
				return;
			}
			else {
				//find the prefix free code using the trie
				buildTable(table, n.left, s + "0");
				buildTable(table, n.right, s + "1");
			}	
		}
		
		private static void trieToBit(HuffNode n) throws IOException {
			if(n.isLeaf()) {
				//if node is character, write 1 then followed by value of char
				BinaryStdOut.write(true);
				BinaryStdOut.write(n.ch);
				return;
			}
			//if node is not char, write 0
			BinaryStdOut.write(false);
			trieToBit(n.left);
			trieToBit(n.right);
		}
		
		

}
