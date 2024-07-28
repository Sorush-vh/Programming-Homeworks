
#include <stdio.h>
#include <math.h>
//int adadin10=0,numinbase2=0;


int to10(int x,int base){
 int raqam,j=0,convert,adadin10=0,realworth;
 double arzeshraqam;
 
while(1){
 raqam=x%10;
 arzeshraqam=pow(base,j);
 convert=arzeshraqam;
 realworth=raqam*convert;
 adadin10=adadin10+realworth;
 j++;
 x=x/10;
 if (x==0) {
 	return adadin10;
 break;
}
 } 
 
 }

int tobase(int x,int base2 ){
	int i=0,raqam,convert,numinbase2=0;
	double arzeshraqam;
	
	while(1){
		raqam=x%base2;
		arzeshraqam=pow(10,i);
		convert=arzeshraqam;
		numinbase2=numinbase2+convert*raqam;
		i++;
		x=x/base2;
		if(x==0) {
			return numinbase2;
			break;
		}
	}
}

int numzsum(int x1,int x2,int base){
	int realx1,realx2,sum,suminbase;
	realx1=to10(x1, base);
	realx2=to10(x2, base);
	sum=realx1+realx2;
	suminbase=tobase(sum,base );
	return suminbase;
}







int main(){
	int base1,base2,num1;
	scanf("%d %d\n%d",&base1,&base2,&num1);
	int in10,inbase2;
	
	in10=to10(num1,base1);
	inbase2=tobase(in10,base2);
	
	
	int counter=0,fnum,inum=inbase2;
	while(1){
		inum=inum/10;
		counter++;
		
		if(inum==0) {
			if (counter%2==0) fnum=inbase2;
			else {
			fnum=inbase2*10;
			counter++;
		   	}
			break;
		}
	}
	int divider,numz1,numz2,sum;
	double lolz=pow(10,counter/2);
	divider=lolz;
	numz1=fnum%divider;
	numz2=fnum/divider;
	sum=numzsum(numz1,numz2,base2);
	printf("%d",sum);
	
	
	
	
	return 0;
}

