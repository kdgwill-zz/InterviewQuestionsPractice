#include <stdio.h>
#include <string.h>
/**
 * Implement a function void reverse(char * str) in C or C++ which reverses a 
 * null terminated string
 * 
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */

/**
 * Reverse the string using XOR SWAP 
 * 
 * @param str char *
 */
void reverse(char * str);

int main() {
    char odd[] = "Racecar";
    printf("%s\n", odd);
    reverse(odd);
    printf("%s\n", odd);
    char even[] = "A man, a plan, a canal: Panama";
    printf("%s\n", even);
    reverse(even);
    printf("%s\n", even);

    return 0;
}

void reverse(char * str) {
    size_t length = strlen(str) - 1;
    for (int i = 0; i <= (int) length / 2; i++) {
        char * c1 = &str[i];
        char * c2 = &str[length - i];
        if (c1 != c2) {
            *c1 ^= *c2;
            *c2 ^= *c1;
            *c1 ^= *c2;
        }
    }
}
