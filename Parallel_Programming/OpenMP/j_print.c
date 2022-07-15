#include <stdio.h>
#include <omp.h>
#include <stdlib.h>

int j;
#pragma omp threadprivate(j) //den doulevei xwris ayto
//nea metavliti gia ka8e thread, dunamiki desmeusi, persistent
void main() {
j=1;
#pragma omp parallel copyin(j) //mono an mpoun kai ta 2 se sxolia
//threadprivate initialization
#pragma omp master
//mono to master thread 8a ektelesei ayto
{
j=2; 
}
printf("j=%d.\n", j);  //this will obviously print 2.
}
