#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>

int A[10]={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
int B[10]={68, 32, -127, 8, 0, 11, 155, 3000, 6, 2};
int C[10]={9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

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

void main() {
merge_sort(A, 10);
merge_sort(B, 10);
merge_sort(C, 10);
for(int z=0;z<10;z++) printf("%d\n", A[z]);
printf("\n---Now B---\n");
for(int q=0;q<10;q++) printf("%d\n", B[q]);
printf("\n---Now C---\n");
for(int w=0;w<10;w++) printf("%d\n", C[w]);
}
