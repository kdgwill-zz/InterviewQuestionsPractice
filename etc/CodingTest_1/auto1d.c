/*
Write a function auto1d() that prints the evolution of 1-D cellular automata.

A cellular automaton is an algorithm for generating a row of child cells given 
an input row of parent cells. Each cell is either 'on' or 'off'. Every cell in 
the child generation is on or off based on the values of cells in the parent generation.

For example, think of each generation as a row in a grid, with each child row below its 
parent row. One rule, which we will call rule 18, says that a child cell is on if its 
immediate parent cell is off and only one of the cells to the immediate left or right 
of its parent is on, but not both. Here are a few cases:

parent:  (X) ( ) ( )
child:       (X)         // on, because left parent is on, top & right are off

parent:  ( ) ( ) (X)
child:       (X)         // on, because right parent is on, top & left are off

parent:  (X) (X) ( )     
child:       ( )         // off, because top parent is on
Starting with just a single cell set to 'on' in the center of the first generation, 
rule 18 produces a nice pattern (sides truncated):

          o
         o o
        o   o
       o o o o
      o       o
     o o     o o
    o   o   o   o
   o o o o o o o o
  o               o
 o o             o o
o   o           o   o
 o o o         o o o
o     o       o     o
 o   o o     o o   o
o o o   o   o   o o o
     o o o o o o
    o           o
   o o         o o
  o   o       o   o
 o o o o     o o o o
o       o   o       o

There is a numerical classification for all 1-D automata based on the values of a cell's 
3 nearest neighbors (directly above, above left, above right) in the parent. 
For example, here is rule 18 again (where 1 is on, 0 is off):

  parent:  111  110  101  100  011  010  001  000
  child:    0    0    0    1    0    0    1    0 

This is called rule 18 because the rules for which patterns in the parent produce a 1 in the child, 
interpreted as a binary number, have value 18. (Reading across the child row, 00010010 18.) 
Notice that since all possible states in the parent are enumerated, all possible automata of this 
type can be completely classified by a single 1-byte number (0-255). (This is pretty cool when you think about it!)

For nice pictures of all the 1D automata, see the atlas at Wolfram.

You are going to write a function called auto1d, with the following signature: 
.
.
int auto1d(int rule, char * positions, int limit);
.
.
The function will accept a rule (0-255) and a null-terminated string, which will represent the initial state of the 
parent cells, and print the evolution of the automaton for generations. Any non-space (and non-null) character 
will be interpreted as an 'on' position, anything else is are considered 'off'. Additional considerations 
(if you are pressed for time, make it work first, then attend to these):

Boundary conditions: anything off the edges of the grid is considered 'off'.

Halting conditions(1): The function should print the initial state and the evolution of the automaton to stdout, 
and then subsequent ones until it has printed <limit> generations, and then stop. 

Halting conditions(2): If the next generation is identical to the prior one, then the automaton has stopped evolving 
and the function should print the duplicate row and return immediately.

Return values: The function should return the number of rows it has printed beyond the initial row.

Characters: Please use a lowercase letter 'o' to denote on, and a space to denote off, in your output. 
(Looks nice, and standardizing on this lets us check your output programmatically.)

Bulletproofing: Assume for purposes of the exercise that your inputs are valid. Don't bother checking if 
e.g. limit < 0 or the positions string you were passed is NULL.

The first evolution above was the correct output for

  auto1d(18, "          o          ", 20).
And here is the output for

  auto1d(204, "          o          ", 20),
the rule which just reproduces its input:

         o
         o
Attach your working code as auto1d.c

 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define EMPTY ' '
#define SET 'o'

int auto1d(int /*rule*/, const char * /*positions*/, int /*limit*/);
int * processRule(int /*rule*/,int[8] /*array*/);
/*
int main(){
  	auto1d(18, "          o          ", 20);
  	auto1d(204, "          o          ", 20);
	return 0;
}
*/
/**
*loop through previous row creating a binary for each position
*/
int auto1d(int rule, const char * positions, int limit){
	printf("\n\n\n");//formatting
	int length = strlen(positions);
	char * prev = (char *) calloc(length,sizeof(char));//also clears mem
	char * cur = (char *) calloc(length,sizeof(char));//also clears mem
	int check[8]={0};processRule(rule,check);//will return a an array of size length 
	strncpy(prev,positions,length); 
	printf("%s\n", prev);//print first row
	for(int i = 0;i<limit;i++){
		for(int j = 0;j<length;j++){
			int x = 0x000;
			//Handle Left 0b100
			if(j!=0 && prev[j-1]!=EMPTY) x|=4;//0b100;
			//Handle Mid  0b010
			if(prev[j]!=EMPTY) x|=2;//0b010;
			//Handle Rght 0b001
			if(j!=length-1 && prev[j+1]!=EMPTY) x|=1;//0b001;
			//check if value is set or not	
			cur[j]=check[x]!=0?SET:EMPTY;
		}
		printf("%s\n",cur);
		if(strncmp(cur,prev,length)==0)break;
		strncpy(prev,cur,length);
	}
	free(prev);	
	free(cur);	
	printf("\n\n\n");//formatting
	return 0;
}

/**
  * Each binary position is checked its position determins if it is 0 or not
  */
int * processRule(int rule,int check[8]){//since only checking if 3 adjacent parents max it can ever be is 7
	for(int i=0; rule>=0 && i<8; i++){
		check[i] = rule & 0x1;
		rule >>= 1;	
	}
	return check;
}
