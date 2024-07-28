#include <stdio.h>


 int matris1[100][100];
 int matris2[100][100];
 int ans[100][100];

void zarbmatrisa(int i1, int km, int j2){
 long long int sum=0;
for (int i = 0; i < i1; i++)
{
    for (int j = 0; j < j2; j++)
    {
        for (int k = 0; k < km; k++)
        {
            sum+=(matris1[i][k]*(1ll)%998244353*(1ll))*(matris2[k][j]*(1ll)%998244353*(1ll))*(1ll);
        }
        ans[i][j]=sum%998244353*(1ll);
        sum=0;
    }
}
}

void getmatris(int n,int m){
        for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
           scanf("%d",&matris2[i][j]);
           getchar();
        }
    }
}

void makemosavi(int n, int m, int arr1[100][100], int arr2[100][100]){
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            arr1[i][j]=arr2[i][j];
        }
        
    }
}


int main(){
int t,n,m;
scanf("%d\n",&t);
int s;
for (int z = 0; z < t; z++)
{
    scanf("%d %d\n",&n,&m);
    getmatris(n,m);
    if(z) 
    {
        zarbmatrisa(s,n,m);
    }
    else {
    s=n;
    makemosavi(n,m,matris1,matris2);
    }
    if(z) makemosavi(s,m,matris1,ans);
}

for (int ii = 0; ii < s; ii++)
{
    for (int jj = 0; jj < m; jj++)
    {
        printf("%d ",matris1[ii][jj]);
    }
    printf("\n");
}


    return 0;
}