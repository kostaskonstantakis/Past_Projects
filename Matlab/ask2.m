%Marios Konstantinos Konstantakis, AM: 3219
%csd3219@csd.uoc.gr

%load all the variables from the given files first.
load('p_e.mat');
load('p_G.mat');
load('p_R.mat');

%Then, read all the images from the folder.
images = dir('*.png'); 
plithos = length(images);    % Number of images on the folder

%Kanw prwta to teleytaio erotima, to 9...
for i=1:plithos
   currentfilename = images(i).name;
   currentimage = imread(currentfilename);
   binaryimage=imbinarize(currentimage, 'global');
   %'global' invokes the Otsu method, dinei ena orio/katofli (threshold) poy xwrizei ta pixels se 2 klaseis/kathgories-aspro & mayro. 
   figure;
   hold on;
   title('Original               Binary');
   imshowpair(currentimage, binaryimage, 'montage');  %show the original and the binary image, side by side.
   hold off;
   figure;
   hold on;
   title('imhist of image');
   imhist(currentimage); %creates a histogram from the grayscale image's pixels. 
   hold off;
end

%Oso mikrotero to threshold, toso asproteri eikona. Antistoixa, oso megalytero threshold, toso skoteinoteri eikona
%To 'adaptive' filtro teinei na deixnei tin deuteri eikona pio "pista" stin prwti eikona

%1. Gauss : p1(l) =p_0*p_e(l) + (1-p_0)*p_G(l) 0<=l<=255
%2. Rice : p2(l) = p_0*p_e(l) + (1-p_0)*p_R(l) 0<=l<=255

e=imhist(p_e) %vrisko tin empiriki pyknotita pithanotitas kai athroizwntas vrisko tin katanomi pithanotitas
g=imhist(p_G) %Gaussian
r=imhist(p_R) %Rice

kat_e=cumsum(e) %to swreytiko ahtroisma twn stoixeiwn tou e, pou einai empiriki pyknotita pithanotitas
kat_g=cumsum(g) %Gaussian
kat_r=cumsum(r) %Rice

%exw tis katanomes pithanotitas twra.
