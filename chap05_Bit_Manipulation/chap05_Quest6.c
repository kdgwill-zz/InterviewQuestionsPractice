/** 
 * Write a program to swap odd and even bits in an integer with as few instructions as possible
 * (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on). 
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
#include <stdio.h>
#include "../dataStructure/Utility.h"
#include <time.h>   //time
#include <stdlib.h> //srand rand

/**
 * assume 64 bit long to cover integers shorts, byte(if applicable) and char
 */
long swapBits(long num){
	long mask = 0xAAAAAAAAAAAAAAAA;//even bit mask 10101010101010101010101010101010
	long m = (num & mask)>>1;//set mask then shift right once
	num = (num & ~mask)<<1;//mask only odd bits and shift to left once
	num |= m;//shift and set even bits
	return num;
}

int main(){
	srand(time(NULL));
	for(int i=0;i<10;i++){
		int n_1 = (rand()%32)+1;
		int n_2 = swapBits(n_1);
		BinaryInteger * n1 = intToBinary(n_1);
		BinaryInteger * n2 = intToBinary(n_2);
		printf("%2d[0b%6s]\t%2d[0b%6s]\n",
				n1->num,n1->binary,n2->num,n2->binary);
		free(n1);
		free(n2);
	}


	return 0;
}
