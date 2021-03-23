load('readmissiontimes.mat');
custpdf = @(data,lambda) lambda*exp(-lambda*data);
custcdf = @(data,lambda) 1-exp(-lambda*data);
phat = mle(ReadmissionTime,'pdf',custpdf,'cdf',custcdf,'start',0.05,'Censoring',Censored);
disp(phat);

custlogpdf = @(data,lambda,k) log(k)-k*log(lambda)+(k-1)*log(data)-(data/lambda).^k;
custlogsf = @(data,lambda,k) -(data/lambda).^k;
phat = mle(ReadmissionTime,'logpdf',custlogpdf,'logsf',custlogsf,...
    'start',[1,0.75],'Censoring',Censored)