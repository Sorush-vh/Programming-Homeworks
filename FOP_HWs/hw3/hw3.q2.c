#include <stdio.h>
int main(){
	int n;
	int i=0;
	int square=0;
	scanf("%d",&n);
	
	if(n%2==0){
	i=2;
	}
	else{
		i=1;
	}
	for( i;i*i<=n;i=i+2){
	if(i*i==n){
		square=1;
	}
}	

if(square==1 || n==0){
	printf("YES");
}

if(square==0 && n!=0){
	printf("NO");
}
return 0;
}
