#include <stdio.h>

int main(){
	int n;
	scanf("%d\n",&n);
    int i=0,num,neededval;
    long long unsigned validnums=0,kApproval,nums;
    int j=0,k,numberUnderCheck,checker=0;
    
    for(i;i<n;i++){
    	scanf("%d\n",&num);
       validnums=validnums| (1ll<<num);
	}
	scanf("%d",&neededval);

	nums=validnums;	
	
for(j;j<64;j++){
	if (j>0) numberUnderCheck=(nums%2)*j;
	else numberUnderCheck= (nums%2);
	
	if(numberUnderCheck){
		if(numberUnderCheck==1&&j==0) numberUnderCheck=0;
			k=neededval-numberUnderCheck;
				if(k<=63&&k>=0&&k!=numberUnderCheck){
					kApproval= (1ll<<k)&validnums;
					if (kApproval) checker=1;
					}	
 	}
 		nums=nums/2;
}


    if(checker==1) printf("YES");
    if(checker==0) printf("NO");
	return 0;
}
