/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etc.CodingTest_3;

/**
 * <pre>Find an 8 letter string of characters that contains only letters from
 *
 * acdegilmnoprstuw
 *
 * such that the hash(the_string) is
 *
 * 25180466553932  [Trello]
 * 945924806726376 [FogCreek]
 *
 * if hash is defined by the following pseudo-code:
 *
 * Int64 hash (String s) {
 *      Int64 h = 7
 *      String letters = "acdegilmnoprstuw"
 *      for(Int32 i = 0; i &lt; s.length; i++) {
 *          h = (h * 37 + letters.indexOf(s[i]))
 *      }
 *      return h
 * }
 *
 * For example, if we were trying to find the 7 letter string where
 * hash(the_string) was 680131659347, the answer would be "leepadg".)
 *
 * jobs@trello.com with your solution as the first word
 * in the subject line. Include any code you used to solve the problem as an
 * attachment, and also send us a current resume in HTML, Plain Text, or PDF
 * format. In the body of the email please explain why you would be a good fit
 * for this job. Include links to any apps you have worked on. If you have a
 * website or GitHub account send those over as well.
 *
 * To apply, please email jobs@fogcreek.com with your solution as the first word
 * in the subject line. Include any code you used to solve the problem as an
 * attachment, and also send us a current resume in HTML, Plain Text, or PDF
 * format. In the body of the email please explain why you would be a good fit for
 * this job. If you have a website, send us the URL!
 * </pre>
 *
 * @author Kyle D. Williams
 * <kyledgwilliams@gmail.com>
 */
public class TestClass {

    private static final String letters = "acdegilmnoprstuw";

    public static void main(String[] args) {
        String s1 = "leepadg";
        System.err.println("Orig: " + s1);
        long i = hash(s1);
        System.err.println("Hash: " + i);
        String s2 = unhash(i);
        System.err.println("UnHash: " + s2);
        assert s1.equals(s2) : s2;
        System.err.println("IMPLEMENTATION CORRECT\t" + s1.equals(s2));
        String s3 = unhash(25180466553932L);
        System.out.println(s3);
        String s4 = unhash(945924806726376L);
        System.out.println(s4);

    }

    public static String unhash(long hash) {
        if (hash < 7) {
            throw new IndexOutOfBoundsException("Incorrect Hashing");
        } else if (hash == 7) {
            return "";
        }
        long oldHash = hash / 37;
        int i = (int) (hash - (oldHash * 37));
        String s = unhash(oldHash) + letters.charAt(i);
        return s;
    }

    public static long hash(String s) {
        long h = 7;
        for (int i = 0; i < s.length(); i++) {
            h = (h * 37 + letters.indexOf(s.charAt(i)));
        }
        return h;
    }
}
