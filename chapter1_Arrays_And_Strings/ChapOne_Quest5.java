/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter1_Arrays_And_Strings;

/**
 * Implement a method to perform basic string compression using the counts of
 * repeated chracters. FOr example, the string aabcccccaaa would become
 * a2b1c5a3. If the "compressed" string would not become smaller than the
 * original string, your method should return the original string
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest5 {

    /**
     * Compress string by counting sequential identical characters then place
     * the character and count in seperate string
     *
     * *Use StringBuilder to avoid recreated a new string per count
     *
     * @param str
     * @return compressedString > str? str: compressedString
     */
    public static String compression(String str) {
        StringBuilder compressed = new StringBuilder();
        for (int i = 1, c = 1; i < str.length(); i++, ++c) {
            char c1 = str.charAt(i - 1);
            char c2 = str.charAt(i);
            boolean endOfTheLine = (i == str.length() - 1);
            if (c1 != c2 || endOfTheLine) {
                //cannot count beyond array in java without error
                if (endOfTheLine) {
                    ++c;
                }
                compressed.append(((char) c1));//char
                compressed.append(((int) c));//num
                c = 0;//prefix increment handles this
            }
        }

        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    public static void main(String[] args) {
        String s = "aabcccccaaa";
        System.out.println("Original  : " + s);
        System.out.println("Compressed: " + compression(s));
    }
}
