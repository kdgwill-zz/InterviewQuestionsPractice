/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap05_Bit_Manipulation;

/**
 * Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double
 * print the binary represention. If the number cannot be represented accurately
 * in binary with at most 32 characters, print "ERROR"
 *
 * EXAMPLE:
 *
 * 18/25 = 0.72 = 0.1011100001010001111010...
 *
 * 01/02 = 0.50 = 0.1
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class chap05_Quest2 {

    /**
     * move binary point one over to the left by multiplying by two
     *
     * @param num
     * @return
     */
    public static String fracToBits(double num) {
        if (num > 1 || num < 0) {
            return "ERROR";
        }
        StringBuilder str = new StringBuilder("0.");
        for (int i = 0; i < 32 && num != 0.0f; i++) {
            num *= 2;
            int n = (int) (num);
            num -= n;
            str.append(n);
        }
        if (num > 0.0f) {
            return "ERROR";
        }
        return str.toString();
    } 

    public static void main(String[] args) {
        System.out.println(fracToBits(0.625f));
        System.out.println(fracToBits(0.72f));
        System.out.println(fracToBits(0.75f));
        System.out.println(fracToBits(0.50f));
        System.out.println(fracToBits(0.25f));
        System.out.println(fracToBits(0.02f));
    }
}
