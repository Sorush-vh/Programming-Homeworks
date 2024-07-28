#include <stdio.h>
int check = 0;
int legal=1;

void parantezer(char a)
{
    if (a == EOF || a == '\n')
    {
        if (check == 0)
            printf("CORRECT");
        else
            printf("INCORRECT");
    }
    else
    {
        if (a == '(')
            check++;
        if (a == ')')
        {
            if (check > 0)
                check--;
            
            else{
                printf("INCORRECT");
                legal=0;
            }
        }
        if(legal){
        a = getchar();
        parantezer(a);
        }
    }
}

    int main()
    {
        int a = getchar();
        parantezer(a);

        return 0;
    }