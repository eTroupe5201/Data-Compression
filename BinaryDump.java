public class BinaryDump {

    public static void dump() throws FileNotFoundException {
		System.setIn(new FileInputStream(new File("abra.txt")));
		int count = 0;
		while(!BinaryStdIn.isEmpty()) {
			BinaryStdIn.readBoolean();
			count++;
		}
		System.out.println (count + " bits");
	}
//     public static void main(String[] args) {
//         int bitsPerLine = 16;
//         if (args.length == 1) {
//             bitsPerLine = Integer.parseInt(args[0]);
//         }

//         int count;
//         for (count = 0; !BinaryStdIn.isEmpty(); count++) {
//             if (bitsPerLine == 0) {
//                 BinaryStdIn.readBoolean();
//                 continue;
//             }
//             else if (count != 0 && count % bitsPerLine == 0) StdOut.println();
//             if (BinaryStdIn.readBoolean()) StdOut.print(1);
//             else                           StdOut.print(0);
//         }
//         if (bitsPerLine != 0) StdOut.println();
//         StdOut.println(count + " bits");
//     }
}
