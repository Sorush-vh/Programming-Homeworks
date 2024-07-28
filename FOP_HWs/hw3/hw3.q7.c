#include <stdio.h>
#include <math.h>
int main(){
	int n;
	scanf("%d",&n);
	getchar();
	 int i;
	 char c1;
	 char c2;
	 int nozuli=0,sowudi=0,khata=0;
	 int check,num1,num2;
	 
	 
	 

	 
	 
	 
	 for(i=0; i<n ; i++){
	c1=getchar();
	c2=getchar();
			while(1){
				num1=c1;
				num2=c2;
				check= num2>=num1;
			//	printf("%d",check);
				
				if (c2=='\n') break;
				
				if( check ) {
					sowudi=1;
				}
				else {
					nozuli=1;
				}
				
				if(nozuli==1 && sowudi==1){
					khata++;
					sowudi=check;
					nozuli=!check;
				}
				c1=c2;
				c2=getchar();
 			}
 			
	if(khata==0) printf("common\n");
	else if(khata==1) printf("efficient\n");
	else printf("inefficient\n");

sowudi=0;
nozuli=0;
khata=0;
}


	return 0;
}
