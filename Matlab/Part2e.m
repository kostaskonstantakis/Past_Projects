%I used the same capture file from Part 2.
%Each matrix represents the size of the header, the data segment, and the
%total size of each packet in bytes, respectively.
header=[62; 62; 20; 20; 62; 20; 20; 62; 62; 62; 20; 20; 62; 62; 20; 20; 62; 20; 62; 62; 62; 62; 20; 20; 20; 20; 62; 20; 62; 20; 62; 20; 62; 20; 20;]; 
data=[814; 29; 34;  40; 35; 54; 1400; 1330; 1330; 33; 1408;  2816; 124; 375; 2816; 4224; 1330; 54; 33; 1325; 1330; 716; 869; 1440; 1408; 1330; 2816; 38; 1408; 33; 1408; 2816; 33; 1408;  2816;]; 
packet=[876; 91; 54; 60; 97; 74; 1474; 1392; 1392; 95; 1462; 2870; 186; 437; 2870; 4278; 1392; 74; 95; 1387; 1392; 778; 923; 1494; 1462; 1392; 2870; 100; 1462; 95; 1462; 2870; 95; 1462; 2870;];
var_h=cdf('Normal',header);
var_d=cdf('Normal',data);
var_p=cdf('Normal',packet);
[y1, x1]= ecdf(var_h); 
[y2, x2]= ecdf(var_d); 
[y3, x3]= ecdf(var_p); 
plot(x1, y1)
title("ECDF for header size, data segment & packet size");
ylabel('ECDF'); 
xlabel('variable size');
hold on
plot(x2,y2)
hold on
plot(x3,y3)
legend('header','data','packet','Location','best') 
hold off

%percentiles for each vector.
h_75 = prctile(header,75)
h_25 = prctile(header,25)
h_m = prctile(header,median(header))

d_75 = prctile(data,75)
d_25 = prctile(data,25)
d_m = prctile(data,median(data))

p_75 = prctile(packet,75)
p_25 = prctile(packet,25)
p_m = prctile(packet,median(packet))

