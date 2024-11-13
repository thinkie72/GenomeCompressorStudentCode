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

    public static final int A = 0b00;
    public static final int C = 0b01;
    public static final int G = 0b10;
    public static final int T = 0b11;
    public static final int BITS_PER_CHAR = 2;


    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {

        // TODO: complete the compress() method

        String s = BinaryStdIn.readString();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(encode(s.charAt(i)), 2);
        }

        BinaryStdOut.close();
    }

    private static int encode(char c) {
        return switch (c) {
            case 'A' -> A;
            case 'C' -> C;
            case 'G' -> G;
            default -> T;
        };
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {

        // TODO: complete the expand() method
        int encoded = 0;
        while (!BinaryStdIn.isEmpty())
            encoded = BinaryStdIn.readInt(2);
            BinaryStdOut.write(decode(encoded));
        }

        BinaryStdOut.close();
    }

    private static char decode(int i) {
        return switch (i) {
            case A -> 'A';
            case C -> 'C';
            case G -> 'G';
            default -> 'T';
        };
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