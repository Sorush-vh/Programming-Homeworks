#include <stdarg.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

char reg[1000]={0};
char trash1[1000]={0};
char trash2[1000]={0};

int read_word(int n, char* hold,char* target){
    // begire age empty bud bere jelo age , bud doroste va badesham bekhune
    int j=0,check=0;
    int count=0;
    int i;
for ( i= 0; i < strlen(hold); i++)
{
    if(count==n){
        while (1)
        {
        
           if(hold[i]!=' ') break;
           i++;
        }
        
        check=1;
        while (1)
        
        {
            if( hold[i]==' '||hold[i]=='\n' || hold[i]=='\0'||hold[i]==',' ){
                
               if(count!=0) break;
               else
               {
                if(hold[i]!=' ') return 0;
                else{
                        hold[i]=',';
                        break;
                } 
               }
               
            } 
            target[j]=hold[i];
            i++;
            j++;
        }
    }
    if(check) break;
    if(hold[i]==',') count++;
}
    if(!check) return 0;
    else return 1;
}

void read_first(char* input){
    int i=0;
    while (1)
    {
        if(input[i]==' ' || input[i]==',') break;
        reg[i]=input[i];
        i++;
    }
}


int check_intro(char* input){
    char temp[1000]={0};
    read_word(0,input,temp);
    int size=strlen(temp);
for (int i = 0; i < size; i++)
{
    if(temp[i]<33) return 0;
}
if(temp[size-1]=='i') return 1;
else return 2;
}

int check_register(char * text){
    if(strlen(text)!=3) return 0;
    if(text[0]!='$') return 0;
    if( text[1]!='s' && text[1]!='t' && text[1]!='a') return 0;
    if(text[2]<58&& text[2]>47) return 1;
    else return 0;
}

int isdash(char c)
{
    if (c == '-')
        return 1;
    else
        return 0;
}

int isnum(char c)
{
    if ((int)c <= 57 && (int)c >= 48)
        return 1;
    else
        return 0;
}

int check_integer(char* text){
if(!isnum(text[0]) && !isdash(text[0])) return 0;
for (int i = 1; i < strlen(text); i++)
{
    if(!isnum(text[i])) return 0;
}
return 1;
}


int main(){
    char dastan[1000]={0};
    gets(dastan);
    int first_edit=read_word(0,dastan,reg);
    int mod=check_intro(reg),check=1;
    if (!mod)
    {
        check=0;
        
    }
    else
    {
        int length=strlen(reg);



        read_word(1,dastan,trash2);
        if (!check_register(trash2))
        {
            check=0;
            
        }
        memset(trash2,0,strlen(trash2));

        read_word(2,dastan,trash2);
        if(!check_register(trash2)){
            check=0;
        }

        memset(trash2,0,strlen(trash2));

        read_word(3,dastan,trash2);
        if (mod==1)
        {
            if(!check_integer(trash2))
            check=0;
        }
        else{
            if(!check_register(trash2))
            check=0;
        }

        printf("%d",check);
    }
    
    


    return 0;
}