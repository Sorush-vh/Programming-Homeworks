#include <stdio.h>

void superfunc(int a, int b, int j, int n)
{
    if (j < n)
    {
        long long int ans = 0;
        int replace;
        // kamtare shomarande bashe
        if(b<0) replace=-b;
        else replace=b;
        for (int i = 0; i < replace; i++)
        {
            ans += a;
        }
        if(b<0) ans=-ans;
        a = b;
        if(j<n-1) scanf(" %d", &b);
        j++;
        printf("%lld ", ans);
        superfunc(a,b,j,n);
    }
}

int main()
{
    int a, b,j=0, n;
    scanf("%d\n", &n);
    scanf("%d %d", &a, &b);
    superfunc(a,b,j,n);
    return 0;
}