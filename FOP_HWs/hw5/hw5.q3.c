#include <stdio.h>
int matris1[500][500];
int matris2[500][500];
long long int matrisAns[500][500];
void getmatris1(int n)
{
    int j = 0;
    for (int i = 0; i < n; i++)
    {
        j = 0;
        for (j; j < n; j++)
        {
            scanf("%d", &matris1[i][j]);
        }
    }
}

void getmatris2(int n)
{
    int j = 0;
    for (int i = 0; i < n; i++)
    {
        j = 0;
        for (j; j < n; j++)
        {
            scanf("%d", &matris2[i][j]);
        }
    }
}

void jam(int n)
{
    int j;
    for (int i = 0; i < n; i++)
    {
        j=0;
        for ( j = 0; j < n; j++)
        {
            matrisAns[i][j]=matris1[i][j]+matris2[i][j];
        }
    }
}

void kam(int n)
{
    int j;
    for (int i = 0; i < n; i++)
    {
        j=0;
        for ( j = 0; j < n; j++)
        {
            matrisAns[i][j]=matris1[i][j]-matris2[i][j];
        }
    }
}

void eachIndexVal(int i,int j,int n){
    for(int k=0; k<n ; k++){
        matrisAns[i][j]=matrisAns[i][j]+matris1[i][k]*matris2[k][j];
    }
}

void zarb(int n){
    int j;
    for (int i = 0; i < n; i++)
    {
        j=0;
        for(j; j<n; j++){
            eachIndexVal(i,j,n);
        }
    }
    
}

void printAns(n)
{
    int j;
    for (int i = 0; i < n ; i++)
    {
        j=0;
        for ( j = 0; j < n; j++)
        {
            printf("%lld ",matrisAns[i][j]);
        }
        printf("\n");
    }
    
}

int main()
{
    int n;
    char operand;
    scanf("%d\n", &n);
    getmatris1(n);
    getchar();
    operand = getchar();
    getmatris2(n);
    
    if (operand=='A') jam(n);
    if (operand=='S') kam(n);
    if (operand=='M') zarb(n);
    printAns(n);
    
        
    
    

    return 0;
}