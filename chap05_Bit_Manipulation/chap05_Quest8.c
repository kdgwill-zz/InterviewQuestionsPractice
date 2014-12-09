/** 
 * A monochrome screen is stored as a single array of bytes, allowing eight consec-
 * utive pixels to be stored in one byte. The screen has width w, where w is divisible
 * by 8 (that is, no byte will be split across rows). The height of the screen, of course,
 * can be derived from the length of the array and the width. implement a function
 * drawHorizontalLine(byte[] screen, int width, int x1, int x2, int y) which draws a hori-
 * zontal line from (x1,y) to (x2,y)
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
#include <stdio.h>
#include "../dataStructure/Utility.h"
#include <time.h>    //time
#include <stdlib.h>  //srand rand memset

#define WIDTH  8
#define HEIGHT (WIDTH*8)/8 * 3 //8:3 display ratio remember must account for 8 pixels per byte
#define LENGTH WIDTH*HEIGHT

typedef unsigned char u_char;

/**
 * Possibility to condense code 
 *
 * @param screen[]	monochrome screen 
 * @param length	the size of the screen array
 * @param width 	the width of the screen
 * @param x1 		coloum 1
 * @param x2 		coloum 2
 * @param y  		row
 */ 
void drawHorizontalLine(u_char screen[], int length, int width, int x1, int x2, int y);
void drawPoint(u_char screen[],int length,int width, int x, int y);
void drawScreen(u_char screen[], int length, int width);

int main(){
	u_char screen[LENGTH] = {0};
	//RANDOM TEST
	drawHorizontalLine(screen,LENGTH,WIDTH,17,48,10);
	drawHorizontalLine(screen,LENGTH,WIDTH,18,49,11);
	drawHorizontalLine(screen,LENGTH,WIDTH,19,50,12);
	drawHorizontalLine(screen,LENGTH,WIDTH,20,51,13);
	drawHorizontalLine(screen,LENGTH,WIDTH,21,52,14);
	drawHorizontalLine(screen,LENGTH,WIDTH,22,53,15);
	drawHorizontalLine(screen,LENGTH,WIDTH,21,52,16);
	drawHorizontalLine(screen,LENGTH,WIDTH,20,51,17);
	drawHorizontalLine(screen,LENGTH,WIDTH,19,50,18);
	drawHorizontalLine(screen,LENGTH,WIDTH,18,49,19);
	drawHorizontalLine(screen,LENGTH,WIDTH,17,48,20);
	//CLOSE
	drawHorizontalLine(screen,LENGTH,WIDTH,16,16,0);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,17,1);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,18,2);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,19,3);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,20,4);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,21,5);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,22,6);
	drawHorizontalLine(screen,LENGTH,WIDTH,16,23,7);
	//VERY CLOSE
	drawHorizontalLine(screen,LENGTH,WIDTH,2,3,4);
	//THE SAME
	drawHorizontalLine(screen,LENGTH,WIDTH,2,2,3);
	drawPoint(screen,LENGTH,WIDTH,2,3);
	//draw a picture
	for(int x = 6; x<19; x++){
		int y = x+1;
		drawPoint(screen,LENGTH,WIDTH,x,y);	
		drawPoint(screen,LENGTH,WIDTH,19-x+5,y);	
	}
	//DRAW THE SCREEN
	drawScreen(screen,LENGTH,WIDTH);
	return 0;
}

void drawHorizontalLine(u_char screen[],int length,int width, int x1, int x2, int y){
	int start = (width*y)+x1/8;
	int end = (width*y)+x2/8;
	if(start>=length || end>=length || start>end){
		perror("INDEX OUT OF BOUNDS");
		exit(EXIT_FAILURE);
	}
	if(x1==x2){
		int temp = 0x1 << (8-x2%8-1);
		screen[start] |= temp;
		return;
	}	
	if(x1/8==x2/8 && end-start<8){//check if same byte if not check for close proximity
		printf("%d\t%d\t%d\n",x1,x2,y);
		int temp = 0xFF >> x1%8; 
		int temp2 = 0xFF >> (x2%8+1);
		temp &=~temp2;
		screen[start] |= temp;
		return;
	}
	//initialize middle bits
	for(int i=start+1;i<end;i++){
		screen[i]=0xFF;
	}
	//applying a mask is better than a shift in cases of x1==x2
	int mask = 0xFF >> x1%8;//divide x1 by 8 since displaying by bits but screen is by byte
	screen[start] |= mask;//mask these bits
	mask = 0xFF>>(x2%8+1);
	screen[end] |= ~mask;
}

void drawPoint(u_char screen[],int length,int width, int x, int y){
	drawHorizontalLine(screen,length,width,x,x,y);
}

void drawScreen(u_char screen[], int length, int width){
	printf("\n\n\n");
	printf("\n   | ");
	for(int i=0;i<width*width;i++)printf("%d",i/10);
	printf(" | \n   | ");
	for(int i=0;i<width*width;i++)printf("%d",i%10);
	printf(" | \n-- | ");
	for(int i=0;i<width*width;i++)printf("%c",'-');
	int count = 0;
	printf(" | --\n%2d | ",count++);
	for(int i = 0; i < length; i++){
		BinaryInteger * bi = intToBinary_MinBits(screen[i],8);
		char * text = replace(bi->binary,'0',"_");
		printf("%s",text);
		free(text);
		free(bi);

		if((i+1)%width==0 && i!=0 && i!=length-1){
			printf(" | %2d\n%2d | ",count-1,count);
			count++;
		}
	}
	printf(" | %d\n-- | ",count-1);
	for(int i=0;i<width*width;i++)printf("%c",'-');
	printf(" | --\n   | ");
	for(int i=0;i<width*width;i++)printf("%d",i/10);
	printf(" | \n   | ");
	for(int i=0;i<width*width;i++)printf("%d",i%10);
	printf(" | \n\n\n");
}
