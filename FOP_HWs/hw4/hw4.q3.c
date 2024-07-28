#include <stdio.h>
#include <math.h>

double aspectmax=0;
double ans,tul;

void masahat(int ax,int ay,int bx,int by,int cx, int cy){
	int b1x=bx-ax,b1y=by-ay,b2x=cx-ax,b2y=cy-ay;
	double lolz=b1x*b2y-b1y*b2x;
	 ans=fabs(lolz)/2;
}

void tolezel(int ax,int ay, int bx,int by){
	double lx=ax-bx;
	double ly=ay-by;
	 tul=sqrt(lx*lx+ly*ly);
}

void highestAspect(double x){
	if(x>aspectmax) aspectmax=x;
}


int main(){
	int i=0,n;
		int x1,y1,x2,y2,x3,y3,x4,y4;
		double s1,s2,stot,l1,l2,l3,l4,mohit,nesbat;
		scanf("%d\n",&n);

	
	for(i;i<n;i++){
	scanf("%d %d\n%d %d\n%d %d\n%d %d",&x1,&y1,&x2,&y2,&x3,&y3,&x4,&y4);
	masahat(x1,y1,x2,y2,x3,y3);
	s1=ans;
	masahat(x1,y1,x3,y3,x4,y4);
	s2=ans;
	stot=s1+s2;
	
	tolezel(x1,y1,x2,y2);
	l1=tul;
	tolezel(x2,y2,x3,y3);
	l2=tul;
	tolezel(x3,y3,x4,y4);
	l3=tul;
	tolezel(x1,y1,x4,y4);
	l4=tul;
	mohit=l1+l2+l3+l4;
	
	nesbat = stot/ mohit;
	highestAspect(nesbat);
	printf("%0.2lf\n%0.2lf\n",stot,mohit);
	}
	printf("%0.2lf",aspectmax);
	
	return 0;
}
