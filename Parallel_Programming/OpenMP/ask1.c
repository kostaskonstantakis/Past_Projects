#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

float big_job(int i){
printf("i=%d\n", i);
float f=(float)i;
return f;
}


void consume(float B, float res){
B++;
res+=B;
}

void main() {
float res, B;
float tmp;
int niters=0;
float X;
/*#pragma omp parallel
{
//float B;
int i, id, nthrds;
id = omp_get_thread_num();
nthrds = omp_get_num_threads();
for(i=id;i<niters;i=i+nthrds){ //i<nthrds;i++
B = big_job(i);
#pragma omp critical
consume (B, res);
}
printf("B=%d\n", B);
printf("res=%d\n", res);
}*/

#pragma omp parallel
{
B = 2;
tmp = big_job(B);
#pragma omp atomic
X += tmp; 
}

printf("B=%d\n", B);
printf("X=%d\n", X);

//printf("B=%d\n", B); //prints zero
//printf("res=%d\n", res); //the same here
}
