/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap01_Arrays_And_Strings;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume
 * that the string has sufficient space at the end of the string to hold the
 * aditional characters, and that you are given the "true" length of the
 * strings. (Note: if imple- menting in Java, please use a character array so
 * that you can perform this opera- tion in place.)
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest4 {

    /**
     * We know for every single space character %20 will have to be added in its
     * place knowing that we can augment the string in 2 passes
     *
     * We make an "assumption" that the string is null terminated
     *
     * @param str
     */
    public static void stringEncoding(char[] str) {
        int length = 0;
        int spaced_length = 0;
        //gather info
        for (; length < str.length; length++) {
            char c = str[length];
            if (c == '\0') {
                break;
            }
            if (c == ' ') {
                spaced_length++;
            }
        }
        //proper calculate spaced_length
        spaced_length *= 2;//remember that legth contains space so only 2 additional characters neede per space
        spaced_length += length;
        //shift into place        
        for (; length >= 0; length--, spaced_length--) {
            char c = str[length];
            str[spaced_length] = c;
            if (c == ' ') {
                str[spaced_length--] = '0';
                str[spaced_length--] = '2';
                str[spaced_length] = '%';
            }
        }

    }

    public static void main(String[] args) {
        String s = "Hello Sir Can I Have More\0                                      ";
        char c[] = s.toCharArray();
        stringEncoding(c);
        System.out.println(new String(c));
    }
}
