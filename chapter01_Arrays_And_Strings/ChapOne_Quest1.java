/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter01_Arrays_And_Strings;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest1 {

    /**
     * Checks if a string is unique by sorting the underlying array and checking
     * for identical adjacent characters
     *
     * @param str
     * @return
     */
    public static boolean strUnique(String str) {
        char[] c_str = str.toCharArray();
        Arrays.sort(c_str);
        for (int i = 1; i < c_str.length; i++) {
            if (c_str[i - 1] == c_str[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Boolean> check = new ArrayList<>();
        list.add("");
        check.add(true);

        list.add(" ");
        check.add(true);

        list.add("..");
        check.add(false);

        list.add("ABCDEFGHIJKLMNOP");
        check.add(true);

        list.add("ABCDEABCOKIJFNOP");
        check.add(false);

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            boolean b = check.get(i);
            boolean b2 = strUnique(s);
            System.out.println("String " + s + " is unique:" + b + ":" + b2);
            assert b == strUnique(s);
        }
    }
}
