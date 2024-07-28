#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct linked_strings{
    char* input;
    int num;
    struct linked_strings* next;
    struct linked_strings* prev;
};

char repeteadz[32];
int cnt=0;

void findRepeats(char c, struct linked_strings* iterArg){
    if(!iterArg){
        repeteadz[cnt]=c;
        cnt++;
        return;
    }
    int check=0;
    for (int i = 0; i < strlen(iterArg->input); i++)
    {
        if(iterArg->input[i]==c){
            check=1;
            break;
        }
    }
    if(!check) return;
    else findRepeats(c,iterArg->next);  
}

char findKey(){
    char hold;
    char small;
    for (int i = 0; i < 32; i++)
    {
        hold=repeteadz[i];
        if(i==0) small=hold;
        if (hold=='\0') break;
        if(hold<small) small=hold;
    }
    return small;
}

int findHighestAndSet(char c,struct linked_strings* address){
    int big=0;
    int i;
    while(address){
        for ( i = 0; i < strlen(address->input); i++)
        {
            if(address->input[i]==c) {
                address->num=i;
                break;
            }
        }
        if (i>big) big=i;
        address=address->next;
    }
    return big;
}

int main(){
int n;
scanf("%d\n",&n);
struct linked_strings* firstStr= (struct linked_strings*) malloc(sizeof (struct linked_strings) );
firstStr->prev=0;
struct linked_strings* address=firstStr;
address->input=(char*) malloc (sizeof(char) * 28);
for (int i = 0; i < n; i++)
{
    gets(address->input);
    if(i<n-1){
    address->next= (struct linked_strings*) malloc(sizeof (struct linked_strings) );
    address->next->prev=address;
    address->next->next=0;
    address->next->input= malloc (sizeof(char) * 28);
    }
    address=address->next;
}

for (int i = 0; i < strlen(firstStr->input); i++)
{
    findRepeats(firstStr->input[i],firstStr->next);
}
char key=findKey();
int index=findHighestAndSet(key,firstStr);
address=firstStr;
while (address)
{
    printf("%d\n",index-address->num);
    address=address->next;
}





    return 0;
}