//--It is good practice to comment each include statement with functions that depend on it
//--This allows removal of uneeded include statements 
#include <cstdio>
#include <cstring>	//why is cstring required
#include <map>		
#include <iostream>
#include <string> 	//usually imported through other functions
//--used to open and read system file
#include <fstream> 	//--fstream file...
#include <iomanip>      // std::setprecision

/*
// Read file in
// Stock, TimeInterval, Volume Traded, High, Low.
    VOD.L      1            100        183.7 184.0
     BT.L      1             14        448.2 449.4
    VOD.L      2            434        182.4 184.1
     BT.L      2            234        449.5 449.8
..

// Find the total volume traded for each stock
// Find the high and low for each stock.

// Write file a to stdout
// Per stock per interval output the %volume traded in that interval as a percentage of the whole day
// Stock, Interval, %Vol for day.
    VOD.L,   1,         2.0
     BT.L,   1,         1.1
    VOD.L,   2,         8.2
     BT.L,   2,         19.0

// Write file b to stdout
// Stock, day high, day low
    VOD.L,  186.7,   182.4
     BT.L,  445.3,   450.9
*/

//--It is better to use declarative instead of directive this reduces the scope of allowable 
//--functions and prevents similar functions from being called 
//using namespace std;
//typedef basic_string<char> string;
//using std::string;
//--it is also better to use declarative in calling ie std::cout << std::endl 
//and removing the using keyword in production code all together 

//--Their is no reason to typedef basic_string it is better to simply using string 
//--which is itself alrady a typedef of basic_string 
//--In international applications it may be sometimes better to use wstring but string will allow for proper
//--handling of foreign characters if handled correctly ... UTF-8/UTF-16


//--Class is difficult to read it would be better if was properly formatted.
//--Since their are no private fields and the class will not be extended 
//--it would make sense to convert the class to a struct.
//--However nCurLow and nCurHigh should in fact be private with getter 
//--functions to prevent any accidental changes to the value.
//--Also documentation of the fields and their usage would be welcomed
class CHighLow{
	private:
	//--To prevent any latent issues make nCurLow and nCurHigh a large positive and a small negative number respetively
	//--Use the value -1 as a sentinel and check later in the add function 
	////should be using floats not ints	
	float nCurLow = -1;
	float nCurHigh = -1;
	public:
	//--Please comment on all created functions  
	void add(float nHigh,float nLow){
		//--if -1 automatically switch
		if(nCurLow < 0 || nCurHigh < 0){
			nCurLow = nLow;
			nCurHigh = nHigh;
			return;
		}

		//--It is good practice to always use curly brackets to prevent confusion
		if(nHigh > nCurHigh){
			nCurHigh = nHigh;
		} 	
		if(nLow < nCurLow){
			nCurLow = nLow;
		}
	}
	//--Getters added
	/**
	 * Returns the lowest value given for each stock
	 * -1 indicates undefined stock value
	 */ 
	float getCurrentLow(){
		return nCurLow;
	}

	/**
	 * Return the highest value given for each stock
	 * -1 indicates undefined stock value
	 */ 
	float getCurrentHigh(){
		return nCurHigh;
	}
};

//--The Main Function is overused and unruly in a C++ style program this is unexceptable 
//--Move excessive code to external function calls
int main(int argc, char* argv[]){
	//--If using terminal arguments should first check that the number of arguments are valid
	if(argc!=3){
		std::cerr<<"Usage: "<<argv[0]<<" version# FileOfStockListings"<<std::endl;
	}
	
	//--This seems irrelevant and incorrect if it is truly important should check that 
	//--the first argument is numerical and then print out an error if it is not
	//if (!strcmp("version", argv[1])){
	//	cerr << "Using version 1.0 VWAPer" << endl;
	//	return 0;
	//}
	//--Fix Above and remove below
	std::cerr << "Using version " << argv[1] << " VMAPer" << std::endl;


	//--Is this C code or C++ this is getting dirty and contrived 
	//FILE*	file = fopen(argv[2], "r");
	std::fstream file(argv[2],std::ios_base::in | std::ios_base::binary);	

	//--Is their a particular reason the error stream being used over the simple output stream
	std::cerr << "Reading file" << argv[2] << std::endl;

	//--The meaning here is becoming very skewed and their is no documentation I would 
	//--recommend redoing this whole section as their is a mixture of C and C++ style code 
	//--resulting in an unproductive workflow and difficulty for peer understanding.  	
	//--Move this whole section to another function


	//--should be using constants instead of magic numbers
	std::string line;//--better to use string
	//Their will be a lot of wasted space beter to use maps or vectors than hard coded numbers
	char Stocks[1000][10];//--Again Should be using Strings
	int Intervals[1000];
	int Volumes[1000];
	float	Highs[1000];
	float	Lows[1000];
	
	int i = 0; //--What is this being used for horrible practice used later in code as well wors
	//int sum = 0; //--what is this being used for
	//better to use for loop
	for(i = 0;i<1000 && getline( file, line );i++){
		std::sscanf(line.c_str(), "%s %d %d %f %f", 
			Stocks[i], &Intervals[i], 
			&Volumes[i], &Highs[i], &Lows[i]);
		//--horrible practice i will be updated in for loop anyway
		std::cerr << Stocks[i] << std::endl;
		std::cerr << Intervals[i] << std::endl;
		std::cerr << Volumes[i] << std::endl;
		std::cerr << Highs[i] << std::endl;
		std::cerr << Lows[i] << std::endl;
	}

	std::cerr << "Calculating total volumes" << std::endl;
	
	std::map<std::string, int>TotalVolumes;

	//--Improve formatting and document whats happening		
	for (int s = 0; s < i; ++s){//--less than or equal to i, is 1 to many
		//--camel casing is always nice
		std::string stockname = Stocks[s];//--now where using strings -__-
	
		/**--This is not making sense at all
		for (int j =0; j <= i; ++j){
			if (!strcmp(Stocks[j], stockname.c_str())){
				TotalVolumes[stockname] += Volumes[j];
			}
		}*/
		TotalVolumes[stockname] += Volumes[s];
	}
		
	std::cerr << "Calculating high lows" << std::endl;
	
	std::map<std::string, CHighLow> HighLows;
		
	for (int s = 0; s < i; ++s){//--less than or equal to i, is 1 to many
		//better to seperate logic to prevent confusion
		std::string stockName = Stocks[s];
		int hi = Highs[s];
		int lo = Lows[s];
		HighLows[stockName].add(hi, lo);
	}
		
	std::cerr << "Writing files" << std::endl;
	
	//--documentation should be overly descriptive 
	//--explain what you are writing to file a 
	//also set precision because using float	
	std::cout << std::fixed <<  std::setprecision(2);
	// write file a
	for (int s = 0; s < i; ++s){//--less than or equal to i, is 1 to many
		//would be nice to style with snprintf and then print to screen 
		std::string stockName(Stocks[s]);
		//percentVolPerDay
		float pvpd = ((float)Volumes[s]/TotalVolumes[stockName]) * 100.0;
		std::cout << Stocks[s] << "," << Intervals[s] << "," 
			<< pvpd << std::endl;
			//--<< TotalVolumes[Stocks[s]] / Volumes[s] * 100 reversed
	}
	
	// write file b
	std::map<std::string, CHighLow>::iterator itr;
	for (itr = HighLows.begin(); itr != HighLows.end(); itr++){//use for loop
		//--remember this is pointer not actual value use -> not . but (*). also works
		//--asked for high first then low
		std::cout << (*itr).first << "," << 
			(*itr).second.getCurrentHigh() << "," << 
			(*itr).second.getCurrentLow() << std::endl;
	}

	//--This should be 0 anything else indicates failure
	//--In C++ programs this particular return statement is uneeded	
	return 0;
}
