x=[1;2;3;4;5;];
bins=[1;0;3;6;4;];

function z = Histogram(x, bins)
% input parameters
% X =[x1; x2; … xn]: a column vector containing the data x1, x2, …, xn.
% bins = [b1; b2; …bk]: A vector that Divides the sampling space in bins
% centered around the points b1, b2, …, bk.
figure; % Create a new figure
[f y] = hist(x, bins); % Assign your data points to the corresponding bins
bar(y, f/trapz(y,f), 1); % Plot the histogram
xlabel('x'); % Name axis x
ylabel('p(x)'); % Name axis y
%z=[f y];
end