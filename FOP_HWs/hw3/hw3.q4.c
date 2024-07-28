#include <stdio.h>

int main(){
	int r,n;
	scanf("%d %d",&n,&r);
	int i=2;
	int j;
	int k=1;
	int x;
	int sumx=1;
	int sumtot=0;

	
	for(i; i<n;i++){
		j=i;
		while(j>0){
    x=j%10;
    for(k; k<x+1 && x!=0 ; k++){
    	sumx=sumx*k;
	}
	j=j/10;
	sumtot=sumtot+sumx;
	sumx=1;
	k=1;
}
		if( (sumtot%i) ==r ){
			printf("%d\n",i);
		}	
		sumtot=0;
	}
	return 0;
}
