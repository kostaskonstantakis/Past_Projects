
P_octopus=0.10487;
S1_i=inverse(S1);
W1=-(1/2)*S1_i;
w1=S1_i*m1;

function w_i0=wi0(w1,S1,m1,p)
w_i0=-(1/2)*transpose(m1)*w1-(1/2)*log(S1)+log(p);
end

function g_1=g1(x,W1,w1,wi0(w1,S1,m1,P_octopus))
g_1=transpose(x)*W1*x+transpose(w1)*x+wi0(w1,S1,m1,P_octopus);
end

wi0(w1,S1,m1,P_octopus);

for i = 1:rows(var_fishes)
  for j = 1:columns(var_fishes)
  g1(var_fishes(i,j),W1,w1,wi0(w1,S1,m1,P_octopus));
  end
end
