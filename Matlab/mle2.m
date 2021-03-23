rng default % for reproducibility
x = ncx2rnd(8,3,1000,1);
%Estimate the parameters of the noncentral chi-square distribution from the sample data. 
%To do this, custom define the noncentral chi-square pdf using the pdf input argument.
[phat,pci] = mle(x,'pdf',@(x,v,d)ncx2pdf(x,v,d),'start',[1,1]);
