/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class Primality {

    public static boolean primeNaive(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean primeBetter(int n) {
        if (n < 2) {
            return false;
        }
        int n2 = (int) Math.sqrt(n);
        for (int i = 2; i < n2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    private boolean[] flags;

    public boolean isPrime(int n) {
        if (flags == null || n > flags.length - 1) {
            sieveOfEratosthenes(n);
        }
        return flags[n];
    }

    /**
     * Its best to pregenerate a list of primes from 2 to max by finding a prime
     * and for each prime found loop from current to max crossing off all
     * numbers that are divisible by it
     */
    private boolean[] sieveOfEratosthenes(int max) {
        flags = new boolean[max + 1];
        //set all flags to true other than 0 and 1
        for (int i = 2; i <= max; i++) {
            flags[i] = true;
        }
        flags[0] = flags[1] = false;
        int prime = 2;

        while (prime <= Math.sqrt(max)) {
            //Cross off remaining multiples of prime
            crossOff(flags, prime);
            //Find next value which is true;
            prime = getNextPrime(flags, prime);
            //break if next prime exceeds maximum
            if (prime >= flags.length) {
                break;
            }
        }
        return flags;
    }

    private void crossOff(boolean[] flags, int prime) {
        /* Cross off remaining multiples of prime. We can start with
         * (prime * prime), because if we have a k* prime, where 
         * k < prime, this value would have already been crossed off in 
         * a prior iteration
         */
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    private int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next]) {
            next++;
        }
        return next;
    }
}
