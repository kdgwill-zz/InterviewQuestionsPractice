/**
 * Write a function sortString(char *input) that accepts an ASCII 
 * string and rearranges it in place so that characters with the 
 * largest ASCII values appear first.
 *
 * For example:
 *
 *   sortString("The quick brown fox jumps over the lazy dog.")
 *   will rearrange the string supplied by the caller to:
 *     "zyxwvuutsrrqpoooonmlkjihhgfeeedcbaT.        "
 *     Attach your working code as sortString.c
 * 
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */ 
#include <string.h>
#include <stdio.h>

void sortString(char *);
/*
int main(){
	char string[] = "The quick brown fox jumps over the lazy dog.";
	char result[] = "zyxwvuutsrrqpoooonmlkjihhgfeeedcbaT.        ";
	printf("%s\n", string);
	sortString(string);
	printf("%s\n", string);
	int b = strcmp(string,result);
	printf("Is the answer correct? %s\n",b==0?"True":"False");
}
*/
/**
 * Simple Insertion Sort o(n^2)
 * 
 * 3 way quick sort can be used as well but may be 
 * overcomplicating for simple problem
 */ 
void sortString(char * input){
	int length = strlen(input);
	for(int i = 0;i<length;i++){
		for(int j=i;j>0;j--){
			if(input[j]>input[j-1]){
				//don't have to check for similarity
				input[j]^=input[j-1];
				input[j-1]^=input[j];
				input[j]^=input[j-1];
			}
		}
	}
}


