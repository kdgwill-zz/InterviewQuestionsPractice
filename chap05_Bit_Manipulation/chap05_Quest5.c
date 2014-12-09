/** 
 * Write a function to determine the number of bits required 
 * to convert interger A to integer B
 * 
 * EXAMPLE:
 * Input: 31, 14
 * Output: 2
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
#include <stdio.h>
#include "../dataStructure/Utility.h"
#include <time.h>    //time
#include <stdlib.h>  //srand rand
/**
 * Easiest way use XOR to get bits that are different 
 * then count set bits
 */
int function(int a,int b){
	int c = 0;
	//for(int i=a^b; i!=0; c+=i&1,i>>=1);
	//Above is correct but could result in wrong answer depending on compiler
	for(int i=a^b; i!=0;i>>=1){
		c+= i & 0x1;
	}
	//Alternatively:
	//cotinuously flip least siginificant bit and count how long it takes to reach 0
	//for(int i=a^b;i!=0; i=i&(i-1))c++;	
	return c;	
}

int main(){
	srand(time(NULL));
	for(int i=0;i<10;i++){
		int n_1 = (rand()%32)+1;
		int n_2 = (rand()%32)+1;
		int n_3 = function(n_1,n_2);
		BinaryInteger * n1 = intToBinary(n_1);
		BinaryInteger * n2 = intToBinary(n_2);
		printf("%2d[0b%6s]\t%2d[0b%6s]\tAnswer=%2d\n",
				n1->num,n1->binary,n2->num,n2->binary,n_3);
		free(n1);
		free(n2);
	}

	return 0;
}
