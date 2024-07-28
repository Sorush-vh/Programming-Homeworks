#include <stdio.h>
#include <stdlib.h>
#include <string.h>
char output[1009];
char input[1009];


void revert(int l, int r){
for (int i = 0; i < (r-l+1)/2 ; i++)
{
    output[r-i]=input[l+i];
    output[l+i]=input[r-i];
}
}

int main(){
int n,length=0,left,right;
char c=getchar();
while(c!='\n'){
input[length]=c;
output[length]=c;
c=getchar();
length++;
}
scanf("%d\n",&n);
for (int j = 0; j < n; j++)
{
scanf("%d",&left);
getchar();
scanf("%d",&right);
getchar();
revert(left-1,right-1);
}
for (int i = 0; i < length; i++)
{
    printf("%c",output[i]);
}
printf("\n");


    return 0;
}