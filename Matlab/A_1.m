load fishes %('fishes.mat');
w= fishes(:,1);%fishes.weight, first column of the matrix.
l= fishes(:,2); %fishes.length; 2nd column of the matrix.


%characteristiscs follow a normal distribution 

%These are the respective means and covariance matrices 
m1=[62; 38;];
S1=[6 3; 3 6;];

m2=[38; 52;];
S2=[5 4; 4 14;];

m3=[56; 34;];
S3=[7 2; 2 13;];

m4=[ 32; 57;];
S4=[11 5; 5 8;];

%{
nor_pd1=makedist('Normal','mu',m1,'sigma',S1); %normal probability distribution
nor_pd2=makedist('Normal','mu',m2,'sigma',S2);
nor_pd3=makedist('Normal','mu',m3,'sigma',S3);
nor_pd4=makedist('Normal','mu',m4,'sigma',S4);
%}

pdw = fitdist(w,'Normal')
pdl = fitdist(l,'Normal')
%[pdca,gn,gl] = fitdist(w,'Normal','By',l)
[gn,gl] = fitdist(w,'Normal','By',l) %pdca
%pd = fitdist(w,l,'Normal'); %doesn't compile % Distribution for weight?
dist1=gl{1}%pdca  %When using (), the resulting array is still blank... 
% Distribution for length?
dist2=gl{2}  

octopus=125;
sardines=616;
scorpionfish=43;
tuna=408;
