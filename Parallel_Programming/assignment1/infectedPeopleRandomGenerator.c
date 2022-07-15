#include <stdio.h>
#include <stdlib.h>


int main() 
{

//p2p-Gnutella24.txt has 26518 nodes. The random number generated was 4664.
//Email-Enron.txt has 36692 nodes. The random number generated was 33668, which is way too big!!
//facebook_combined doesn't specify, so I'm going with the minimum number generated!
//So, the chosen one is 4664! Now, I'm gonna generate the 4664 random nodes that are infected!
//Insert them in an array, and then put them in a test file!
int i, n;
FILE* f;

  f = fopen("infectedNodes.txt", "w");
  if(f == NULL)
      return 1;

for (i = 1; i <= 4664; i++) 
{
n = rand() %26518 + 1;
fprintf(f, "%d\n", n);
}
fclose(f);
return 0;
}
