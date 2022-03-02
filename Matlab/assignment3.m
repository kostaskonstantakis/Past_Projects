%Marios Konstantinos Konstantakis, AM: 3219
%csd3219@csd.uoc.gr

%Read all the images from the folder.
i1=imread('bsds86016.png'); %erotima 1-Eksomalunsh kai duadikopoihsh
i2=imread('facade.png'); %erotima 2-Topikh tupikh apoklish kai duadikopoihsh
i3=imread('plate_usa.png'); %erotima 3-Laplasian kai duadikopoihsh

bin1=imbinarize(i1); %dyadikopoihsh xwris eksomalynsh
figure;
hold on;
title('Εικόνα 1       VS        Δυαδική χωρίς Γκαουσιανό Φίλτρο');
imshowpair(i1,bin1,'montage');
hold off;

%Twra kanw eksomalynsh
%image filtered with sigma=3, then binarized
imf3 = imgaussfilt(i1,3);
bin3=imbinarize(imf3);
%image filtered with sigma=5, then binarized
imf5 =imgaussfilt(i1,5);
bin5=imbinarize(imf5);
%image filtered with sigma=7, then binarized
imf7 = imgaussfilt(i1,7);
bin7=imbinarize(imf7);

%subplot them
figure;
hold on;
subplot(2,2,1);
imshow(bin3);
title('Filtered sigma=3');
subplot(2,2,2);
imshow(bin5);
title('Filtered sigma=5');
subplot(2,2,3);
imshow(bin7);
title('Filtered sigma=7');
hold off;

%filtered with different paddings, then binarized
imp=imgaussfilt(i1,'Padding','replicate');
binpr=imbinarize(imp);
imp2=imgaussfilt(i1,'Padding','circular');
binpc=imbinarize(imp2);
imp3=imgaussfilt(i1,'Padding','symmetric');
binps=imbinarize(imp3);

figure;
hold on;
subplot(2,2,1);
imshow(binpr);
title('Padding replicate');
subplot(2,2,2);
imshow(binpc);
title('Padding circular');
subplot(2,2,3);
imshow(binps);
title('Padding symmetric');
hold off;

%Fainetai oti me to fltering xanetai h perissoterh plhroforia. Oso megalytero sigma, toso xanetai perissoterh plhroforia
%Me to padding xanetai ligoteri plhroforia.

%2o erotima
filtro=fspecial('average', 5);
i2oerotima=imfilter(i2, filtro, 'conv');
bin2oerotima=imbinarize(i2oerotima);
figure;
hold on;
subplot(2,2,1);
imshow(i2);
title('Εικόνα 2');
subplot(2,2,2);
imshow(i2oerotima);
title('Τοπική τυπική απόκλιση');
subplot(2,2,3);
imshow(bin2oerotima);
title('Δυαδικοποιημένη');
hold off;

%Den vlepw treli diafora anamesa se arxiki kai filtrarismeni me 
%topiki typiki apoklisi, sxedon idies.

%3o erotima
%sigma=1, sqrt(2), 2, 2*sqrt(2)
sigmas=[1 sqrt(2) 2 2*sqrt(2)];
for i=1:numel(sigmas)
    sigma2=sigmas(i);
    sigma1=1.28*sigma2;
    G1 = imgaussfilt(i3,sigma1);
    G2 = imgaussfilt(i3,sigma2);
    G=G1-G2;
    finalImage3=imbinarize(G);
    figure;
    hold on;
    subplot(2,2,1);
    imshow(i3);
    title('Εικόνα 3');
    subplot(2,2,2);
    imshow(G1);
    title('G1 με σ=σ1');
    subplot(2,2,3);
    imshow(G2);
    title('G2 με σ=σ2');
    subplot(2,2,4);
    imshow(finalImage3);
    title('Δυαδικοποιημένη G1-G2');
    hold off;
end

%Pali, deixnei oti me to fltering xanetai h perissoterh plhroforia. Oso megalytero sigma, 
%toso xanetai perissoterh plhroforia. Ginetai pio tholi i eikona, kai to
%teliko binarization