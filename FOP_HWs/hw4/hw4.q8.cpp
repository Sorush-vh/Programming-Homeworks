#include "grader.h"
#include <stdarg.h>
#include <math.h>

long long solve(int num, ...)
{
   va_list dastan;
   long long int sum = 0;
   int i = 0;
   int a = 0, b = 0;
   double lol = 0;
   long long int convert = 0;
   va_start(dastan, num);

   for (i; i < num; i++)
   {
      b = va_arg(dastan, int);
      if(i==0) a=b;
      lol = fabs(b - a);
      convert = lol;
      sum = sum + convert;
      a = b;
   }
   va_end(dastan);

   return sum;
}

