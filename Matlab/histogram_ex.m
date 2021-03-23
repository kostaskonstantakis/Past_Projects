x=[-4 -2 0 2 4];
bins=[3 5 6 2];
%produce_histogram(x,bins)
ecdf(x)
xcorr(x, bins)