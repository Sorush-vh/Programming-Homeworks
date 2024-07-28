#include <stdio.h>
long long mod=1e9+7;
long long int inline_possibilities(int a)
{
    if (a > 2)
    {
        a--;
        return ((1 + a * inline_possibilities(a))%mod) ;
    }
    else
        return 1ll*a;
}

long long int line_recieve_total_possibilities(int a){
    return (a*inline_possibilities(a)%mod);
}


long long int deliver(int i,int n)
{
int a;
scanf("\n%d",&a);
if(i==n-1) return line_recieve_total_possibilities(a)%mod;
else return  line_recieve_total_possibilities(a) %mod +(1+line_recieve_total_possibilities(a)) % (long long) mod * (deliver(i+1,n) % (long long) mod);
}




int main()
{
    int n;
    long long int ans;
    scanf("%d", &n);
    ans=(1+deliver(0,n));
    ans=ans% (long long) mod;
    printf("%lld",ans);
// printf("%d",inline_possibilities(15));


    return 0;
}