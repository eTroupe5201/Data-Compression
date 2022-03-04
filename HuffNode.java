package DataCompression.Huffman;

public class HuffNode implements Comparable<HuffNode>{
		public char ch;
		public int freq;
		public HuffNode left, right;
		
		public HuffNode (char ch, int freq, HuffNode left, HuffNode right){
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf() {
			return (left == null && right == null);
		}
		
//		comparable
		public int compareTo(HuffNode that) {
			return this.freq - that.freq;
		}

}
