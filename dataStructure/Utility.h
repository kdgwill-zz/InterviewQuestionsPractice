#ifndef dataStructures
#define dataStructures
#include <stdio.h>

void intToBinary(int num) {
	char binary[32];
	int i = 0;
	while(num != 0) {
		binary[i++] = (num & 1)==1?'1':'0';
		num >>= 1;
	}
	binary[i--] = '\0'; 
	for(int j=0;j<=i/2;j++,i--){
		int temp = binary[j];
		binary[j]=binary[i];
		binary[i]=temp;
	}
	printf("%s\n",binary);
}
#endif
