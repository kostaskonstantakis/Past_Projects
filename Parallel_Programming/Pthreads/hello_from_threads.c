#include<stdio.h>
#include<pthread.h>

void *do_something(void *p) {
printf("Hello from %s thread\n", (char*)p);
return NULL;
}
int main() {
pthread_t tid;
char *msg1 = "parent", *msg2 = "child";
pthread_create(&tid, NULL, do_something, msg2);
do_something(msg1);
pthread_join(tid, 0);
return 0;
}
