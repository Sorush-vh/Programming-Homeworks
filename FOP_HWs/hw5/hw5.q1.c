#include <stdio.h>
int myArray[100000];

void printshiftednumz(int n, int k)
{
    for (int i = 0; i < n; i++)
    {
        if (i - k >= 0)
            printf("%d ", myArray[i - k]);
        else
        {
            printf("%d ", myArray[n - k + i]);
        }
    }
}

void getarray(int n)
{
    for (int i = 0; i < n ; i++)
    {
        scanf("%d", &myArray[i]);
    }
}

int main()
{
    int k, n;
    scanf("%d %d\n", &n, &k);
    k=k%n;
    getarray(n);
    printshiftednumz(n,k);

    return 0;
}