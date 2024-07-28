#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

char string[10000];
char number[100] = {'\0'};
char fs[100]={0};
char is[100]={0};
char fchecks[100]={0};
char ichecks[100]={0};
double fboss = 0;
double fcheck = 0;
int iboss = 0;
int icheck = 0;
int s=0;


int isnum(char c)
{
    if ((int)c <= 57 && (int)c >= 48)
        return 1;
    else
        return 0;
}

int isdot(char c)
{
    if (c == '.')
        return 1;
    else
        return 0;
}

int number_protocol(int i){
    int count=0;
    int j=0;
    while (1)
    {
        if (isnum(string[i+j]))
        {
            number[j]=string[i+j];
            j++;
        }
        else if(isdot(string[i+j])){
            number[j]=string[i+j];
        count++;
        j++;
        }
        else break;
    }
    return count+j*10;
}

void float_string_update(){

    sscanf(fchecks,"%lf",&fcheck);
    if(fcheck>fboss) {
        fboss=fcheck;
        memset(fs,0,strlen(fs));
        strcpy(fs,fchecks);
    }
    memset(fchecks,0,strlen(fchecks));
}

void int_string_update(){
    sscanf(ichecks,"%d",&icheck);
    if(icheck>iboss) {
        iboss=icheck;

        memset(is,0,strlen(is));
        strcpy(is,ichecks);
    }
    memset(ichecks,0,strlen(ichecks));
    }

void read_string(){
     int size=strlen(string),data;
     
     for (int i = 0; i < size; i++)
     {
        if( !isnum(string[i]) && !isdot(string[i])  ) ;
        else{
            data=number_protocol(i);
                i+=data/10;
            if(data%10>1){
                memset(number,0,strlen(number));
            }
            else{
                if(data%10){
                    strcpy(fchecks,number);
                    float_string_update();
                }
                else
                {
                    strcpy(ichecks,number);
                    int_string_update();
                }
                
            }
            memset(number,0,strlen(number));
        }
     }
     
}


int main(){
char lol[100];
// strcpy(lol,"12.12");
// int m=sscanf(lol,"%lf",&fboss);
// printf("%0.2lf",fboss);


    gets(string);
    int size=strlen(string);
    read_string();
    if((int)fboss>= iboss) printf("%s",fs);
    else printf("%s",is);

    return 0;
}