/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *
 *  Description: Supports transforming a string into one that is more suitable
 * for compression, through creating clusters where there are repeated
 * characters. given n circular suffixes that are sorted, supports transforming
 * and inverting the transformation.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // avoid repetition of constant
    private static final int SIZE = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String input = BinaryStdIn.readString();
        CircularSuffixArray circArray = new CircularSuffixArray(input);
        int index = Integer.MIN_VALUE;
        char[] characters = new char[input.length()];

        for (int i = 0; i < circArray.length(); i++) {
            if (circArray.index(i) == 0) {
                index = i;
                characters[i] = input.charAt(input.length() - 1);
            }
            else {
                characters[i] = input.charAt(circArray.index(i) - 1);
            }
        }
        BinaryStdOut.write(index);

        for (int i = 0; i < input.length(); i++) {
            BinaryStdOut.write(characters[i]);
        }
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    /*
    @citation Copied/Adapted from:https://algs4.cs.princeton.edu/
    51radix/LSD.java.html Accessed 04/28/2020. various blocks of code
    were taken from the file and adapted into the program; some of them copied
    directly
     */
    public static void inverseTransform() {
        int index = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();

        // key indexed counting
        int[] next = new int[input.length()];
        int[] count = new int[SIZE + 1];

        for (int i = 0; i < input.length(); i++) {
            count[input.charAt(i) + 1]++;
        }
        for (int i = 0; i < SIZE; i++) {
            count[i + 1] += count[i];
        }
        for (int i = 0; i < input.length(); i++) {
            next[count[input.charAt(i)]++] = i;
        }

        int current = next[index];
        for (int i = 0; i < input.length(); i++) {
            BinaryStdOut.write(input.charAt(current));
            current = next[current];
        }
        BinaryStdOut.flush();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        if (args[0].equals("+")) {
            inverseTransform();
        }
    }

}
