#include <stdio.h>


int main(){
	long long int xa,ya,xb,yb,xc,yc,xd,yd;
	
	long long int lab,lbc,lcd,lda,qotr1,qotr2;
	//tavando manzure
 long long int masahatdo;
	
	
	
	scanf("%lld %lld\n%lld %lld\n%lld %lld\n%lld %lld",&xa,&ya,&xb,&yb,&xc,&yc,&xd,&yd);
	
	masahatdo= ((xa-xc)*(yb-yd)-(ya-yc)*(xb-xd)) * ((xa-xc)*(yb-yd)-(ya-yc)*(xb-xd)) /4;
	lab=(xa-xb)*(xa-xb)+(ya-yb)*(ya-yb);
	lbc=(xc-xb)*(xc-xb)+(yc-yb)*(yc-yb);
	lcd=(xc-xd)*(xc-xd)+(yc-yd)*(yc-yd);
	lda=(xa-xd)*(xa-xd)+(ya-yd)*(ya-yd);
	qotr1=(xa-xc)*(xa-xc)+(ya-yc)*(ya-yc);
	qotr2=(xb-xd)*(xb-xd)+(yb-yd)*(yb-yd);
	
	long long int cross1=(xb-xa)*(yc-yb)-(xc-xb)*(yb-ya);
	long long int cross2=(xc-xb)*(yd-yc)-(xd-xc)*(yc-yb);
	long long int cross3=(xd-xc)*(ya-yd)-(xa-xd)*(yd-yc);
	long long int cross4=(xa-xd)*(yb-ya)-(xb-xa)*(ya-yd);
	
	
	if(   cross1*cross2>0 && cross2*cross3>0 && cross3*cross4>0){
	
	
	if(masahatdo==(lab*lbc)  && lab==lbc && lbc==lcd && lcd==lda){
		printf("Square");
	}
	
	else if( masahatdo==(lab*lbc) &&masahatdo==(lbc*lcd) && masahatdo==(lcd*lda) && masahatdo==(lda*lab) && lab==lcd && lbc==lda) {
		printf("Rectangle");
	}
	else if(masahatdo==qotr1*qotr2/4 && lab==lbc && lbc==lcd && lcd==lda){
		printf("Diamond");
	}
	else if(  masahatdo==qotr1*qotr2/4 &&  ( (lab==lbc && lda==lcd) || (lab==lda && lbc==lcd) )    ){
		printf("Kite");
	}
	else if(lab==lcd && lbc==lda){
		printf("Parallelogram");
	}
	else{
		printf("None");
	}
;	
}
else printf("None");
	
	
	
	return 0;
}
