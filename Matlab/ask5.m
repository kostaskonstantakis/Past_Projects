%Marios Konstantinos Konstantakis, A.M: 3219. csd3219@csd.uoc.gr

%discrete Fourier transform assignment
%diakritos fourier 

%read images first
i1=imread('einstein.png'); 
i2=imread('obama.png'); 
i3=imread('barbara.png'); 

%creating matrices for some values I'ma need
a=[0.45 0.49 0.495];
K_f=[5 9 17 33 65];
p=[2.5 5 7.5];

%applying the discrete Fourier transform
dF1=fft2(i1);
dF2=fft2(i2);
dF3=fft2(i3);

cdF1=fftshift(dF1); % Center FFT
cdF2=fftshift(dF2); % Center FFT
cdF3=fftshift(dF3); % Center FFT

adF1=abs(dF1); %To metro
adF2=abs(dF2); %To metro
adF3=abs(dF3); %To metro

angdF1=angle(dF1); %H gwnia
angdF2=angle(dF2);
angdF3=angle(dF3);

%gia imagesc
x = [-1/2 1/2];
y = [1/2 1/2];

    figure;
    hold on;
    subplot(2,2,1);
    imshow(i1);
    title('Einstein');
    subplot(2,2,2);
    imshow(i2);
    title('Obama');
    subplot(2,2,3);
    imshow(i3);
    title('Barbara');


figure;
hold on;
title("log(|Shifted Centralized Einstein Image|) ");
imagesc(x, y, log(adF1));
hold off;

figure;
hold on;
title("angle(Shifted Centralized Einstein Image) ");
imagesc(x, y, angdF1);
hold off;

figure;
hold on;
title("log(|Shifted Centralized Obama Image|) ");
imagesc(x, y, log(adF2));
hold off;

figure;
hold on;
title("angle(Shifted Centralized Obama Image) ");
imagesc(x, y, angdF2);
hold off;

figure;
hold on;
title("log(|Shifted Centralized Barbara Image|) ");
imagesc(x, y, log(adF3));
hold off;

figure;
hold on;
title("angle(Shifted Centralized Barbara Image) ");
imagesc(x, y, angdF3);
hold off;

%calculate the dimensions of each image
[M1, N1, numberOfColorChannels1] = size(i1);
[M2, N2, numberOfColorChannels2] = size(i2);
[M3, N3, numberOfColorChannels3] = size(i3);

u1=0:0.99;
v1=0:0.99;
m1=0:M1-1;
n1=0:N1-1;

u2=0:0.99;
v2=0:0.99;
m2=0:M2-1;
n2=0:N2-1;

u3=0:0.99;
v3=0:0.99;
m3=0:M3-1;
n3=0:N3-1;


%ektos an dwsw kai to a ws param...3 params dld
function Auv=A(u,v)
    for i=1:numel(a)
        Auv=(1/((1-a(i)*(cos(2*pi*u)+cos(2*pi*v)))));
    end
end
