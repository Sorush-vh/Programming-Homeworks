#include <stdio.h>

int count=0,n;

void findnumbers(int i){
if(i>n);
else{
count++;
findnumbers(10*i);
findnumbers(10*i+1);
}
}


int main(){

scanf("%d",&n);

findnumbers(1);
printf("%d",count);

    return 0;
}
