#include <stdio.h>

int avalcheck(x){
		int i=1,counter=0;
		for(i;i<=x;i++){
			if(x%i==0) counter++;
		}
		
		if (counter!=2) return 0;
		else return 1;
}


int main(){
int a,b,c,d,p,aval;
scanf("%d",&p);

aval=avalcheck(p);
if (!aval) {
	a=-1;
	b=-1;
	c=-1;
	d=-1;
	printf("%d %d %d %d",a,b,c,d);
}

else{
	if (p>100)
	{
	printf("%d %d %d %d",100,100,100,100);
	}
	else{
		a=p;
		b=1;
		c=2;
		d=p;
		printf("%d %d %d %d",a,b,c,d);
		}
}
	return 0;
}
