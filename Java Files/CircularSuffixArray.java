/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *
 *  Description:  Given an input string, create its circular suffixes in an
 * array of comparables sorted using arrays.sort. has the ability to return
 * the index of the ith sorted suffix
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {

    // sorted circular suffix array
    private final CircularSuffix[] sorted;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        sorted = new CircularSuffix[s.length()];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(sorted);
    }

    // nested class to represent a circulr suffix implicitly,
    // with an input string and pointer to an index/character
    private class CircularSuffix implements Comparable<CircularSuffix> {

        private final String inString;
        private final int pointer;

        public CircularSuffix(String input, int pointer) {
            inString = input;
            this.pointer = pointer;
        }

        public int compareTo(CircularSuffix that) {
            for (int i = 0; i < inString.length(); i++) {
                int compare = Character
                        .compare(inString.charAt((this.pointer + i) %
                                                         inString.length()),
                                 inString.charAt((that.pointer + i) %
                                                         inString.length()));

                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        }
    }

    // length of s
    public int length() {
        return sorted.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length() - 1) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return sorted[i].pointer;
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray testOne = new CircularSuffixArray("TESTWORD");
        StdOut.println("Sorted Suffixes Indices:");
        for (int i = 0; i < testOne.length(); i++) {
            StdOut.println(testOne.index(i));
        }
        StdOut.println("Length: " + testOne.length());

    }

}
