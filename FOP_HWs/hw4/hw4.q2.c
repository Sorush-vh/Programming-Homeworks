#include <stdio.h>
int a,b,c;

int buildnum(int i){
	int lol=(a*i*i + b*i +c);
	return lol;
}


int main(){
	int n,k;
	scanf("%d %d %d\n%d\n%d",&a,&b,&c,&n,&k);
	
	int j=0,checkNum,counter=0,i,checker=0;
	for(j;j<n;j++){
		checkNum=buildnum(j);
		
		i=1;
		for(i;i<=checkNum;i++){
			if(checkNum%i==0) counter++;
		}
		
		if (counter>=k){
			printf("%d",checkNum);
			checker=1;
			break;
		}
		
		counter=0;
	}
	
	if(checker==0) printf("No match found!");
	
	return 0;
}

