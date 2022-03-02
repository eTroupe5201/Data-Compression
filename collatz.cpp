#include <iostream>

int sequence(long n)
int count = 1;
while(n != 1){
if(n % 2 == 0){
n /= 2;
}else{
n = (3 * n) +1;}
++count;
}
return count;}

int main(){
int max = 0, maxi;
for(int i = 999999, i > 0; --i){
int s = sequence(i);
if(s > max){
max = s;
maxi = i;
}
std::cout << maxi << std::endl;
}
