#include <stdio.h>
#include <math.h>

int dataArray [100][3];

void getarrays(int n){

for (int i = 0; i < n; i++)
{
scanf("%d",&dataArray[i][0]);
scanf("%d",&dataArray[i][1]);
scanf("%d",&dataArray[i][2]);
}
}

int checkVol(int v,int i,int j,int k){
int v1= dataArray[i][0]*(dataArray[j][1]*dataArray[k][2]-dataArray[k][1]*dataArray[j][2]);
int v2= -dataArray[i][1]*(dataArray[j][0]*dataArray[k][2]-dataArray[j][2]*dataArray[k][0]);
int v3=dataArray[i][2]*(dataArray[j][0]*dataArray[k][1]-dataArray[j][1]*dataArray[k][0]);
double vtot = fabs(v1+v2+v3);
int convert =vtot;
if (convert>v) return 1;
else return 0; 
}

int calcNum(int n,int v){
    int counter=0,j,k;
for (int i = 0; i < n; i++)
{
j=i+1;
    for (j ; j < n; j++)
    {
        k=j+1;
        for ( k ; k < n; k++)
        {
            counter=counter+checkVol(v,i,j,k);
        }
        
    }
    
}
return counter;
}


int main(){
int n,v;
scanf("%d %d",&n,&v);
getarrays(n);
int ans=calcNum(n,v);
printf("%d",ans);

    return 0;
}