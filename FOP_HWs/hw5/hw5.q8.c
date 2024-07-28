#include <stdio.h>
char word[51];
void setToZero()
{
    for (int i = 0; i < 51; i++)
    {
        word[i] = '0';
    }
}

void getword(int k)
{
    for (int i = 0; i < k; i++)
    {
        scanf("%c", &word[i]);
    }
}

int wordcap(char c)
{
    if ((int)'A' <= (int)c && (int)c <= (int)'Z')
        return 1;
    else
        return 0;
}

int correctcap(int i)
{
    if (wordcap(word[i]))
    {
        if (!wordcap(word[i - 1]) && !wordcap(word[i + 1]))
            return 1;
        else
            return 1;
    }
    else
        return 0;
}

int checkpascal(int k)
{
    int check = 1, count = 1;
    if (wordcap(word[0]))
    {
        for (int i = 1; i < k; i++)
        {
            if (wordcap(word[i]))
            {
                    count++;
            }
            if (  word[i] == '-' || word[i] == '_')
            {
                check = 0;
                break;
            }
        }
        if (check&& count>1){
            printf("PascalCase");
            return 1;
        }
        else return 0;
    }
    else return 0;
}

int checkcamel(int k)
{
    int check = 1, count = 0;
    if (!wordcap(word[0]))
    {
        for (int i = 1; i < k; i++)
        {
            if (wordcap(word[i]))
            {
                count++;
            }
            if (word[i] == '-' || word[i] == '_')
            {
                check = 0;
            }
        }
    
            if (count > 0 && check)
        {
            printf("camelCase");
            return 1;
        }
        else
            return 0;
    }
    else return 0;
}

int checksnake(int k){
    int check=1,count=0;
    for (int i = 0; i < k; i++)
    {
        if(wordcap(word[i])) check=0;
        if(word[i]=='_') count++;
        if(word[i]=='-') check=0;
    }
          if (count > 0 && check)
        {
            printf("snake_case");
            return 1;
        }
        else
            return 0;
    
}

int checkkebab(int k){
        int check=1,count=0;
    for (int i = 0; i < k; i++)
    {
        if(wordcap(word[i])) check=0;
        if(word[i]=='-') count++;
        if(word[i]=='_') check=0;
    }
          if (count > 0 && check)
        {
            printf("kebab-case");
            return 1;
        }
        else
            return 0;
}

int main()
{
    int n, k,c1,c2,c3,c4;

    scanf("%d", &n);
    setToZero();
    for (int i = 0; i < n; i++)
    {
        printf("\n");
        getchar();
        scanf("%d ", &k);
        getword(k);
        c1=checkpascal(k);
        c2=checkcamel(k);
        c3=checksnake(k);
        c4=checkkebab(k);
        if(!c1 && !c2 && !c3 && !c4) printf("Undefined Case Style");
        setToZero();
    }
    return 0;
}