#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char namez[100][100] = {'\0'};
int repetition[100] = {0};
char input[1000];
char checkname1[100];
char checkname2[100];
char newname[100] = {'\0'};

void clearInput()
{
    for (int i = 0; i < 1000; i++)
    {
        input[i] = '\0';
    }
}

void clearname()
{
    for (int i = 0; i < 100; i++)
    {
        newname[i] = '\0';
    }
    for (int i = 0; i <100; i++)
    {
        checkname2[i] = '\0';
    }
        for (int i = 0; i < 100; i++)
    {
        checkname1[i] = '\0';
    }
    
}

int findNameSize()
{
    int i = 0;
    while (1)
    {
        if (!newname[i])
            break;
        i++;
    }
    return i;
}

int checkSimiliarity(int indexes)
{
    int eq = 1;
    for (int i = 0; i < indexes; i++)
    {

        for (int j = 0; j < 100; j++)
        {

            if (newname[j] != namez[i][j])
            {
                eq = 0;
                break;
            }
        }
        if (eq == 1)
        {
            repetition[i]++;
            return 1;
        }
        eq = 1;
    }
    return 0;
}

void copyName(int index)
{
    for (int i = 0; i < index + 1; i++)
    {
        if (namez[i][0] == '\0')
        {
            for (int j = 0; j < 100; j++)
            {
                namez[i][j] = newname[j];
            }
            break;
        }
    }
}

void copychecks(int index, int which)
{
    if (which == 1)
    {
        for (int j = 0; j < 100; j++)
        {
            checkname1[j] = namez[index][j];
        }
    }
    else
    {
        for (int j = 0; j < 100; j++)
        {
            checkname2[j] = namez[index][j];
        }
    }
}
// checkOrder(order,"exit", i) && i + 1 == sizeof("exit")

int main()
{
    int m, n, size, holder;
    char c;
    int j;
    int k;
    int dashCount = 0;
    scanf("%d %d\n", &n, &m);
    for (int i = 0; i < n; i++)
    {
        j = 0;
        k = 0;
        dashCount = 0;
        clearInput();
        gets(input);
        while (1)
        {
            c = input[j];
            if (c == '\n' || c == '\0')
                break;
            if (c == '-')
                dashCount++;
            else if (dashCount == m - 1)
            {
                newname[k] = c;
                k++;
            }
            if (dashCount > m - 1)
                break;
            j++;
        }
        if (!checkSimiliarity(i))
            copyName(i);
        clearname();
    }

    for (int c = 0; c < n - 1; c++)
    {
        for (int d = 0; d < n - c - 1; d++)
        {
            copychecks(d, 1);
            copychecks(d + 1, 2);
            if (strcmp(checkname1, checkname2) > 0 && checkname1[0] != '\0' && checkname2[0] != '\0')
            {
                for (int ii = 0; ii < 100; ii++)
                {
                    newname[ii] = namez[d][ii];
                }
                holder = repetition[d];

                for (int ii = 0; ii < 100; ii++)
                {
                    namez[d][ii] = namez[d + 1][ii];
                }
                for (int ii = 0; ii < 100; ii++)
                {
                    namez[d + 1][ii] = newname[ii];
                }
                repetition[d] = repetition[d + 1];
                repetition[d + 1] = holder;
                clearname();
            }
        }
    }

    for (int i = 0; i < n; i++)
    {
        if (namez[i][0])
        {
            printf("%7d ", 1 + repetition[i]);
            for (int j = 0; j < 100; j++)
            {
                if (namez[i][j] != '\0')
                    printf("%c", namez[i][j]);
                else
                    break;
            }
            printf("\n");
        }
    }
    return 0;
}