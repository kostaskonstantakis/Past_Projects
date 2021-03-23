load('readmissiontimes.mat');
custnloglf = @(lambda,data,cens,freq) - length(data)*log(lambda) + nansum(lambda*data);
phat = mle(ReadmissionTime,'nloglf',custnloglf,'start',0.05)