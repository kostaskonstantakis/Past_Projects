#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <cstdlib>
#include <cstring>
#include <cmath>


int repetitions=0;
int N=0;
long double R=0;
std::string S;
long double  X, Y, Vx, Vy, M;
long double G=6.67e-11;
std::ofstream outputfile; //output file



class BarnesHutBody{
  public:
    char* S;
    long double  X, Y, Vx, Vy, M;
    long double a; //acceleration 
    long double ax;
    long double ay;
	
    long double F; 
    long double Fx = 1; 
    long double Fy = 1;
	 
	
    int type;//0 for internal nodes 1 for final nodes
    int cnt;//counts the sub nodes
	
	
    long double length;
	
	
    BarnesHutBody *parent;
    BarnesHutBody *child1,*child2,*child3,*child4;//tetradiko dentro
	
    BarnesHutBody *next;

    BarnesHutBody(long double  X, long double  Y, long double  Vx, long double  Vy, long double  M, const char* S, int type)
    {
		this->X=X;
		this->Y=Y;
		this->Vx=Vx;
		this->Vy=Vy;
		this->M=M;
		this->S=(char*)S;
		this->cnt=0;
		this->type=type;
		child1=child2=child3=child4=0;
		next=NULL;
		length=0;
		parent=NULL;
		Fx=1;
		Fy=1;
    }

};

BarnesHutBody *ROOT;
//x1 x2 apo ta aristera pros ta deksia
//y1 y2 apo ta katw pros ta panw

BarnesHutBody* create_sublist(BarnesHutBody** elements, int *el,long double  x1, long double  x2, long double  y1, long double  y2);


void print_tab(int level){
	int i;
	for(i=0;i<level;i++){
		printf("  ");
	}
	return;
}
		
		
		/*
		m = m1 + m2
		x =x1m1 + x2m2/m
		y =y1m1 + y2m2/m
		*/

void kentromazas(BarnesHutBody* elements,long double  *M, long double  *X,long double  *Y){
	
	long double  m = 0;
	
	(*X)=(*Y)=0;
	
	BarnesHutBody* temp = elements;
	while(temp){
		
		m+=temp->M;
		
		
		temp=temp->next;
	}
	
	(*M) = m;
	
	temp = elements;
	while(temp){
		
		m+=temp->M;
		
		(*X)+= (temp->X*temp->M)/(long double)m;
		(*Y)+= (temp->Y*temp->M)/(long double)m;
		
		temp=temp->next;
	}
	
}
		

//elements contains bodies that belong to space [x1,x2,y1,y2]
BarnesHutBody* create_tree(BarnesHutBody* R, long double  x1, long double  x2, long double  y1, long double  y2, BarnesHutBody* elements, int elems, int level, BarnesHutBody* PARENT, char *name){

	//if(R == NULL)return NULL;
	
	//malloc node and distribute elements

	if(elems > 1){
		
		//sprintf("%s%d",name,level);
		R = new BarnesHutBody(1,2,3,4,5,name,0);
		R->cnt=elems;
		
		R->length = x2-x1;
		R->parent = PARENT;
		
		//ypologismos kentro mazas
		kentromazas(elements,&R->M, &R->X,&R->Y);
		
		
		
		//panw deksia
		int counter=0;
		BarnesHutBody* elements2 = create_sublist(&elements,&counter,x1 + (x2-x1)/2, x2, y1 + (y2-y1)/2, y2);
	//	print_tab(level);
		//printf("counter A: %d\n", counter);
		
		
		char name2[50];
		sprintf(name2,"%sA%d",name,level);
		R->child1 = create_tree( R, x1 + (x2-x1)/2, x2, y1 + (y2-y1)/2, y2, elements2,counter,level+1,R,strdup(name2));

		//panw aristera
		counter=0;
		elements2 = create_sublist(&elements,&counter,x1 , x2-(x2-x1)/2, y1 + (y2-y1)/2, y2);
		//print_tab(level);
		//printf("counter B: %d\n", counter);
		sprintf(name2,"%sB%d",name,level);
		R->child2 = create_tree( R, x1 , x2-(x2-x1)/2, y1 + (y2-y1)/2, y2, elements2,counter,level+1,R,strdup(name2));
		
		//katw aristera
		counter=0;
		elements2 = create_sublist(&elements,&counter,x1 , x2-(x2-x1)/2, y1 , y2 - (y2-y1)/2);
		//print_tab(level);
		//printf("counter C: %d\n", counter);
		sprintf(name2,"%sC%d",name,level);
		R->child3 = create_tree( R, x1 , x2-(x2-x1)/2, y1 , y2 - (y2-y1)/2, elements2,counter,level+1,R,strdup(name2));
		
		//katw deksia
		counter=0;
		elements2 = create_sublist(&elements,&counter,x1 + (x2-x1)/2, x2, y1 , y2 - (y2-y1)/2);
		//print_tab(level);
		//printf("counter D: %d\n", counter);
		sprintf(name2,"%sD%d",name,level);
		R->child4 = create_tree( R, x1 + (x2-x1)/2, x2, y1 , y2 - (y2-y1)/2, elements2,counter,level+1,R,strdup(name2));
	}else{//0 h 1
		R = new BarnesHutBody(0,0,0,0,0,NULL,1);
		R->cnt=elems;
		R->parent = PARENT;
		//kentromazas(elements,&R->M, &R->X,&R->Y);
		
		if(R->cnt==1){
			R->S = elements->S;
			R->M = elements->M;
			R->X = elements->X;
			R->Y = elements->Y;
			
			R->Vx = elements->Vx;
			R->Vy = elements->Vy;
			
		}
	}

	return R;
}	


BarnesHutBody* insert(BarnesHutBody* elements, BarnesHutBody* element){
	
	element->next = elements;
	elements = element;
	return elements;
	
}


BarnesHutBody* create_sublist(BarnesHutBody** elements, int *el,long double  x1, long double  x2, long double  y1, long double  y2){
	
	
	BarnesHutBody* tmp2=NULL;
	BarnesHutBody* tmp = *elements;
	BarnesHutBody* elements2 = NULL;
	BarnesHutBody*prev=NULL;
	
	while(tmp!=NULL){
		
		tmp2=tmp->next;
		
		int deleted = 0;
		
		if(tmp->X >= x1 && tmp->X <= x2 && tmp->Y >= y1 && tmp->Y <= y2){
			
			
			if(prev){//diagrafi apo tin trexousa lista
				prev->next=tmp->next;
			}else{
				*elements=(*elements)->next;
			}
			
			
			tmp->next = elements2;
			elements2 = tmp;//mpainei stin alli lista
			(*el)++;
			
			deleted=1;
			
		}
		
		if(!deleted)
			prev=tmp;
		tmp=tmp2;
	}
	
	return elements2;
	
}


void print_universe(BarnesHutBody *R, char space, int level){
	
	if(R){
		print_tab( level);
		if(R->type==0){ //internal node
			printf("%c [%s] INTERNAL (%d)", space, R->S, R->cnt);
			std::cout<<"("<<R->X<<","<<R->Y<<") ("<<R->M<<") (L: "<<R->length<<")"<<'\n';
		}else{  //leaf
			printf("%c LEAF (%d)", space, R->cnt);
			std::cout<<"("<<R->X<<","<<R->Y<<") ("<<R->M<<")";
			if(R->cnt==1){
				std::cout<<R->S;
			}
			
			std::cout<<'\n';
		}

		print_universe(R->child1,'A', level+1);
		print_universe(R->child2,'B', level+1);
		print_universe(R->child3,'C', level+1);
		print_universe(R->child4,'D', level+1);
		
	}
	
}


void print_output(BarnesHutBody *R){

        if(R){
			if(R->type==1 && R->cnt==1){
				outputfile<<std::scientific;
				outputfile<<R->X<<" "<<R->Y<<" "<<R->Vx<<" "<<R->Vy<<" "<<R->M<<" "<<R->S<<std::endl;
			}

			print_output(R->child1);
			print_output(R->child2);
			print_output(R->child3);
			print_output(R->child4);
		}
}


int checkSubSpace(BarnesHutBody *space, BarnesHutBody *element){
	
	if(element==NULL)return 0;
	if(space==NULL)return 0;

	while(element->parent!=NULL){
		if(element->parent==space)return 1;
		element=element->parent;
	}

	return 0;
}

BarnesHutBody* elements = NULL;


void netForce(BarnesHutBody *R, BarnesHutBody **elem){
	
	if(R){
		
		BarnesHutBody *children[4];
		children[0] = R->child1;
		children[1] = R->child2;
		children[2] = R->child3;
		children[3] = R->child4;
		
		
		int i;
		for(i=0;i<4;i++){
			if(children[i]){
				if(children[i]->type == 0){//internal node
					//check if elem exists on this universe
					
					if( 1==checkSubSpace(children[i], *elem) ){
						netForce(children[i],elem);
					}else{//calculate F
					
						(*elem)->F +=	(G * ((*elem)->M) * children[i]->M)/ ( pow((*elem)->X-children[i]->X,2)-pow((*elem)->Y-children[i]->Y,2) );
							//r=sqrt((x1-x2)^2-(y1-y2)^2) r^2=((x1-x2)^2-(y1-y2)^2)
					}
					
				}else if(children[i]!= (*elem) && children[i]->cnt==1){
					(*elem)->F +=	(G * ((*elem)->M) * children[i]->M)/ ( pow((*elem)->X-children[i]->X,2)-pow((*elem)->Y-children[i]->Y,2) );
				}
			}
		}			
	}	
}
	
	
void foreachElement(BarnesHutBody *R){
	if(R){
		if(R->type==1 && R->cnt==1){
			printf("LEAF (%d)", R->cnt);
			std::cout<<"("<<R->X<<","<<R->Y<<") ("<<R->M<<")";
			std::cout<<R->S;
			
			std::cout<<'\n';
			
			netForce(ROOT, &R);
						
			R->ax = R->Fx/R->M;
			R->ay = R->Fy/R->M;
			
			R->Vx = R->Vx + R->ax;
			R->Vy = R->Vy + R->ay;

		
			R->X = R->X + R->Vx;
			R->Y = R->Y + R->Vy;
			
			R->next=NULL;
			elements=insert(elements,R);//add again in the list to be processed 
		}
		foreachElement(R->child1);
		foreachElement(R->child2);
		foreachElement(R->child3);
		foreachElement(R->child4);
	}
}


int main(int argc, char* argv[])
{
clock_t begin;

//Start counting time
begin = clock();
double execution_time;


std::ifstream inputfile; //input file
repetitions=atoi(argv[1]);
inputfile.open(argv[2]);
outputfile.open(argv[3]);
std::cout<<repetitions<<std::endl;

std::cout<<std::scientific;
inputfile>>N;
inputfile>>R;
std::cout<<N<<"\n"<<R<<'\n';
outputfile<<N<<std::endl;
outputfile<<R<<std::endl;

elements=NULL;


while(!inputfile.eof())
{
		inputfile>>X;
		inputfile>>Y;
		inputfile>>Vx;
		inputfile>>Vy;
		inputfile>>M;
		inputfile>>S;    

		if(inputfile.eof())break;
		
		BarnesHutBody* element = new BarnesHutBody(X,Y,Vx,Vy,M,strdup(S.c_str()),1);
		//elements[index++] = element;
		
		elements=insert(elements,element);
		
		std::cout<<X<<"\t"<<Y<<"\t"<<Vx<<"\t"<<Vy<<"\t"<<M<<"\t"<<S<<'\n';
}

inputfile.close();

ROOT = create_tree(ROOT, -R, R, -R, R, elements, N,0, NULL, strdup("UNIV"));


print_universe(ROOT, 'O', 0);

printf("\n############################################\n");

int i;
for(i=0;i<repetitions;i++){
	
	elements=NULL;
	foreachElement(ROOT);//compute new positions
	ROOT=NULL;
	ROOT = create_tree(ROOT, -R, R, -R, R, elements, N,0, NULL, strdup("UNIV"));
	print_universe(ROOT, 'O', 0);
	printf("\n############################################\n");
}
        print_output(ROOT);
        outputfile.close();
	
 execution_time=(double)(clock() - begin) / CLOCKS_PER_SEC;
 printf("The program has been running for %lf seconds, with repetitions=%d, testfile=%s.\n\n", execution_time, repetitions, argv[2]);
    
}

