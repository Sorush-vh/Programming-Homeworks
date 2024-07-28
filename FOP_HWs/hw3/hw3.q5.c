#include <stdio.h>

int main(){
	long long int sum,xorr,zarb;
	scanf("%lld %lld",&sum,&xorr);
	long long int zarbmax=0;
	long long int i=0;
	long long int j=sum;
	long long int imax;
	long long int jmax;
	int checker=0;
	
	for(i; i<=j; i++){
		if( (i^j) == xorr){
			zarb=i*j;
			if(zarb>=zarbmax){
				zarbmax=zarb;
				imax=i;
				jmax=j;
				checker=1;
			}
		}
	j--;	
	}
	
	if(checker){	
	printf("%lld %lld",imax,jmax);
	}
	else{
		printf("None");
	}
	
	
	return 0;
}
