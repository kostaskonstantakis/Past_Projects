#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>
#include <time.h>

char **create_array(int rows, int columns){
	int i;
	char ** a = malloc(rows * sizeof(char*));
	//#pragma omp parallel
	for(i=0;i<rows;i++){
		a[i] = malloc(columns * sizeof(char));
	}
	
	return a;
}

int main(int argc, char *argv[])
{

clock_t begin;

//Start counting time
begin = clock();
double execution_time;

int rows=0;
int columns=0;
char **a, **b;

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
          fscanf(ifp, "%d\n", &columns);
		  
		  a = create_array( rows,  columns);
		  b = create_array( rows,  columns);
	
		 //#pragma omp parallel
		 for(i=0;i<rows;i++)
		 {
			
		 // #pragma omp parallel
		  for(j=0;j<columns;j++)
		  {	fscanf(ifp,"%c",&c);
			
			fscanf(ifp,"%c",&c); 
			if(c==' ') 
			{
				
		 		a[i][j]=c;
			}
			else if(c=='*') { a[i][j]=c;   }
			

						
		  }
		  fscanf(ifp,"%c\n",&c); 
		 
		 }
	
		
 
        fclose(ifp);
	}
	

     #pragma omp parallel
     for(g=0;g<generations;g++)
     {
		#pragma omp parallel
		for(i=0;i<rows;i++)
		{
			#pragma omp parallel
			for(j=0;j<columns;j++)
			{	
							living_neighbors=0; 
							//for every cell, each time we suppose they don't have any living neighbors left.
				
							int x,y;
		
							//#pragma omp parallel			
							for(x=i-1;x<=i+1;x++){
								//#pragma omp parallel
								for(y=j-1;y<=j+1;y++){
									
									if(i==x && j==y)continue;
									
									if(x>=0 && x<rows && y>=0 && y<columns){
										if(a[x][y]=='*')
											living_neighbors++;
									}
								}
							}			
			

						//Rules here
						
						b[i][j] = a[i][j];
							
						if(a[i][j]==' '&&living_neighbors==3) 
							b[i][j]='*'; //In the next generation, it will be alive.
						if(a[i][j]=='*'&&(living_neighbors<2||living_neighbors>3)) 
							b[i][j]=' '; //It dies.
						if(a[i][j]=='*'&&(living_neighbors==2||living_neighbors==3)) 
							b[i][j]='*'; //just barely survives.
						
		
			}
			

		} 	

		int i2,j2;
		//#pragma omp parallel
		for(i2=0;i2<rows;i2++)
		{	
			//#pragma omp parallel
			for(j2=0;j2<columns;j2++){
				a[i2][j2] = b[i2][j2];
			}
		}

												 
	}

						//Now write in the output file!
						ofp=fopen(argv[3],"w+");
						
                                                 if(ofp)
                                                 {
							//#pragma omp parallel
                                                        for(i=0;i<rows;i++)
                                                        {
								//#pragma omp parallel
                                                                for(j=0;j<columns;j++)
                                                                {
                                                                        fprintf(ofp,"|%c",a[i][j]);
                                                                        if(j==columns-1) fprintf(ofp,"|\n",a[i][j]);

                                                                }
                                                        }
                                                 } 
						fclose(ofp); //Now, close the output file.

} 


 execution_time=(double)(clock() - begin) / CLOCKS_PER_SEC;
 printf("Game of life has been running for %lf seconds, with generations=%d, testfile=%s.\n\n", execution_time, generations, argv[1]);
    

}
