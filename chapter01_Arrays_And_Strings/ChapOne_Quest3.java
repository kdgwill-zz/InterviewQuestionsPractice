/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter01_Arrays_And_Strings;

import java.util.Arrays;

/**
 * Given Two Strings, write a method to decide if one is a permutation of the
 * other
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest3 {

    /**
     * Determines if a string is a permutation of another by first sorting them
     * then checking if they are equal
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isPermutation(final String s1, final String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char c1[] = s1.toCharArray();
        char c2[] = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    public static void main(String[] args) {
        String s1 = "RACECAR";
        String s2 = "RRAACCE";
        assert true == isPermutation(s1, s2) : "POSITION 1";
        s2 = "RACECAT";
        assert false == isPermutation(s1, s2) : "POSITION 2";
    }
}
