/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etc.com.outr

import scala.io.Source
import scala.util.Random

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
object TextParser{
    /**
     * Bug issue with random number generator landing on blank line
     */ 
   def main(args: Array[String]) {
        val quotes = io.Source.fromURL(
            "http://www.coverfire.com/files/quotes.txt","utf-8").getLines.toArray
        val start = quotes.lastIndexWhere( _ == "",
            new Random().nextInt(quotes.size)-1)
        val end = quotes.indexOf("",start+1)
        for(i <- start until end+1)print(quotes(i))
   }
}


