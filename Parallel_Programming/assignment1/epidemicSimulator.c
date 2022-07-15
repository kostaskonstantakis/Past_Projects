#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>
#include <time.h>
 
int T = 10; //transmission rate-pososto metadosis epi tois ekato
int M = 5; //mortality rate-pososto thnitotitas epi tois xilihs
int D = 10; //days after recovery-meres anarrosis
int N=0; //days the epidemic lasts

FILE *ifp; //input file 
FILE *ofp; //output file
char* ifn; //input file name

typedef struct Komvos {
  long k;
  int infected;
  int recovered;
  int dead; 
  int day; //on which day the node got infected
  int D; //quarantine days counter
  struct Komvos *neighbors; //the contacts/"friends" each node has
  struct Komvos *next;
}komvos;

komvos *node=NULL; // creating an object to traverse the struct in the function. 
komvos *HEAD=NULL; //The head of the list (1st node of the "Graph")

komvos *lookup(long k, komvos *start){
	
	komvos* tmp = start;
	while(tmp){
		if(tmp->k==k)return tmp;
		tmp=tmp->next;
	}

	return NULL;

}

void insert(long k1, long k2, komvos **start){
	
	komvos *n = lookup(k1, *start);
	if(n == NULL){
		n = malloc(sizeof(komvos));
		n->k=k1;
		n->neighbors=NULL;
		n->infected = 0;
		n->recovered = 0;
		n->dead = 0;
		n->day = 0;

		n->next = *start;
		*start = n;
	}

	komvos *k = lookup(k2, n->neighbors);
	if(k == NULL){
		k = malloc(sizeof(komvos));
                k->k=k2;
                k->neighbors=NULL;
                k->next =  n->neighbors;
                n->neighbors = k;
	}
}


void print_komvous(komvos *start, int mode){

	if(mode == 1){//print komvwn

		komvos *n = start;
		while(n){
			printf("%ld -> ", n->k);
			print_komvous(n->neighbors, 2);
			printf("\n");
			n=n->next;
		}
	}else{//print neighbors-pou einai oi epafes tou kathena
		komvos *n = start;
                while(n){
                        printf("%d", n->k);
                        n=n->next;
			if(n!=NULL)printf(", ");
                }
	}
}


void consume_file(char * path){
	
	FILE *ifp = fopen(path, "r");
	long x, y;
	char line[100];
	while(fgets(line,100,ifp)){
	
		if(line[0] == '#'){continue;}
	
		sscanf(line, "%d %d", &x, &y) ;
	
	
		insert(x,y,&HEAD);
		insert(y,x,&HEAD);
	}

	//print_komvous(HEAD, 1);
	fclose(ifp);
}

int NewCases,TotalCases,Recovered,Active=0,NewDeaths,TotalDeaths;


void print_stats(int day){//Now, write in output file!
	
		//print the header only on the first day 
		if(day==1) fprintf(ofp, "Day,NewCases,TotalCases,Recovered,Active,NewDeaths,TotalDeaths\n");        
		
		//Then, continue printing on the output file
		fprintf(ofp, "%d,%d,%d,%d,%d,%d,%d\n",day,NewCases,TotalCases,Recovered,Active,NewDeaths,TotalDeaths);

}

void infection_process(int days, char * path){ //simulate the infection process

	ofp=fopen("output.txt", "w+");
   	
	FILE *fp = fopen(path, "r"); //read the infected nodes from the second input file
	NewCases=0;
	long x;
	while(fscanf(fp, "%ld", &x)==1){
		
		
		komvos * n = lookup(x, HEAD);
		//printf("%ld\n",x);
		//assert(n != NULL);
		if(n!=NULL){
			n->infected = 1;
			Active++;
			n->day=1;
			n->D=D;
			NewCases++;
		}
		
	}
	TotalCases+=NewCases;
	
	print_stats(1); //print the statistics for the first day of the epidemic
		
	int i=0;
	
	for(i=2;i<=days;i++){ //for every day from day 2 onwards
			NewDeaths=0; //compute new deaths
			NewCases=0;  //compute new cases
			Active=0; //compute new active cases
		
			komvos *n= HEAD;
			while(n!=NULL){ //search everyone 
				
				if(n->infected == 1){ //if infected
					n->D--; //decrease his quarantine duration by one day, every day
					Active++; //another active case for the books
		
					
					if(n->D == 0){ //if node has recovered
						n->recovered = 1;
						Recovered++;
					
						Active--;
					}else{
						//check if node dead
						int pith = rand()%1000;
						if(pith < M){ //There's a M chance to die, after being infected
							Active--;
							//n->infected=0;
							n->dead=1;
							NewDeaths++;
						}
					}
				}
				
				n=n->next;
			}
			
			n = HEAD;
			while(n!=NULL){	
				
				if(n->infected && n->day==i-1){
					komvos *n2 = n->neighbors;
					while(n2!=NULL){
						komvos *n3 = lookup(n2->k, HEAD);
						if(!n3->dead && !n3->infected && !n3->recovered){
							int pith = rand()%100;
							if(pith < T){ //You get infected with a T possibility
								assert(n3 != NULL);
								n3->infected = 1;
								Active++;
								n->D=D;
								n3->day=i;
								NewCases++;
								
							}
						}
						
						
						n2=n2->next;
					}
				}
				
				if(n->recovered)n->infected=0;
				if(n->dead)n->infected=0;
				
				n=n->next;
			}
		TotalCases+=NewCases;
		TotalDeaths+=NewDeaths;
		print_stats(i);
	}
	
	fclose(ofp);
	
}

int main(int argc, char *argv[])
{
clock_t begin;
double execution_time;

    //Start counting time 
    begin = clock();

srand(time(NULL)); //randomize the seed

if(argc<4) printf("More arguments! $ ask1 input.txt N infectedCells.txt\n"); 
else
{

//Now, let's try to parallelize the execution.

	#pragma omp parallel	
	{
		N=atoi(argv[2]);
		ifn=argv[1];
		consume_file(ifn);
		infection_process(N, argv[3]);

	}
} 

 execution_time=(double)(clock() - begin) / CLOCKS_PER_SEC;
 printf("The program has been running for %lf seconds, with N=%d, testfile=%s.\n\n", execution_time, N, ifn);

return 0;
} 
