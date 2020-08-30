/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *
 *  Description:  supports move to front encoding and decoding. encoding reads
 * characters from input and outputs the index where character C occurs,
 * then moves C to the front of the characters. decoding turns the encoded
 * text back into the original.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    // avoid repetition of constant
    private static final int SIZE = 256;

    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {

        // read each 8-bit character c from standard input, one at a time;
        // output the 8-bit index in the sequence where c appears
        char[] sequence = new char[SIZE];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {

            char current = BinaryStdIn.readChar();
            int index = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                if (sequence[i] == current) {
                    index = i;
                    break;
                }
            }
            // output the 8-bit index in the sequence where c appears
            BinaryStdOut.write((char) index);

            // move c to the front
            for (int i = index; i > 0; i--) {
                sequence[i] = sequence[i - 1];
            }
            sequence[0] = current;
        }
        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from stdin and writing to stdout
    public static void decode() {

        char[] sequence = new char[SIZE];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = (char) i;
        }

        // read each 8-bit character i (but treat it as an
        // integer between 0 and 255) from standard input one at a time
        while (!BinaryStdIn.isEmpty()) {
            char charIndex = BinaryStdIn.readChar();
            char letter = sequence[charIndex];
            // write the ith character in the sequence
            BinaryStdOut.write(sequence[charIndex]);

            // move that character to the front
            for (int i = charIndex; i > 0; i--) {
                sequence[i] = sequence[i - 1];
            }
            sequence[0] = letter;
        }
        BinaryStdOut.flush();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        if (args[0].equals("+")) {
            decode();
        }
    }

}
