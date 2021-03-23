%Add a scale parameter to the chi-square distribution for adapting to the scale of data and fit it. First, generate sample data of size 1000 from a chi-square distribution with degrees of freedom 5, and scale it by the factor of 100.
rng default % For reproducibility
x = 100*chi2rnd(5,1000,1);
%Estimate the degrees of freedom and the scaling factor. To do this, custom define the chi-square probability density function using the pdf input argument. The density function requires a 1/s factor for data scaled by s.
[phat,pci] = mle(x,'pdf',@(x,v,s)chi2pdf(x/s,v)/s,'start',[1,200])
%{
The estimate for the degrees of freedom is 5.1079 and the scale is 99.1681. 
The 95% confidence interval for the degrees of freedom is (4.6862,5.5279) and the scale parameter is (90.1215,108.2146). 
The confidence intervals include the true parameter values of 5 and 100, respectively.
%}