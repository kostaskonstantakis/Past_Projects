#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>

int min3(int a, int b, int c)
{
if(a<=b && a<=c) return a;
else if(b<=a && b<=c)return b;
else return c;
}

int edit_distance(char *str1, char *str2) {
int i, j;
int result;
const int length1 = strlen(str1);
const int length2 = strlen(str2);
int dist[length1+1][length2+1];
for(i = 0; i < length1; i++) dist[i][0] = i;
for(j = 0; j < length2; j++) dist[0][j] = j; //i;
for(i = 1; i <= length1; i++) {
for(j = 1; j <= length2; j++) {
dist[i ][ j] = (str1[i - 1] == str2[j -1]) ? dist[i-1][j-1]
: 1 + min3(dist[i-1][j], dist[i][j-1], dist[i-1][j-1]);
}
} result = dist[length1][length2];
return result;
}

void *parallel(void* a, void* b) {
int i=edit_distance((char*)a,(char*)b);
}

void main(int argc, char *argv[]) {
pthread_t tid;
char *a=argv[1];
char *b=argv[2];
int d=edit_distance(a, b);
printf("\nLevenshtein distance between '%s' & '%s' is %d.\n", a, b,d);
}
