#include "grader.h"

int bemimimz(int a, int b)
{
    if (b)
        return bemimimz(b, a % b);
    else
        return a;
}
long long int bemimim(int a, int b, int c)
{
    int ans1=bemimimz(a,b);
    int ans2=bemimimz(ans1,c);
    long long int ansf=1ll*ans2;
    return ansf;
}

long long int kemimim(int a, int b)
{
    int bmm = bemimimz(a,b);
    long long int ans =1ll* a * b / bmm;
    return ans;
}

#if s == 0
long long int findDivisor(int a, int b)
{
    return kemimim(a, b);
}

#else
long long int findDivisor(int a, int b, int c)
{
    return bemimim(a, b, c);
}

#endif
