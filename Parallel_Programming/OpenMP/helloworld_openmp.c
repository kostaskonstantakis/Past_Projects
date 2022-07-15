#include <stdio.h>
#include <omp.h>
int main()
{
#pragma omp parallel
{
int ID = omp_get_thread_num();
printf(" hello(%d) ", ID);
printf(" world(%d) \n", ID);
}
int a[4]={12, 13, 14, 15};
int i=0;
int j=0;
#pragma omp parallel default (none) \
shared (a) private(i,j)
{
#pragma omp for nowait
for (i=0; i<4; i++)
a[i] = (a[i] + a[i+1]) / 2;
#pragma omp for nowait
for (j=0; j<4; j++)
a[j] =a[j]/a[j];  //1.0/a[j];
}
for(int c=0;c<4;c++) printf("%d\n",a[c]);

int sum=0;
int num_threads=0;
int npoints=0;
/*
An OpenMP version of a threaded program to compute PI.
#pragma omp parallel default(private) shared (npoints) \
reduction(+: sum) num_threads(8)
{
num_threads = omp_get_num_threads();
sample_points_per_thread = npoints / num_threads;
sum = 0;
for (i = 0; i < sample_points_per_thread; i++) {
rand_no_x =(double)(rand_r(&seed))/(double)((2<<14)-1);
rand_no_y =(double)(rand_r(&seed))/(double)((2<<14)-1);
if (((rand_no_x -0.5)*(rand_no_x -0.5) +
(rand_no_y -0.5)*(rand_no_y -0.5)) < 0.25)
{
sum++;
}
}
}*/
}
