#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#define  NUM_THREADS 1000000


typedef struct thread_arg {
int from;
int to;
pthread_t thread;
}thread_arg;

int C[1000000], A[1000000], B[1000000];
void *do_work(void *voidarg) {
struct thread_arg *arg = (struct thread_arg *)voidarg;
for(int j = arg->from; j < arg->to; j++) {
C[j] = A[j] + B[j];
}
}

void main(){ 
struct thread_arg thread_arg[NUM_THREADS];
int from = 0, to = 1000000;
int step = to / NUM_THREADS;
int i;
for(i = 0; i < NUM_THREADS; i++) {
thread_arg[i].from = from;
thread_arg[i].to = (i < NUM_THREADS-1) ? (from + step) : to;
from = to;
pthread_create(&thread_arg[i].thread, NULL, &do_work, &thread_arg[i]);
pthread_join(thread_arg[i].thread, NULL);
}
//for(i = 0; i < NUM_THREADS; i++) {
//pthread_join(thread_arg[i].thread, NULL);
//}
}


