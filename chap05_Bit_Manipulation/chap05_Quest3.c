/**
 * Given a positive integer, print the next smallest and the next largest number
 * that have the same number of 1 bits in their binary representation
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */

#include <stdio.h>
#include <stdlib.h>
#include "../dataStructure/Utility.h"
int nextS(int); //get next number with same binary 1 Simple
int prevS(int); //get previous number with same binary 1 Simple
//int next(int);  //optimized versions
//int prev(int);  //^^^^^^^^^^^^^^^^^^

void loop(int max,int (*n)(int),int (*p)(int)){
	for(int i=0;i<max;i++){
		int next = n(i);
		int prev = p(i);
		BinaryInteger * pre = intToBinary(prev);
		BinaryInteger * cur = intToBinary(i);
		BinaryInteger * nex = intToBinary(next);
		printf("PRE={%2d[0b%6s]}\tCUR={%2d[0b%6s]}\tNEX={%2d[0b%6s]\n",
			pre->num,pre->binary,cur->num,cur->binary,nex->num,nex->binary);
		free(pre);
		free(cur);
		free(nex);
	}
}

int main() {
	loop(32,&nextS,&prevS);
	
	return 0;
}

int binary1Count(int i){
	int c = 0;
	for (c = 0; i!=0; i >>= 1)c += i & 0x1;
	return c;
}

int check(int num1,int num2){
	return binary1Count(num1)==binary1Count(num2)?1:0;
}

int check2(int num,int dir){
	if(dir==0)return num;
	int num2=num;
	while(num2>=1){
		num2=dir>0?num2+1:num2-1;
		if(check(num,num2)){
			break;
		}
	}
	return num2;

}

int nextS(int num){return check2(num,1);}
int prevS(int num){return check2(num,-1);}
