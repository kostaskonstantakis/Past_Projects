#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>
#include <time.h>

int main(int argc, char *argv[])
{
int rows=0;
int columns=0;
char a[rows][columns]; 
FILE *ifp; //input file pointer
FILE *ofp; //output file pointer
int i,j,g; //g used for counting generations
char c=' ';
int generations=0; //counts the seconds the program runs.
int living_neighbors=0;

if(argc<4) printf("More arguments! $ game_of_life input.txt GENERATIONS output.txt\n"); 
else
{


generations=atoi(argv[2]);
ifp=fopen(argv[1],"r");

	if(ifp) 
	{
    	  fscanf(ifp, "%d", &rows);
          fscanf(ifp, "%d", &columns);
	
		 for(i=0;i<rows;i++)
		 {
		  for(j=0;j<columns;j++)
		  {	fscanf(ifp,"%c",&c);
			if(c==' ') 
			{
				
		 		a[i][j]=c; 
			}
			else if(c=='*')  a[i][j]=c;  
			fscanf(ifp,"%c",&c); 			
		  }
		 }
	
		
 
        fclose(ifp);
	}

#pragma omp parallel for shared(generations,a,i,j) private(g) 
     for(g=0;g<generations;g++)
     {
		for(i=0;i<rows;i++)
		{
			for(j=0;j<columns;j++)
			{	
				living_neighbors=0; //for every cell, each time we suppose they don't have any living neighbors left.
				if(i<0||i>rows-1||j<0||j>columns-1) printf("Array out of bounds!\n"); 
		//Now, check how many neighbors every cell has, and how many of those are alive as well
							else if(0<i-1<rows-1&&0<j-1<columns-1)
							{
								//an existing neighbor
								if(a[i-1][j-1]=='*')
								living_neighbors++;
								else break; //a dead neighbor, nothing to do, I think?
							} 

							else if(0<i-1<rows-1&&0<j<columns-1)
							{
											//an existing neighbor
											if(a[i-1][j]=='*')
													living_neighbors++;
											else break; //a dead neighbor, nothing to do, I think?
							}

							else if(0<i-1<rows-1&&0<j+1<columns-1)
							{
										//an existing neighbor
										if(a[i-1][j+1]=='*')
												living_neighbors++;
										else break; //a dead neighbor, nothing to do, I think?

							}

							else if(0<i<rows-1&&0<j-1<columns-1)
							{
									//an existent neighbor
									if(a[i][j-1]=='*')
											living_neighbors++;
									else break; //a dead neighbor, nothing to do, I think?

							}

							else if(0<i<rows-1&&0<j+1<columns-1)
							{
									//an existing neighbor
									if(a[i][j+1]=='*')
											living_neighbors++;
									else break; //a dead neighbor, nothing to do, I think?

							}

							else if(0<i+1<rows-1&&0<j-1<columns-1)
							{
							 //an existing neighbor
									if(a[i+1][j-1]=='*')
											living_neighbors++;
									else break; //a dead neighbor, nothing to do, I think?
							}

							else if(0<i+1<rows-1&&0<j<columns-1)
							{
									//an existing neighbor
									if(a[i+1][j]=='*')
											living_neighbors++;
									else break; //a dead neighbor, nothing to do, I think?
							}

							else if(0<i+1<rows-1&&0<j+1<columns-1)
							{
									//an existing neighbor
									if(a[i+1][j+1]=='*')
											living_neighbors++;
									else break; //a dead neighbor, nothing to do, I think?
							}

						//Rules here
						if(a[i][j]==' '&&living_neighbors==3) a[i][j]=='*'; //In the next generation, it will be alive.
						else if(a[i][j]=='*'&&(living_neighbors<2||living_neighbors>3)) a[i][j]==' '; //It dies. I might split this up.
						else if(a[i][j]=='*'&&(living_neighbors==2||living_neighbors==3)) break; //just survives
		
			}

		} 										
												 
	}

						//Now write in output file!
						ofp=fopen(argv[3],"w+");
                                                 if(ofp)
                                                 {
                                                        for(i=0;i<rows;i++)
                                                        {
                                                                for(j=0;j<columns;j++)
                                                                {
                                                                        fprintf(ofp,"|%c|",a[i][j]);
                                                                        if(j==columns-1) fprintf(ofp,"\n",a[i][j]);

                                                                }
                                                        }
                                                 } 
						fclose(ofp); //Now, close the output file.

} 

} 
