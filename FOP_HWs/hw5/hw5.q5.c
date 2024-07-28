#include <stdio.h>
char firstword[1000];
char secondword[1000];

void getWord1(int j){
for (int i = 0; i < j; i++)
{
    firstword[i]=getchar();
}
}

void getWord2(int j){
for (int i = 0; i < j; i++)
{
    secondword[i]=getchar();
}
}

int oneComparisonData(){
    int j;
    scanf("%d\n",&j);
    getWord1(j);
    scanf("\n");
    getWord2(j);
    return j;
}

int checkPossibility(int z){
    int lettercheck=0,possible=1;
    for (int i = 0; i < z; i++)
    {
       for (int j = 0; j < z; j++)
       {
        if (firstword[i]==secondword[j])
        {
            secondword[j]='0';
            lettercheck=1;
            break;
        }
       }



       if (lettercheck) lettercheck=0;
       else
       {
        possible=0;
        break;
       }
    }
    return possible;

}

int main(){
int n,j,t;
scanf("%d",&n);
for (int i = 0; i < n; i++)
{    
    scanf("\n");
    j=oneComparisonData();
    t=checkPossibility(j);
    if (t)
    {
        printf("YES\n");
    }
    else printf("NO\n");
}




    return 0;
}