#include <stdio.h>
#include <math.h>

int zaribnazade = 0;interzade=0;
int var = 0;
void space(int i)
{
	for (int j = 0; j < i; j++)
	{
		getchar();
	}
}

int getnum()
{
	int num = 0, raqam = 0;
	char a;
	while (1)
	{
		a = getchar();
		if (a == 'x')
		{
			zaribnazade = 1;
			num = 1;
			getchar();
			return num;
		}
		else if (a == ' ')
			return num;
			else if(a=='\n'){
				interzade=1;
				return num;
			}
		else
		{
			raqam = a - '0';
			num = num * 10 + raqam;
		}
	}
}

int calcOneTerm()
{
	double lol;
	int zarib = 0, convert, tavan;
	zarib = getnum();
	if (!zaribnazade)
		space(4);
	space(2);
	tavan = getnum();
	// in tahesh age jomle akhar bashe momkene bug bezane
	lol = pow(var, tavan);
	convert = lol;
	int ans = zarib * convert;
	return ans;
}

int tellsign()
{
	char a = getchar();
	if (a == '+')
	{
		getchar();
		return 1;
	}
	if (a == '-')
	{
		getchar();
		 return 0;
	}
	if (a == '\n')
		return -1;
}

int main()
{
	scanf("%d\n", &var);
	int fullans = 0, i = 0, s = 0, newterm;

	fullans = calcOneTerm();
	while (1)
	{
		if (interzade) {
			printf("%d",fullans);
			break;
		}
		
		s = tellsign();
		if (s == 1)
		{
			newterm = calcOneTerm();
			fullans=fullans+newterm;
		}
		if(s==0 )
		{
			newterm = calcOneTerm();
			fullans=fullans-newterm;
		}


		if (interzade) {
			printf("%d",fullans);
			break;

		}
		if(s==-1) {
			printf("%d",fullans);
			break;
			}

	}
	return 0;
}
