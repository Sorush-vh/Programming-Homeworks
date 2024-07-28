#include <stdio.h>
int number[10009];
char color[10009];
int check[10009];
int count = 0;
int ans = 0;

// void putInOrder(int i, int j)
// {
//     if (j == -1)
//         ;
//     else
//     {
//         rank[j + 1] = number[i - 2];
//         putInOrder(number[i - 2], j - 1);
//     }
// }

// void giveans(int i, int n)
// {
//     if (i == n-1)
//         ;
//     else
//     {
//         if (color[rank[i]] == 'B')
//             count--;
//         else
//             count++;
//         if (count == 0)
//             ans++;
//         giveans(i + 1, n);
//     }
// }
void setto1(int n){
    for (int i = 2; i < n+1; i++)
    {
        check[i]=1;
    }
    
}

// void superfunc(int i, int n, int colorz){
//     int endcheck=1,newcolorz=0;
//     if(color[i]=='B'){
//          colorz++;
//     newcolorz++;
//     }
//     else {
//         colorz--;
//          newcolorz--;
//     }
//     for (int j = 2; j < n+1 ; j++)
//     {
//         if(number[j]==i) {
//             endcheck=0;
//             superfunc(j,n,colorz);
//             if(check[i]==1 ){
//             superfunc(j,n,newcolorz);
//             check[i]=0;
//             }
//             }
//     }
//     if(endcheck && colorz==0) ans++;
// }

void ansfunc(int i, int n){
if(colorzsum(i,n)==0) ans++;
            for (int j = 2; j < n+1 ; j++)
    {
        if(number[j]==i && check[j]==1) {
            check[j]==0;
            ansfunc(j,n);
            }
    }
}

int colorzsum(int i,int n){
    int endcheck=1,newcolorz=0;
    if(color[i]=='B'){
    newcolorz++;
    }
    else {
         newcolorz--;
    }
            for (int j = 2; j < n+1 ; j++)
    {
        if(number[j]==i) {
            endcheck=0;
            newcolorz+=colorzsum(j,n);
            }
    }
    return newcolorz;
}

int main()
{
    int n,s;
    scanf("%d", &n);
    int i = 2;
    while (i < n+1)
    {
        scanf("%d", &number[i]);
        if(number[i]==1) s=i;
        i++;
    }
    i = 1;
    getchar();
    while (i < n + 1)
    {
        scanf("%s", &color[i]);
        i++;
    }
    setto1(n);
    ansfunc(1,n);
    printf("%d",ans);



    return 0;
}