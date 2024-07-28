#include <stdio.h>
int table[9][9];

void getTable()
{
    int j = 0;
    for (int i = 0; i < 9; i++)
    {
        if (i != 0)
            getchar();
        for (j; j < 9; j++)
        {
            scanf("%d", &table[i][j]);
        }
        j = 0;
    }
}

int checkSatr()
{
    int x = 0, n = 1, numcheck = 0, truth = 1;
    for (int s = 0; s < 9; s++)
    {
        for (n; n < 10; n++)
        {
            for (x; x < 9; x++)
            {
                if (table[s][x] == n)
                {
                    numcheck = 1;
                    break;
                }
            }
            x = 0;
            if (numcheck)
                numcheck = 0;
            else
            {
                truth = 0;
                break;
            }
        }
        n = 1;
        if (!truth)
            break;
    }
    return truth;
}

int checkSotun()
{
    int x = 0, n = 1, numcheck = 0, truth = 1;
    for (int s = 0; s < 9; s++)
    {
        for (n; n < 10; n++)
        {
            for (x; x < 9; x++)
            {
                if (table[x][s] == n)
                {
                    numcheck = 1;
                    break;
                }
            }
            x = 0;
            if (numcheck)
                numcheck = 0;
            else
            {
                truth = 0;
                break;
            }
        }
        n = 1;
        if (!truth)
            break;
    }
    return truth;
}

int checkSmallSquare(int satr, int sotun)
{
    int i=satr,j=sotun,numcheck=0,truth=1;
for (int n = 1; n < 10; n++)
{
    for (i ; i<satr+3 ; i++)
    {
        for ( j; j < sotun+3; j++)
        {
            if (table[i][j] == n)
            {
                numcheck=1;
            }
            
        }
        j=sotun;
    }
    i=satr;
    if (numcheck)
    {
        numcheck=0;
    }
    else{
        truth=0;
    }
}
return truth;
}

int checksquarez()
{
    int j = 0, smallcheck = 0, truth = 1;
    for (int i = 0; i < 7; i += 3)
    {
        for (j; j < 7; j += 3)
        {
            smallcheck = checkSmallSquare(i, j);
        }
        j = 0;
        if (smallcheck)
            smallcheck = 0;
        else
            truth = 0;
    }
    return truth;
}

int main()
{
    getTable();


    if (checkSatr()&&checkSotun()&&checksquarez())
    {
        printf("Valid");
    }
    else printf("Not Valid");

    return 0;
}