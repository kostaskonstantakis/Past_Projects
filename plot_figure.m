Nmax=100;

function plot_figure(Nmax)
% plots the function e(N) for N=1,2,..., Nmax
x = 1:1:Nmax;
%setting the derivative of f(p) = N*p*[(1-p)^(N-1)] equal to 0
%we get that a local maximum of f(p) in the interval [0 1]
%lies at p* = 1/N. According to the above result we get:
%e(N) = (N/N)*((N-1)/N)^(N-1) = ((N-1)/N)^(N-1).
figure;
set(gcf,'color',[1 1 1]);
plot(x, ((x-1)./x).^(x-1), '*k');
ylabel('e(N)');
xlabel('N');
end
