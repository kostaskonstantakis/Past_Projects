#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
void *thread_func(void *memory) {
int *number = (int *) memory;
pthread_mutex_lock(&lock);
*number = 42; //htane sketo number=42; pointer se akeraio me akeraio
printf("number=%d.\n", *number);
pthread_mutex_unlock(&lock);
}
void start_threads() {
int my_int = 0;
pthread_t t1, t2;
pthread_create(&t1, NULL, &thread_func, &my_int); //Mporw na valw NULL  //epishs, edw eixe 1 parapanw pedio
pthread_create(&t2, NULL, &thread_func, &my_int);
pthread_join(t1, NULL);
pthread_join(t2, NULL);

}
int main() {
start_threads();
return 0;
}
