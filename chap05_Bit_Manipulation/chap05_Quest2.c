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
#include <stdio.h>

/**
 * move binary point one over to the left by multiplying by two
 *
 * @param num
 * @return
 */
void fracToBits(double num) {
    if (num > 1 || num < 0) {
        printf("ERROR\n");
        return;
    }

    char binaryfrac[35];
    int i = 0;
    binaryfrac[i++] = '0';
    binaryfrac[i++] = '.';
    for (; i < 32 && num != 0.0f; i++) {
        num *= 2;
        int n = (int) (num);
        num -= n;
        binaryfrac[i] = '0' + n;
    }
    binaryfrac[i++] = '\0';
    if (num > 0.0f) {
        printf("ERROR\n");
        return;
    }

    printf("%s\n", binaryfrac);
}

int main() {
    fracToBits(0.625f);
    fracToBits(0.72f);
    fracToBits(0.75f);
    fracToBits(0.50f);
    fracToBits(0.25f);
    fracToBits(0.02f);
    return 0;
}
