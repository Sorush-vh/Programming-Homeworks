#include <string.h>
#include <stdlib.h>
#include <stdlib.h>
#include <math.h>

char encrypt[35][35]={'\0'};
char input[1000];

void fillMatrice(int n,int length){
    int count=0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if(count>length) return ;
            encrypt[j][i]=input[count];
            count++;
        }
    }
}

void readMatrice(int n){
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if(encrypt[i][j]) printf("%c",encrypt[i][j]);
        }
    } 
}

int main()
{
    int length, n;
    gets(input);
    length = strlen(input);
    n = (int)ceil(sqrt(length));
    fillMatrice(n,length);
    readMatrice(n);
    return 0;
}