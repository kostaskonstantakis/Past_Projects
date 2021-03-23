syms x y %y=transpose(x)
g2 = y*[-7/54 1/27; -1/27 -5/108;]*x+[6 2;]*x-168.6678;
g1 = y*[-1/9 1/18; 1/18 -1/9;]*x+[13.6 -6.8; -4.2 8.4;]*x-324.7868;
g3 = y*[-13/174 2/174; 2/174 -7/174;]*x+[7.5862 1.4483;]*x-242.7691;
g4 = y*[-8/126 5/126; 5/126 -11/126;]*x+[-0.4603 7.4127;]*x +98.7980;
solution12 = solve(g1==g2)
solution13 = solve(g1==g3)
solution14 = solve(g1==g4)
solution23=solve(g2==g3)
solution24=solve(g2==g4)
solution34=solve(g3==g4)