%ping data
gr_rtts=[7; 7; 8; 9; 7; 10; 12; 7; 8; 7; 8; 7; 10; 8; 7; 6; 8; 9; 7; 7; 10; 7; 6; 8; 15; 7; 6; 7; 7; 7;]; %sony.gr
de_rtts=[7; 11; 8;  7; 10; 7; 7; 7; 10; 7; 8; 9; 7; 7; 14; 14; 6; 6; 7; 6; 7; 8; 7; 9; 7; 7; 8; 7; 7; 7;]; %sony.de
jp_rtts=[80; 78; 79; 78; 80; 79; 81; 82; 80; 78; 78; 78; 78; 81; 80; 79; 79; 79; 78; 78; 78; 80; 80; 78; 79; 79; 78; 80; 80; 79;]; %sony.jp
%tracert data
gr_hops=[11; 12; 10; 7; 8; 10;]; %sony.gr
de_hops=[54; 10; 43; 9; 7; 12;]; %sony.de
jp_hops=[7; 28; 28; 35; 60; 55; 74; 55; 56; 55; 56; 58; 56; 67; 69; 69; 79; 61; 61; 78; 81; 78;]; %sony.jp
%I'm choosing 18 elements of the larger matrices, because the hop matrices
%for sony.de & sony.gr have 18 elems each
size1 = numel(gr_rtts);
rtt1=gr_rtts(randperm(size1,18));
size2 = numel(de_rtts);
rtt2=de_rtts(randperm(size2,18));
size3= numel(jp_rtts);
rtt3=jp_rtts(randperm(size3,18));
size4= numel(jp_hops);
jp_hopsNew=jp_hops(randperm(size3,18));
corr_gr=corr(rtt1,gr_hops) %correlation of RTT and number of hops for sony.gr URL
corr_de=corr(rtt2,de_hops)
corr_jp=corr(rtt3,jp_hopsNew)
plot3(corr_gr,corr_de,corr_jp)
title('Correlations between RTT and the number of hops.')
xlabel('corr-gr')
ylabel('corr-de')
zlabel('corr-jp')
legend('corr-gr','corr-de','corr-jp','Location','best')