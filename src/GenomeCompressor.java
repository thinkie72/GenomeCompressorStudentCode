import java.util.Arrays;
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

    private static final char[] alphabet = {'A', 'C', 'G', 'T'};    // Our alphabet
    private static final int N = 4;                                 // The size of the alphabet
    private static int[] inverse;               // Array to allow O(1) lookups of index from char

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses them using two bits per
     * character; and writes the results to standard output.
     */
    public static void compress() {

        // Read in the string, write out the length first.
        String s = BinaryStdIn.readString();
        int n = s.length();
        BinaryStdOut.write(n);

        // Write out the two-bit code for each char.
        for (int i = 0; i < n; i++) {
            int d = inverse[s.charAt(i)];
            BinaryStdOut.write(d, 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; converts each two bits
     * to an 8-bit extended ASCII character over the alphabet { A, C, T, G };
     * and writes the results to standard output.
     */
    public static void expand() {

        // Get the length of the compressed file, stored first.
        int n = BinaryStdIn.readInt();

        // Read two bits, write a char.
        for (int i = 0; i < n; i++) {
            char c = BinaryStdIn.readChar(2);
            BinaryStdOut.write(alphabet[c], 8);
        }
        BinaryStdOut.close();
    }


    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        initialize();
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

    /**
     * Initializes the inverse array to allow for O(1) translations from
     * indices to nucleotides.
     */
    private static void initialize() {
        inverse = new int[Character.MAX_VALUE];
        Arrays.fill(inverse, -1);

        // inverse[char] = index
        for (int c = 0; c < N; c++)
            inverse[alphabet[c]] = c;
    }
}