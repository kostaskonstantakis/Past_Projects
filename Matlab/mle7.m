%{
Generate sample data of size 1000 from a Rician distribution 
with noncentrality parameter of 8 and scale parameter of 5. 
First create the Rician distribution.
%}
r = makedist('Rician','s',8,'sigma',5);
%Now, generate sample data from the distribution you created above.
rng default % For reproducibility
x = random(r,1000,1);
%{
Suppose the scale parameter is known, 
and estimate the noncentrality parameter from sample data. 
To do this using mle, you must custom define the Rician probability density function.
%}
[phat,pci] = mle(x,'pdf',@(x,s,sigma) pdf('rician',x,s,5),'start',10)
%{
The estimate for the noncentrality parameter is 7.8953, with a 95% confidence interval of 7.5404 and 8.2501. 
The confidence interval includes the true parameter value of 8.
%}