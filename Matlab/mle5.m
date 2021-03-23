data = binornd(20,0.75,100,1);
%Estimate the probability of success and 95% confidence limits using the simulated sample data.
[phat,pci] = mle(data,'distribution','binomial','alpha',.05,'ntrials',20)
