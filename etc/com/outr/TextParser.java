/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etc.com.outr;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Then I'll give you my standard interview question for you to complete in
 * Scala. :)
 *
 * I want a random quote generator. Each time you run the program it spits out a
 * random quote from this file: http://www.coverfire.com/files/quotes.txt
 *
 * My implementation was 20 lines of code and took less than five minutes to
 * write. I could have probably gotten it down to 5 lines, but it would have
 * taken me longer to write it
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class TextParser {

    public static void main(String[] args) {
        /**
         * URLConnection connect = quotes.openConnection(); InputStreamReader
         * isr = new InputStreamReader(connect.getInputStream()); try
         * (BufferedReader in = new BufferedReader(isr)) { String inputLine;
         * while ((inputLine = in.readLine()) != null) {
         * System.out.println(inputLine); } } catch (Exception e) {
         */
        Scanner s = null;
        try {
            URL quotes = new URL("http://www.coverfire.com/files/quotes.txt");
            s = new Scanner(quotes.openStream());

            int lineNum = 0;
            while (s.hasNext() != false) {
                s.nextLine();
                lineNum++;
            }
            s.reset();
            
            while (s.hasNext() != false) {
                s.nextLine();
                lineNum++;
            }


            System.out.println(lineNum);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
