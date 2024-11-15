/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/


/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    public static final int BITS_PER_CHAR = 2;


    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        // Array to hold the binary 2-bit values of each of the characters at their index
        // The Array is of size 85 because the ASCII Value of T is 84
        int[] charsToVals = new int[85];
        charsToVals['A'] = 0;
        charsToVals['C'] = 1;
        charsToVals['G'] = 2;
        charsToVals['T'] = 3;

        String s = BinaryStdIn.readString();
        int n = s.length();

        // Writes int at start of file to read in only the real characters and not the padded zeroes
        BinaryStdOut.write(n, 32);

        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(charsToVals[s.charAt(i)], BITS_PER_CHAR);
        }

        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        // Create an array for the values in binary mapped to the actual chars
        char[] valsToChars = new char[4];
        valsToChars[0] = 'A';
        valsToChars[1] = 'C';
        valsToChars[2] = 'G';
        valsToChars[3] = 'T';

        // Reads the int at start of file for for-loop
        int numChars = BinaryStdIn.readInt(32);

        for (int i = 0; i < numChars; i++) {
            int code = BinaryStdIn.readInt(2);
            BinaryStdOut.write(valsToChars[code]);
        }

        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}