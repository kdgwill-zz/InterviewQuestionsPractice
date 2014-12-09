/** 
 * An array 'A' contains all the integers from 0 to n, except for one number which is
 * missing. In this problem, we cannot access an enitre integer in 'A' with a single
 * operation. The elements of 'A' are represented in binary, and the only operation 
 * we can use to access them is "fetch the jth bit of A[i]," which takes constant 
 * time. Write code to find the missing integer. Can you do it in O(n) time? 
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
#include <stdio.h>
#include "../dataStructure/Utility.h"
#include <time.h>    //time
#include <stdlib.h>  //srand rand

int fetch(int arr[],int pos,int shift);
int findMissNum(int arr[],int length);

int main(){
	srand(time(NULL));

	int length = rand()%30+1+5;//5-30
	length--;//subtract missing integer
	int missNum = rand()%length;
	int array[length];

	for(int i=0, c=0; i<length+1; i++){//replace missing integer
		if(i==missNum)continue;
		array[c++]=i;			
	}

	printf("LENGTH IS %d\n",length);
	for(int i=0; i<length; i+=3){
		printf("%d\t",array[i]);
		if(i+1<length){
			printf("%d\t",array[i+1]);
		}
		if(i+2<length){
			printf("%d",array[i+2]);
		}
		printf("\n");
	}
	int missingNumber = findMissNum(array,length);
	BinaryInteger * n1 = intToBinary(missingNumber);
	BinaryInteger * n2 = intToBinary(missNum);
	printf("Supposed MISSING NUMBER IS %d[0b%s]\n",n1->num,n1->binary);
	printf("Actual MISSING NUMBER IS %d[0b%s]\n",n2->num,n2->binary);
	free(n1);
	free(n2);
	return 0;
}

int fetch(int arr[],int pos,int shift){
	return arr[pos] & (1 << shift);
}

/**
 * count each number in binary colum and if their is a 
 * difference between the counted and what it should be 
 * produce a 1 else produce 0
 * do this for each possible column
 *
 * Not o(n)
 * could be done recurseviley 
 */ 
int findMissNum(int arr[],int length){
	int num=0;
	for(int j=0;j<32;j++){
		int fc = 0;//what actual count should be
		int bc = 0;//what count is
		for(int i = 0; i < length; i++){
			bc += fetch(arr,i,j);
			fc += i & (1 << j);
		}
		fc+=length & (1<<j);//remember have to get actual length 0-n because array removed rand num
		if(bc==0)break;
		int bit = fc-bc>0?1:0;//
		num |= bit << j;
	}
	return num;
}
