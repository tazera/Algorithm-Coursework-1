/*
  Changes made by B7077493
    */
/*****************************************************/
//*** Sort class currently contains some initial    ***/
/*** methods for implementing sorting algorithms   ***/
/***                                               ***/
/***     Initial Author: Jason Steggles 20/09/19   ***/
/***     Extended by: ?                            ***/
/*****************************************************/

import java.io.*;
import java.text.*;
import java.util.*;
public class Sort {

/** Size of array **/
private int size;

/** Number of used elements in array **/
private int usedSize;

/** Array of integers **/
private int[] A;//declaring the array

/** Global variables for counting sort comparisons **/
public int compIS; /** Global comparison count for Insertion Sort **/
public int compQS; /** Global comparison count for Quicksort **/
public int compNS; /** Global comparison count for newsort **/

/*****************/
/** Constructor **/
/*****************/
public Sort(int max)
{
    /** Initialiase global sort count variables **/
    compIS = 0;
    compQS = 0;
    compNS = 0;
    
    /** Initialise size variables **/
    usedSize = 0;
    size = max;
    
    /** Create Array of Integers **/
    A = new int[size];
}

//INSERTION SORT
	public void insertionSort() {  
 
 
		for (int i = 1; i < usedSize; i++)
		  {

			int key = A[i]; // Store next value to insert
			
			int j = i;  
              
			// We add one to the counter as comparison will be made no matter the result  true or false
			compIS++; 
			while (j > 0 && key < A[j - 1])   // Find correct position for key
			{

				A[j] = A[j - 1];  // Pushing space left
				j = j - 1; 
				
				//As we are inside loop comparison is made 
				compIS++;

			}

			A[j] = key;  // Insert key into space
		}
	}
//Quicksort
public void quickSort(int L, int R) 
{
	int p; // p is location of pivot after partition and L & R are left and right array pointers 
	
	if(R > L) {
		
		p = partition(A,L,R);
		quickSort(L,p-1); // Sort the left half
		quickSort(p+1,R); // Sort the right half
		// The algorithm is recursive
	}
	
}
// The partition algorithm for quickSort
public  int partition(int A[],int left,int right) 
{
	int v = A[right];//Pivot value
    int pL = left; // Scanning pointer
    int pR = right; // Scanning pointer
    
    
    while(pL < pR) // Repeat until scanning pointers cross
    {	
    	  compQS++; // Comparisons are made with the pivot 
    	while(A[pL] < v)  //Make comparison 
    	{
    	  
    		pL = pL + 1; // True move left pointer
    		 compQS++; // Count ++ if the comparison is true
    	}
    	
    	    compQS++; // Same logic we increment it before the loop as we are sure the comparison is made
    	while(A[pR] >= v && pR > left) // make comparison
    	{
    		
  
    		
    		 pR = pR - 1; //Move right pointer
    	       compQS++;  // And we increment here again as comparisons within the loop are made
    	} 
    	
    	if(pL < pR) 
    	{
    	
    		// Swap
    	int temp = A[pL];
    	A[pL] = A[pR];
    	A[pR] = temp; 
    	}
    	
    }
    // Swap
    int temp2 = A[pL];
	A[pL] = A[right];
	A[right] = temp2;
   
	
	
	return pL;
}


public void newSort() 
{
	int min,pos,i;
	pos = 0;
	while(pos < usedSize) {
		
		min = findMinFrom(A,pos);
		for(i = pos; i < usedSize; i++) {
			compNS++;  // We add one  comparison since comparisons  are made, also there is no need to get in the if statement 
			
			if(A[i] == min) {
				
				int temp = A[i];
				A[i] = A[pos];
				A[pos] = temp;
				
				
				pos++;
				
			}
		}
		
	}
}

	public int findMinFrom(int A[], int pos) 
	{
		int i, min;
		min = A[pos];

		for (i = pos + 1; i < usedSize; i++) {
			compNS++; // Pretty much the same logic, comparisons are made 
			if (A[i] < min) {
				
				min = A[i];
				
			}
		}

		return min;

	}






/*********************************************/
/*** Read a file of integers into an array ***/
/*********************************************/
public void readIn(String file)
{
   try
   {
       /** Initialise loop variable **/
       usedSize = 0;
       
       /** Set up file for reading **/
       FileReader reader = new FileReader(file);
       Scanner in = new Scanner(reader);
       
       /** Loop round reading in data while array not full **/
       while(in.hasNextInt() && (usedSize < size))
       {
           A[usedSize] = in.nextInt();
           usedSize++;
       }
       
    }
    catch (IOException e)
    {
       System.out.println("Error processing file " + file);
    }
   
}

/**********************/
/*** Display array  ***/
/**********************/
public void display(int line, String header)
{
    /*** Integer Formatter - three digits ***/
    NumberFormat FI = NumberFormat.getInstance();
    FI.setMinimumIntegerDigits(3);

    /** Print header string **/
    System.out.print("\n"+header);

    /** Display array data **/
    for (int i=0;i<usedSize;i++)
    {
        /** Check if new line is needed **/
        if (i%line == 0) 
        { 
            System.out.println(); 
        }
        
        /** Display an array element **/
        System.out.print(FI.format(A[i])+" ");
    }
}

}  /** End of Sort Class **/
