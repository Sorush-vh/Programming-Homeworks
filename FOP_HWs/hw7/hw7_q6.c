#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define SHIFT 1000000000

struct Accounts
{
    char fname[100];
    char lname[100];
    long long int balance;
    long long int log[10][5][2];
    int i;
    //0create 1deposit 2withdraw 3to 4from
    
};

struct Accounts *accs[1000000];
char input[200];
char order[100];
char nameinputz[100];
char lnameinputz[100];
char trash[100];
char trash2[100];
long long int n=0;

int validAcc(long long int num)
{
    int index = num - SHIFT;
    int check = 1;
    if (index < 0 || index>=n)
        check = 0;
    return check;
}

void setLogto0(long long int num){
    int index=num-SHIFT;
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            accs[index]->log[i][j][0]=0;
            if(j==3||j==4) accs[index]->log[i][j][1]=0;
        }
        
    }
    
}

void makeAcc(char fnamez[100], char lnamez[100])
{
    accs[n] = (struct Accounts *)malloc(sizeof(struct Accounts));
    strcpy((*accs[n]).fname, fnamez);
    strcpy((*accs[n]).lname, lnamez);
    accs[n]->balance = 0;
    printf("Account number: %lld\n", n + SHIFT);
        setLogto0(n+SHIFT);
        accs[n]->i =0;
        accs[n]->log[9-accs[n]->i%10][0][0]=1;
        accs[n]->i +=1;
    n++;
}

void makebatchreg(char fnamez[100],char lnamez[100],int k){
    int start=n;
    for(int i=0;i<k;i++){
        accs[n] = (struct Accounts *)malloc(sizeof(struct Accounts));
    strcpy((*accs[n]).fname, fnamez);
    strcpy((*accs[n]).lname, lnamez);
    accs[n]->balance = 0;
    setLogto0(n+SHIFT);
        accs[n]->i =0;
        accs[n]->log[9-accs[n]->i%10][0][0]=1;
        accs[n]->i +=1;
    n++;
    }
    printf("Account numbers: %lld-%lld\n",SHIFT+start,SHIFT+start+k-1);
}

void makeDeposit(long long int num, long long int amount)
{
    int index = num - SHIFT;
    if (amount <= 0)
        printf("Invalid amount\n");
    else if (!validAcc(num))
        printf("Invalid account number\n");
    else
    {
        accs[index]->balance += amount;
        printf("Success\n");
        accs[index]->log[9-accs[index]->i%10][1][1]=0;
        accs[index]->log[9-accs[index]->i%10][1][0]=amount;
        accs[index]->i +=1;
    }
}

void withdrawCash(long long int num, long long int amount)
{
    int index = num - SHIFT;
    if (amount <= 0)
        printf("Invalid amount\n");
    else if (!validAcc(num))
        printf("Invalid account number\n");
    else
    {
        if ((*accs[index]).balance - amount < 0)
            printf("Not enough balance\n");
        else
        {
            (*accs[index]).balance -= amount;
            printf("Success\n");
            accs[index]->log[9-accs[index]->i%10][2][1]=0;
            accs[index]->log[9-accs[index]->i%10][2][0]=amount;
            accs[index]->i +=1;
        }
    }
}

void tellBalance(long long int num)
{
    int index = num - SHIFT;
    if (!validAcc(num))
        printf("Invalid account number\n");
    else
    {
        printf("$%lld\n", (*accs[index]).balance);
    }
}

void transfer(long long int from, long long int to, long long int amount)
{
    if (amount <= 0)
        printf("Invalid amount\n");
    else if (!validAcc(from))
        printf("Invalid sender account number\n");
    else if (!validAcc(to))
        printf("Invalid receiver account number\n");
    else
    {
        int bonus;
        long long int hold;
        if (amount < 500000)
            bonus = 600;
        if (amount >= 500000 && amount < 4000000)
            bonus = 800;
        if (amount >= 4000000)
            bonus = 1200;
        hold = amount + bonus*(1ll);
        if ((*accs[from-SHIFT]).balance - hold < 0)
            printf("Not enough balance\n");
        else
        {
            int index1=to-SHIFT;
            int index2=from-SHIFT;
            accs[index1]->balance += amount;
            accs[index2]->balance -= hold;
            printf("Success\n");
            accs[index2]->log[9-accs[index2]->i%10][3][0]=to;
            accs[index2]->log[9-accs[index2]->i%10][3][1]=hold;
            accs[index1]->log[9-accs[index1]->i%10][4][0]=from;
            accs[index1]->log[9-accs[index1]->i%10][4][1]=amount;
            accs[index2]->i +=1;
            accs[index1]->i +=1;
        }
    }
}

void tellInfo(long long int num)
{
    int index = num - SHIFT;
    if (!validAcc(num))
        printf("Invalid account number\n");
    else
    {
        printf("%s %s\n$%lld\n", accs[index]->fname, accs[index]->lname, accs[index]->balance);
    }
}

void tellLog(long long int num){
if(!validAcc(num)) printf("Invalid account number\n");
else{
    int index=num-SHIFT;
    for (int i = 9; i > -1; i--)
    {
        if(accs[index]->log[i][0][0]) printf("account created\n");
        if(accs[index]->log[i][1][0]) printf("deposit         +$%lld\n",accs[index]->log[i][1][0]);
        if(accs[index]->log[i][2][0]) printf("withdraw        -$%lld\n",accs[index]->log[i][2][0]);
        if(accs[index]->log[i][3][0]) printf("to   %lld -$%lld\n",accs[index]->log[i][3][0],accs[index]->log[i][3][1]);
        if(accs[index]->log[i][4][0]) printf("from %lld +$%lld\n",accs[index]->log[i][4][0],accs[index]->log[i][4][1]);
    }
}
}

void getinput()
{
    char c;
    int i = 0;
    while (1)
    {
        c = getchar();
        if (c == '\n')
        {
            input[i] = '\0';
            break;
        }
        else
        {
            input[i] = c;
            i++;
        }
    }
}

int getOrder(){
    int i=0;
    while(1){
if(input[i] != ' '&& input[i]!= '\0'){
    order[i]=input[i];
    i++;
    } 
    else break;  
    }
    return i;
}

int checkOrder(char *orderr, char *key, int s)
{
    int eq = 1;
    for (int i = 0; i < s; i++)
    {
        if (orderr[i] != key[i])
        {
            eq = 0;
            break;
        }
    }
    return eq;
}

// register deposit withdraw balance transfer info exit
int readInput(int i){
    int ctn = 1;
    int checkz;
     if(checkOrder(order,"exit", i) && i + 1 == sizeof("exit"))
    {
        checkz=sscanf(input,"%s %s",trash,trash2);
        if(checkz==1)
        ctn = 0;
        else printf("Invalid input\n");
    }
    else if (checkOrder(order, "register", i) && i + 1 == sizeof("register"))
    {
        //\n ro nagire bega berim
        checkz=sscanf(input,"%s %s %s %s",trash,nameinputz,lnameinputz,trash2);
        if(checkz==3) 
        makeAcc(nameinputz,lnameinputz);
        else printf("Invalid input\n");
    }
    else if (checkOrder(order, "deposit", i) && i + 1 == sizeof("deposit"))
    {
        long long int num, cash;
        checkz=sscanf(input,"%s %lld %lld %s",trash,&num, &cash,trash2);
        if(checkz==3) 
        makeDeposit(num,cash);
        else printf("Invalid input\n");
    }
    else if (checkOrder(order, "withdraw", i) && i + 1 == sizeof("withdraw"))
    {
        long long int num, cash;
        checkz=sscanf(input,"%s %lld %lld %s", trash,&num, &cash,trash2);
        if(checkz==3) 
        withdrawCash(num, cash);
        else printf("Invalid input\n");
    }
    else if (checkOrder(order, "batchreg", i) && i+1 ==sizeof("batchreg")){
        int p;
        checkz=sscanf(input,"%s %s %s %d %s",trash,nameinputz,lnameinputz,&p,trash2);
        if(checkz==4)
         makebatchreg(nameinputz,lnameinputz,p);
        else printf("Invalid input\n");
        }
    else if (checkOrder(order, "balance", i) && i + 1 == sizeof("balance"))
    {
        long long int num;
        checkz=sscanf(input,"%s %lld %s",trash,&num,trash2);
        if(checkz==2)
         tellBalance(num);
        else  printf("Invalid input\n");
    }
    else if (checkOrder(order, "transfer", i) && i + 1 == sizeof("transfer"))
    {
        long long int num1, num2, cash;
        checkz=sscanf(input,"%s %lld %lld %lld",trash ,&num1, &num2, &cash);
        if(checkz==4)
         transfer(num1, num2, cash);
        else printf("Invalid input\n");
    }
    else if (checkOrder(order, "info", i) && i + 1 == sizeof("info"))
    {
        long long int num;
        checkz=sscanf(input,"%s %lld %s",trash, &num,trash2);
        if(checkz==2) {
        tellInfo(num);
        }
        else printf("Invalid input\n");

    }
    else if (checkOrder(order, "recent", i) && i + 1 == sizeof("recent")){
        long long int num;
        checkz=sscanf(input,"%s %lld %s",trash, &num,trash2);
        if(checkz==2) {
        tellLog(num);
        }
        else printf("Invalid input\n");
    }
    else
    {
        printf("Invalid input\n");
    }
    return ctn;
}

void cleararray(char *array){
    int i=0;
    while(1){
    if(array[i] !='\0') array[i]='\0';
    else break;
}
}

int main()
{
    int go = 1, data;
    int check;

    
    while (go)
    {
        getinput();
        data = getOrder();
        go = readInput(data);
        cleararray(nameinputz);
        cleararray(lnameinputz);
        cleararray(input);
        cleararray(trash);
        cleararray(order);
        cleararray(trash2);
    }
// char dastan[100];
// strcpy(dastan,"lolol 123 v");
// check=sscanf("info 100\n","%s %d",lolz,&data);
// printf("%d\n",check);




    return 0;
}