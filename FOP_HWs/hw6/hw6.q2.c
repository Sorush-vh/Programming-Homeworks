#include <stdio.h>
long long int calcA(int i);
// long long int calcB(int i);


// long long int calcA(int i){
// if(i==0) return 1ll;
// else return(3*calcA(i-1)+2*calcB(i-1));
// }


// long long int calcB(int i){
// if (i==0) return 2ll;
// else return(calcA(i-1)+ 2*calcB(i-1));
// }

long long int calcA(int i){
if (i==0) return 1;
if (i==1) return 7;
else return( 5*calcA(i-1)-4*calcA(i-2));

}

int main(){
int n;
scanf("%d",&n);
long long int ans=calcA(n);
printf("%lld",ans);

    return 0;
}