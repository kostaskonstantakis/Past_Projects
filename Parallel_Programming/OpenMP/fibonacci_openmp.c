#include <stdio.h>
#include <stdlib.h>
#include <omp.h>


/*int fib(int n) {
int x, y;
if(n < 2) return n;
#pragma omp task
x = fib(n-1);
#pragma omp task
y = fib(n-2);
#pragma omp taskwait
return (x + y);
}
//exw thema me private task variables

//an ta kanw shared, ola komple~
*/

int fibonacci(int n) {
int x, y;
if(n < 2) return n;
#pragma omp task shared(x)
x = fibonacci(n-1);
#pragma omp task shared(y)
y = fibonacci(n-2);
#pragma omp taskwait
return x + y;
}

void main(int argc, char *argv[] ) {
int n=atoi(argv[1]);
int res=fibonacci(n);
printf("Fibonacci of %d=%d\n", n, res);

}
