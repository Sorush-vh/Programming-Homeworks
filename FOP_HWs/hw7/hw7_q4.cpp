#include "grader.h"
#include <stdlib.h>

int cmpName(struct Gift g1, struct Gift g2){
    int check=0;
for (int i = 0; i < 100; i++)
{
    if(g1.name[i]==NULL&&g2.name[i]!=NULL)
    break;
    if(g1.name[i]!=NULL&&g2.name[i]==NULL)
    {
        check=1;
        break;
    }

    if( g1.name[i]> g2.name[i]){
        check=1;
        break;
    }
    if(g1.name[i]<g2.name[i]) break;
}
return check;
}

int cmpPrice(struct Gift g1, struct Gift g2){
if(g1.price==g2.price){
    if(g1.id>g2.id) return 1;
    else return 0;
}
if(g1.price > g2.price) return 1;
else return 0;
}

int cmpHeight(struct Gift g1, struct Gift g2){
    if(g1.height==g2.height){
    if(g1.id>g2.id) return 1;
    else return 0;
}
    if(g1.height>g2.height) return 1;
    else return 0;
}

int cmpWeight(struct Gift g1, struct Gift g2){
    if(g1.weight==g2.weight){
    if(g1.id>g2.id) return 1;
    else return 0;
}
        if(g1.weight<g2.weight) return 1;
    else return 0;
}

void sort(int n, struct Gift arr[], int (*cmp)(struct Gift, struct Gift)){
    struct Gift temp;

        for(int i=1;i<n;i++){

        for(int j=0; j<n-i;j++){

            if( (*cmp) (arr[j],arr[j+1])) 
            {
                temp=arr[j];

                arr[j]=arr[j+1];

                arr[j+1]=temp;

            } 
        }
        }
}
