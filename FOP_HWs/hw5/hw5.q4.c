#include <stdio.h>
int dataArray[5000];
int formercombo[3]={-1,-1,0};
int currentcombo[3]={-1,-1,0};
int checkarray[3]={-1,-1,0};

int checkToAdd(int i, int sum, int n)
{
    if (i<n-1)
    {
         if ( currentcombo[2]+dataArray[i]>0 ) {
        return 1;
    }

    else {
        return 0;
    }
    }

    else{
        if (dataArray[i]>0)
        {
            return 1;
        }
        else return 0;
    }
    
   
 
}

void updatecheckarray(){
    if (currentcombo[2]>checkarray[2])
    {
        checkarray[0]=currentcombo[0];
        checkarray[1]=currentcombo[1];
        checkarray[2]=currentcombo[2];
    }
    
}

void shouldAdd(int i){
            currentcombo[2] = currentcombo[2] + dataArray[i];
            if (currentcombo[0] == -1) currentcombo[0] = i;
            currentcombo[1] = i;
}

void finish(){ 
if (checkarray[2]>formercombo[2])
{
    formercombo[2]=checkarray[2];
    formercombo[1]=checkarray[1];
    formercombo[0]=checkarray[0];
}
    currentcombo[0]=-1;
    currentcombo[1]=-1;
    currentcombo[2]=0;
        checkarray[0]=-1;
        checkarray[1]=-1;
        checkarray[2]=0;
}

void superFunction(int n)
{
    int i=0;

    while (i<n)
    {
    for ( i; i < n; i++)
    {
          if(checkToAdd (i,currentcombo[2],n) )  {
            shouldAdd(i) ;
            updatecheckarray();
          }
            else break;
    }
    finish();
    i++;
    }
}

int main()
{
    int n;
    scanf("%d\n", &n);
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &dataArray[i]);
    }
superFunction(n);

printf("%d\n",formercombo[2]);
printf("%d %d",formercombo[0]+1,formercombo[1]+1);
    return 0;
}