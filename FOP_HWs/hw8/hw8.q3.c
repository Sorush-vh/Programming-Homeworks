#include <stdio.h>

char string[10009];
int repeated[10009]={0};

void handle_repetition(int source,int index,int n){
    if(string[source]!=string[index]|| index>=n) return;
    else{
        if(!repeated[source]) repeated[source]=2;
        else repeated[source]++;
        string[index]='\0';
        index++;
        handle_repetition(source,index, n);
    }
}

int main(){
char c=getchar();
int length=0;
while (c!='\n' && c!=EOF)
{
    string[length]=c;
    c=getchar();
    length++;
}

//beri jelo har tedadi ke yeki bud ro ruye moadelesh benevisi badiaro khali bezari begi print nakone age adadi bud adade ro badesh print kone
for (int j = 0; j < length; j++)
{
if(string[j]) handle_repetition(j,j+1,length);
}
for (int j = 0; j <length; j++)
{
    if(string[j]) printf("%c",string[j]);
    if(repeated[j]) printf("%d",repeated[j]);
}
    return 0;
}