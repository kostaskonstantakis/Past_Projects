%{
Generate sample data of size 1000 from a noncentral chi-square distribution 
with degrees of freedom 10 and noncentrality parameter 5.
%}
rng default % for reproducibility
x = ncx2rnd(10,5,1000,1);
%{
Suppose the noncentrality parameter is fixed at the value 5. 
Estimate the degrees of freedom of the noncentral chi-square distribution from the sample data. 
To do this, custom define the noncentral chi-square pdf using the pdf input argument.
%}
[phat,pci] = mle(x,'pdf',@(x,v,d)ncx2pdf(x,v,5),'start',1)
%{
estimate for the noncentrality parameter is 9.9307, 
with a 95% confidence interval of 9.5626 and 10.2989.
The confidence interval includes the true parameter value of 10.
%}
