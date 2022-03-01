package DataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class Huffman {

	//Node for trie
	private static class Node implements Comparable<Node> {
		private char ch;
		private int freq;
		private Node left, right;
		
		Node (char ch, int freq, Node left, Node right){
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		private boolean isLeaf() {
			return (left == null && right == null);
		}
		
		
//		comparable
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}
	
	public static void compress() throws FileNotFoundException {
//		System.setIn(new FileInputStream(new File("test.txt")));
//		System.setOut(new PrintStream(new File("encode.txt")));
		String str = BinaryStdIn.readString();
		char[] input = str.toCharArray();
		
		//count frequency of each character in the input array
		Map<Character, Integer> freq = new HashMap<>();
		for(char c : input) {
			if(freq.get(c) == null) {
				freq.put(c, 1);
			}
			freq.put(c, freq.get(c) + 1);
			
		}
		//build the trie
		Node root = buildTrie(freq);
		
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
	
	public static void expand() throws FileNotFoundException {
//		System.setIn(new FileInputStream(new File("encode.txt")));
//		System.setOut(new PrintStream(new File("expand.txt")));
		Node root = bitToTrie();
		int length = BinaryStdIn.readInt();
		char[] output = new char[length];
		
		//decode using the trie: left = 0, right = 1;
		for(int i = 0; i < length; i++) {
			Node n = root;
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
		
	private static Node buildTrie(Map<Character, Integer> freq) {
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		//initialize a minPQ forest where each char is individual node
		for(char key : freq.keySet()) {
			Node n = new Node(key, freq.get(key), null, null);
			q.add(n);
		}
		
		//build the tree
		Node root = null;
		while(q.size() > 1) {
			//removing the 2 smallest from minPQ
			Node least = q.poll();
			Node secondLeast = q.poll();
			
			//create a new parent node 
			Node parent = new Node('\0', least.freq + secondLeast.freq, least, secondLeast);
			
			//add the parent node back to the PQ
			root = parent;
			q.add(parent);
		}		
		return root;
				
	}
	
	private static Map<Character, String> buildCodeTable(Node root) {
		Map<Character, String> codeTable = new TreeMap<>();
		buildTable(codeTable, root, "");
		return codeTable;
	}
	
	private static void buildTable(Map<Character, String> table, Node n, String s) {
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
	
	private static void trieToBit(Node n) {
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
	
	private static Node bitToTrie() {
		boolean leaf = BinaryStdIn.readBoolean();
		if(leaf) {
			//create a leaf node with value of char that follows the flag bit
			return new Node(BinaryStdIn.readChar(), -1, null, null);
		}
		else {
			return new Node('\0', -1, bitToTrie(), bitToTrie());
		}
		
	}
	
//	private static void printCode(Node x, String s) {
//		//test check
//		if(x == null) {
//			return;
//		}
//		if(x.isLeaf()) {
//			System.out.println(x.ch + ":" + s);
//		}
//		printCode(x.left, s + "0");
//		printCode(x.right, s + "1");
//		
//	}
	public static void main (String[] args) throws FileNotFoundException {
		if(args[0].equals("-")) {
			compress();
		}
		if(args[0].equals("+")) {
			expand();
		}
        else {
			throw new IllegalArgumentException("Invalid argument");
		}
	}
	
}
