#include "grader.h"
#include <stdlib.h>
#include <string.h>



struct linkedlist{
char a;
struct linkedlist* next;
struct linkedlist* prev;
};




struct linkedlist* create_linkedlist(){
    struct linkedlist* address=(struct linkedlist*) malloc(sizeof(struct linkedlist));
    address->prev=0;
    address->next=0;
    return address;
}


void add(struct linkedlist* address, char c){
if(address->next) add(address->next,c);
else {
    address->a=c;
    address->next=(struct linkedlist*) malloc(sizeof(struct linkedlist));
    address->next->prev=address;
    address->next->next=0;
    }
}

void add_all(struct linkedlist* address, int n, char* string){
if(address->next) add_all(address->next,n,string);
else {
    struct linkedlist* temp=address;
    for (int i = 0; i <n; i++)
    {
    add(temp,string[i]);
    temp=temp->next;
    }
    }
}

void remove_with_index(int n, struct linkedlist* address){
for (int i = 0; i < n; i++)
{
    if(!address->a) i--;
    address=address->next;
}
//avalo akhari
    if(address->prev) address->prev->next=address->next;
    if(address->next) address->next->prev=address->prev;
    address->a='\0';
}

void remove_with_char(char c, struct linkedlist* address){
while(address->next){
    if(address->a==c){
        if(address->prev) address->prev->next=address->next;
        if(address->next) address->next->prev=address->prev;
        address->a='\0';
        break;
    }
    address=address->next;
}
}
    
int get_index_of(char c, struct linkedlist* address){
    int i=0;
while(address->next){
    if(address->a==c&&address->a) return i;
        i++;
        address=address->next;
}
return -1;
}

int size(struct linkedlist* address){
    int i=0;
while(address->next){
    if(address->a){
    i++;
    }
    address=address->next;
}
return i;
}

char get(int n, struct linkedlist* address){
    if(n==0 && !address->a) return get(0,address->next);
    else{
for (int i = 0; i < n; i++)
{
    address=address->next;
}
return address->a;
}
}

// int main(){
// struct linkedlist* linkedlist1;
// linkedlist1=create_linkedlist();
// add(linkedlist1,'a');
// add(linkedlist1,'b');
// add(linkedlist1,'c');
// remove_with_index(0,linkedlist1);
// printf("%c",get(0,linkedlist1));
// return 0;
// }