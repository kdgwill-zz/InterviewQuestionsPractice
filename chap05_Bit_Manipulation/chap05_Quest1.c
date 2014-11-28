/**
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit
 * i. You can assume that the bits j through i have enought space to fit all of
 * M. That is, if M=1001, you can assume that there are at least 5 bits between
 * j and i. You would not, for example have j=3 and i=2, becaise M could not
 * fully fit between 3 and 2.
 *
 * EXAMPLE:
 *
 * INPUT: N = 10000000000, M = 10011, i=2, j=6
 *
 * OUTPUT:N = 10001001100
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
#include <stdio.h>
#include <stdlib.h>
#include "../dataStructure/Utility.h"

int clearBitsIThroughJ(int num, int i, int j) {
    if (j < i) {
        perror("j should be larger than i");
        exit(EXIT_FAILURE);//throw new IllegalArgumentException("j should be larger than i");
    }
    int mask = (1 << (j - i + 1)) - 1; //push then twos compliment invert
    num &= ~(mask << i); //shift into palce, invert and "And" 
    return num;
}

int orBitsAtI(int num, int M, int I) {
    return num | (M << I);
}

int function(int N, int M, int I, int J) {
    int X = clearBitsIThroughJ(N, I, J);
    int Y = orBitsAtI(X, M, I);
    return Y;
}

int main() { 
    int N = 0x400;
    int M = 0x13;
    int I = 2;
    int J = 6;
    //int X = clearBitsIThroughJ(N, I, J);
    //int Y = orBitsAtI(X, M, I);
    int Y = function(N, M, I, J);
    if(Y!=0x44C){
        perror("incorrect\n");
        exit(EXIT_FAILURE);
    }
    printf("Digit: %d == %d\n",Y,0x44C);
    printf("Binary:\n%d\t",Y);
    intToBinary(Y);
    printf("%d\t",0X44C);
    intToBinary(0x44C);
    return 0;
}
