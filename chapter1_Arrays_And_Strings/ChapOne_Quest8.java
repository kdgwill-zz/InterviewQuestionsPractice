/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter1_Arrays_And_Strings;

/**
 * Assume you have a method isSubstring which checks if one word is a substring
 * of another. Given two strings,s1 and s2, write code to check if s2 is a
 * rotation of s1 using only one call to isSubstring (e.g., "waterbottle" is a
 * rotation of "erbottlewat"
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest8 {

    /**
     * If s2 is a rotation of s1 then if s2 is appended to itself s1 will be a
     * substring of s2+s2
     *
     * @param s1
     * @param s2
     * @return True if s2 is a rotation of s1
     */
    public static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String s2_2 = s2 + s2;
        return isSubstring(s2_2, s1);
    }

    /**
     *
     * @param s1
     * @param s2
     * @return True if s2 is a substring of s1
     */
    public static boolean isSubstring(String s1, String s2) {
        return s1.indexOf(s2) >= 0 ? true : false;
    }

    public static void main(String[] args) {
        assert true == isRotation("water", "water") : "Check 1";
        assert true == isRotation("water", "aterw") : "Check 2";
        assert true == isRotation("water", "terwa") : "Check 3";
        assert true == isRotation("water", "erwat") : "Check 4";
        assert true == isRotation("water", "rwate") : "Check 5";
        assert false == isRotation("water", "waetr") : "Check 6";
        assert false == isRotation("water", "ratew") : "Check 7";
        assert true == isRotation("wawater", "waterwa") : "Check 8";
        assert true == isRotation("waterbottle", "erbottlewat") : "Check 9";
    }
}
