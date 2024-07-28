#include <stdio.h>


int main(){
	char star='*';
	char khat='-';
	int m1,m2,h;
	scanf("%d %d %d",&m1,&m2,&h);
	int i=0,j=0,k;
	int n=(h-1)/2;
	long long int lmax=(n/2)*(m1+m2),lsec=lmax-(n*m1/2);
    int lol=0;
	
	for(i; i<(n/2+1) ;i++){
	//if(i){ ino bebar tuye sharte un vasat
	for(j;j<lmax-i*m1;j++){
	printf("%c",khat);
	}
	
	j=0;
	printf("%c",star);
	
	for(j; j<2*i*m1-1 ;j++){
	printf("%c",khat);
	}
	j=0;
	
	if(i&&m1!=0){
	printf("%c",star);
	}
	
	for(j;j<lmax-i*m1;j++){
		printf("%c",khat);
	}
	j=0;
	printf("\n");
}

i--;
k=1;
for( k; k<(n/2+1) ;k++ ){
	
		for(j;j<lmax-i*m1-k*m2;j++){
	printf("%c",khat);
	}
	
	j=0;
	printf("%c",star);
	
	for(j; j<2*(i*m1+k*m2)-1 ;j++){
	printf("%c",khat);
	}
	j=0;
	
	if(i){
	printf("%c",star);
	}
	
	for(j;j<lmax-i*m1-k*m2;j++){
		printf("%c",khat);
	}
	j=0;
	printf("\n");
}


i=1;
k=0;
for(i; i<(n/2+1) ;i++){ 

			for(j;j<i*m2;j++){
	printf("%c",khat);
	}
	
	j=0;
	printf("%c",star);
	
	for(j; j<2*(lmax-i*m2-k*m1)-1 ;j++){
	printf("%c",khat);
	}
	j=0;
	
	if(i==n/2 && m1==0) ;
	else printf("%c",star);

	
	for(j;j<i*m2+k*m1;j++){
		printf("%c",khat);
	}
	j=0;
	printf("\n");
}
i--;
k=1;
for(k; k<(n/2+1) ;k++){ 

			for(j;j<i*m2+k*m1;j++){
	printf("%c",khat);
	}
	
	j=0;
	printf("%c",star);
	
	for(j; j<2*(lmax-i*m2-k*m1)-1 ;j++){
	printf("%c",khat);
	}
	j=0;
	
	if(m1!=0){
	if(k!=n/2){
	printf("%c",star);
}
}
	
	for(j;j<i*m2+k*m1;j++){
		printf("%c",khat);
	}
	j=0;
	printf("\n");
}
	
	
	
	return 0;
}
