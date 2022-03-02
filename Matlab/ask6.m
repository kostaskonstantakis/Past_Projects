%Marios Konstantinos Konstantakis, A.M: 3219. csd3219@csd.uoc.gr
%read image
i=imread('obama.png');

%create some matrices I need 
z=[1 2 3 4];

visibility = [ 16 11 10 16 24 40 51 61;
     12 12 14 19 26 58 60 55;
     14 13 16 24 40 57 69 56;
     14 17 22 29 51 87 80 62;
     18 22 37 56 68 109 103 77;
     24 35 55 64 81 104 113 92;
     49 64 78 87 103 121 120 101;
     72 92 95 98 112 100 103 99];
 
 %custom function gia ton metasximatismo synimitonou
 fun=@(block_struct) dct2(i, [8 8]); 
 y=blockproc(i,[8 8], fun); 
 
 %calculate the dimensions of the cosine transformed image
 [M, N, numberOfColorChannels] = size(y);

 %quantizer arrays-kvantistes
 Q1=z(1)*visibility;
 Q2=z(2)*visibility;
 Q3=z(3)*visibility;
 Q4=z(4)*visibility;
 
 %y' is the quantized blocks of the initial image
 y_tonos1=[];
 y_tonos2=[];
 y_tonos3=[];
 y_tonos4=[];
     for a=1:numel(Q1)
         for k=1:M
            %for k=1:N %dunno if these are actually useful
                y_tonos1(k)=Q1(a)*round(y(k)/Q1(a));
             %end; %same as above.
          end;
    end;
    
    for b=1:numel(Q2)
         for k=1:M
                y_tonos2(k)=Q2(b)*round(y(k)/Q2(b));
          end;
    end;
 
    for c=1:numel(Q3)
         for k=1:N
                y_tonos3(k)=Q3(c)*round(y(k)/Q3(c));
          end;
    end;
    
    for d=1:numel(Q4)
         for k=1:N
                y_tonos4(k)=Q4(d)*round(y(k)/Q4(d));
          end;
    end;
%custom function for the inverse cosine transform of y'
 fun1=@(block_struct) idct2(y_tonos1, [8 8]); 
 ytonos1=blockproc(y_tonos1,[8 8], fun1); 
 
 fun2=@(block_struct) idct2(y_tonos2, [8 8]); 
 ytonos2=blockproc(y_tonos2,[8 8], fun2); 
 
 fun3=@(block_struct) idct2(y_tonos3, [8 8]); 
 ytonos3=blockproc(y_tonos3,[8 8], fun3); 
 
 fun4=@(block_struct) idct2(y_tonos4, [8 8]); 
 ytonos4=blockproc(y_tonos4,[8 8], fun4); 
 
%gia imagesc
x = [-8 8];
y = [-8 8];

%1o & 3o, 2o & 4o fainontai to idio...
figure;
hold on;
subplot(2,2,1);
imagesc(x, y, y_tonos1);
title("cosine transform of y1'");
subplot(2,2,2);
imagesc(x, y, y_tonos2);
title("cosine transform of y2'");
subplot(2,2,3);
imagesc(x, y, y_tonos3);
title("cosine transform of y3'");
subplot(2,2,4);
imagesc(x, y, y_tonos4);
title("cosine transform of y4'");
hold off;

%parathrw oti den exoun diafora metaksy tous, optika.
figure;
hold on;
subplot(2,2,1);
imagesc(x, y, ytonos1);
title("Inverse cosine transform of y1'");
subplot(2,2,2);
imagesc(x, y, ytonos2);
title("Inverse cosine transform of y2'");
subplot(2,2,3);
imagesc(x, y, ytonos3);
title("Inverse cosine transform of y3'");
subplot(2,2,4);
imagesc(x, y, ytonos4);
title("Inverse cosine transform of y4'");
hold off;