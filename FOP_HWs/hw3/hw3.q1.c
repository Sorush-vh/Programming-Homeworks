#include <stdio.h>

int main(){
	int min=0,max=0;
	int i=1;
	int a;	
	float avg,sum;
	
	
	
	while(1){
	
	scanf("%d",&a);
	if(a!=-1) getchar();
	else break;
	
	
	if(min){
		if(a<min) min=a;
	}
	else{
		min=a;
	}
	
		if(max){
		if(a>max) max=a;
	}
	else{
		max=a;
	}
	sum=sum+a;
    avg=sum/i;
    i++;
	}
printf("%d\n%d\n%0.2f",min,max,avg);

	return 0;
}
