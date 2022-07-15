#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#define THRESHOLD 1000

//int A[4]={9, 6, 3, 0};

int A[10]={5, 10, -5, -100, 666, 0, 747, 13, -118, 541666};
struct thread_arg {
int *a;
int length;
};

void merge(int A[], int length, int middle) {
int i, j , k;
int *x = malloc(length *sizeof (int));
for (i = 0, j = middle, k = 0; k < length; k++) {
x[k] = j == length ? A[i++]
: i == middle ? A[j++]
: A[j] < A[i] ? A[j++]
: A[i++];
}
for (i = 0; i < length; i++) {
A[i] = x[i];
}
free(x);
}

void merge_sort(int A[], int length) {
int middle;
if (length < 2) return;
middle = length / 2;
merge_sort(A, middle);
merge_sort(A+middle, length -middle);
merge(A, length, middle);
}

void *par_merge_sort(void *voidarg) {
pthread_t child;
struct thread_arg *arg = malloc(sizeof(struct thread_arg)); //(struct thread_arg)malloc (sizeof(voidarg)); 
// (struct thread_arg *) voidarg;
struct thread_arg childarg;
int middle;
if (arg->length < THRESHOLD) {
merge_sort(arg->a, arg->length);
return NULL;
}
middle = arg->length / 2;
childarg.a = arg->a;
childarg.length = middle;
pthread_create(&child, NULL, &par_merge_sort, &childarg);
//merge_sort(arg->a + middle, arg->length - middle);
par_merge_sort(arg->a + middle); //runs, but prints the array in its initial state
pthread_join(child, NULL);
merge(A, sizeof(A), middle); //length ennoei A.length, arg.length or a.length???!
}


void main() {
void *p;
par_merge_sort(p);

for(int j=0;j<10;j++) printf("%d\n", A[j]);
}
