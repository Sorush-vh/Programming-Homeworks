#include <stdio.h>
int number[1000000];
int code[1000000];

void getnumber(int n){
    for (int i = 0; i < n; i++)
    {
        scanf("%d",&number[i]);
    }
    
}

void getcode(int n){
    for (int i = 0; i < n; i++)
    {
        scanf("%d",&code[i]);
    }
    
}

int checkgreater(int i,int j){
    if (code[i]>code[j]) return i;
    else if (code[j]>code[i]) return j;

    else
    {
    if (number[i]>number[j]) return j;
    else return i;
    }
}

int findgreater(int n)
{
int great=0,check=0;
for (int i = 1; i < n; i++)
{
    check=i;
    great=checkgreater(check,great);
}
return great;
}




int main()
{
int n,ans;
scanf("%d/n",&n);
getnumber(n);
getchar();
getcode(n);

ans=findgreater(n);
printf("%d %d",number[ans],code[ans]);

    return 0;
}