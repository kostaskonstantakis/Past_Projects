%tracert hops for each URL
%* hops are represented by 0ms here.
gr_hops=[11; 12; 10; 7; 8; 10;]; %sony.gr
de_hops=[54; 10; 43; 9; 7; 12;]; %sony.de
jp_hops=[7; 28; 28; 35; 60; 55; 74; 55; 56; 55; 56; 58; 56; 67; 69; 69; 79; 61; 61; 78; 81; 78;]; %sony.jp
%ecdf() can't have a matrix as an argument, so...
x_gr=numel(gr_hops) %counting the elements of each vector, ie the number of hops for every URL.
x_de=numel(de_hops) %same as above
x_jp=numel(jp_hops) %same here, too!
y_gr=cdf('Normal',x_gr) %What kind of distribution should the parameter be?! Dunno, so...
y_de=cdf('Normal',x_de)
y_jp=cdf('Normal',x_jp)
[y_gr, x_gr] = ecdf(gr_hops)
[y_de, x_de] = ecdf(de_hops)
[y_jp, x_jp] = ecdf(jp_hops)
plot(x_gr,y_gr)
title('ECDF values for hops data.')
xlabel('hops')
ylabel('F(x)')
hold on
plot(x_de,y_de)
hold on
plot(x_jp,y_jp)
legend('sony.gr','sony.de','sony.jp','Location','best') 
hold off