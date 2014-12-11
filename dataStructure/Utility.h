#ifndef dataStructures
#define dataStructures
#include <stdio.h>  //printf
#include <stdlib.h> //calloc free
#include <string.h> //memcpy free

int bitCount(int);

typedef struct {
	int num;
	int numBits;
	char binary[1];
}BinaryInteger;

BinaryInteger * intToBinary_MinBits(const int num,const int minBits) {
	int bits = minBits<2?2:minBits;//"0\0"
	char * binary = NULL;
	if( num > 0 ){//make sure num is not 0
		int btc = bitCount(num);//add \0
		if(bits<btc)bits=btc;
		binary = (char *)calloc(bits+1,sizeof(char));
		if (binary==NULL){
			printf ("Error allocating memory\n");
			exit(EXIT_FAILURE);
		}
		memset(binary,'0',bits);
		binary[bits]='\0';//subtract extra
		//since know number of bits count down
		for(int i = bits-1, temp=num; temp!=0; i--){
			if (i<0){
				printf ("Error bit allocation\n");
				exit(EXIT_FAILURE);
			}
			binary[i] = (temp & 1)==1?'1':'0';
			temp >>= 1;
		}
	}
	//size of struct and size of character array remember size of character is 1 
	//also since used char binary[1] subtract 1 from total 
	BinaryInteger * binInt = (BinaryInteger *)malloc(sizeof(BinaryInteger)+bits-1);
	binInt->num=num;	
	binInt->numBits=bits;
	memset(binInt->binary,'0',bits);
	binInt->binary[bits] = '\0';	
	if(binary!=NULL){
		memcpy(binInt->binary,binary,bits+1);//+1 remember termination
	}
	free(binary);
	return binInt;
}

BinaryInteger * intToBinary(const int num) {
	return intToBinary_MinBits(num,0);	
}

int bitCount(int num){
	int bits=1;
	//bits = (int)(fastlog(num)/fastlog(2.0)+1);//get the number of bits in the number 2^bit>=num
	//calling math.h requires linking math libraries which can be a hassle
	while (num>>=1)++bits;
	return bits;
}

char *replace(const char * text, char find, const char * replace) {
	int count = 0;
	const char *t;
	for(t=text; *t; t++)
		count += (*t == find);

	size_t rlen = strlen(replace);
	char *res = (char *)malloc(strlen(text) + (rlen-1)*count + 1);
	char *ptr = res;
	for(t=text; *t; t++) {
		if(*t == find) {
			memcpy(ptr, replace, rlen);
			ptr += rlen;
		} else {
			*ptr++ = *t;
		}
	}
	*ptr = 0;
	return res;
}


int isNumericChar(char x){
    return (x >= '0' && x <= '9')? 1: 0;
}
 
// A simple atoi() function. If the given string contains
// any invalid character, then this function returns 0
int myAtoi(char *str){
    if (str == NULL)
       return 0;
 
    int res = 0;  // Initialize result
    int sign = 1;  // Initialize sign as positive
    int i = 0;  // Initialize index of first digit
 
    // If number is negative, then update sign
    if (str[0] == '-')
    {
        sign = -1;
        i++;  // Also update index of first digit
    }
 
    // Iterate through all digits of input string and update result
    for (; str[i] != '\0'; ++i)
    {
        if (isNumericChar(str[i]) == 0)
            return 0; // You may add some lines to write error message
                      // to error stream
        res = res*10 + str[i] - '0';
    }
 
    // Return result with sign
    return sign*res;
}
#endif
