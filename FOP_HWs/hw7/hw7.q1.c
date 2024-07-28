#include <stdio.h>



void giveans(unsigned char *address)
{
    int mod;
    int num;
 for (int  j = 0; j <8 ; j++)
 {
        num=*(address+1*j);
    for (int i = 1; i >-1 ; i--)
    {
        mod=15&(num>>4*i);
        printf("%x",mod);
    }
 }
}

int main()
{
    double num;
    scanf("%lf", &num);
    unsigned char *address = (unsigned char *)&num;
    giveans(address);

    return 0;
}