%ping rtts for each URL
gr_rtts=[7; 7; 8; 9; 7; 10; 12; 7; 8; 7; 8; 7; 10; 8; 7; 6; 8; 9; 7; 7; 10; 7; 6; 8; 15; 7; 6; 7; 7; 7;]; %sony.gr
de_rtts=[7; 11; 8;  7; 10; 7; 7; 7; 10; 7; 8; 9; 7; 7; 14; 14; 6; 6; 7; 6; 7; 8; 7; 9; 7; 7; 8; 7; 7; 7;]; %sony.de
jp_rtts=[80; 78; 79; 78; 80; 79; 81; 82; 80; 78; 78; 78; 78; 81; 80; 79; 79; 79; 78; 78; 78; 80; 80; 78; 79; 79; 78; 80; 80; 79;]; %sony.jp
%allakse i ekfonisi, opote den thelei pia to average RTT
x_gr=(gr_rtts) %RTT array for sony.gr URL
y_gr=cdf('Normal',x_gr) %What kind of distribution should the parameter be?! Dunno, so...
x_de=(de_rtts)
y_de=cdf('Normal',x_de)
x_jp=(jp_rtts)
y_jp=cdf('Normal',x_jp)
[y_gr, x_gr] = ecdf(gr_rtts)
[y_de, x_de] = ecdf(de_rtts)
[y_jp, x_jp] = ecdf(jp_rtts)
plot(x_gr,y_gr)
title('ECDF values for RTT data.')
xlabel('RTT')
ylabel('F(x)')
hold on
plot(x_de,y_de)
hold on
plot(x_jp,y_jp)
legend('sony.gr','sony.de','sony.jp','Location','best') 
hold off