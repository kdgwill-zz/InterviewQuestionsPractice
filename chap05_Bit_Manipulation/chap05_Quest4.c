/**
 * Explain what the following code does: ((n & (n-1))==0)
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
#include "../dataStructure/Utility.h"

int main(){
	for (int i=0;i<=32;i++){
		int n = i;
		int mn = n-1;
		const char * check=((n & (n-1))==0) ? "true" : "false";
		BinaryInteger * n1 = intToBinary(n);
		BinaryInteger * n2 = intToBinary(mn);
		printf("N{%2d=0b%6s},\t(N-1){%2d=0b%6s\tCHECK=%6s\n",n1->num,n1->binary,n2->num,n2->binary,check);
		free(n1);
		free(n2);
	}
	printf("\n\n\n");
	printf("As can be deffered from the sample above the check (n&(n-1))==0 will be true if n is part of the binary sequence\n");
	printf("ie. (n & (n01))==0 checks if n is a power of 2\n");
	printf("eg. 1 2 4 8 16 32 64 128 256 512 1024 2048 4096\n");
	printf("\n\n\n");
	return 0;
}
