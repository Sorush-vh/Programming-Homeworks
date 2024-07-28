#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdarg.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

void put_content_in_string(char *file_name, char *hold);
int isDirectoryExists(const char *path);
FILE *make_directory(char *file_stats);

int main(){
char filenameorg[500]={0};
char filename[500]={0};
char newfilename[500]={0};
char content[10000]={0};
char hcontent[10000]={0};

int n;
char c;
scanf("%d\n",&n);
for (int i = 0; i < n; i++)
{
    gets(filenameorg);
    strcpy(filename,filenameorg);
    strcat(filename,".txt");
    strcat(content,"<!DOCTYPE html>\n");
    put_content_in_string(filename,hcontent);
    strcat(content,hcontent);
    strcpy(newfilename,filenameorg);
    strcat(newfilename,".html");

    FILE* file_ad=fopen(newfilename,"w");
    fputs(content,file_ad);
    fclose(file_ad);

    memset(filename,0,strlen(filename));
    memset(newfilename,0,strlen(newfilename));
    memset(filenameorg,0,strlen(filenameorg));
    memset(content,0,strlen(content));
    memset(hcontent,0,strlen(hcontent));
    getchar();
}


return 0;
}


FILE *make_directory(char *file_stats)
{
    FILE *address;
    int count = 0;
    char c;
    char hold[1000] = {'\0'};
    int i = 1;
    while (1)
    {
        while (1)
        {
            c = file_stats[i];
            if (c == 47)
            {
                count++;
                break;
            }
            hold[i - 1] = c;
            i++;
            if (i == strlen(file_stats))
            {
                count++;
                break;
            }
        }
        // if (count == 1)
        // {
        //     if (strcmp("root", hold) || file_stats[0] != 47)
        //     {
        //         printf("Error: not started from root");
        //         return 0;
        //     }
        // }
        // if (!isDirectoryExists(hold) && i != strlen(file_stats))
        // {
        //     CreateDirectory(hold, NULL);
        // }
        if (i == strlen(file_stats))
        {
            address = (FILE *)fopen(hold, "w");
            if (!address)
                perror("fopen");
            return address;
        }
        hold[i - 1] = c;
        i++;
    }
}

int isDirectoryExists(const char *path)
{
    struct stat stats;

    stat(path, &stats);

    // Check for file existence
    if (S_ISDIR(stats.st_mode))
        return 1;

    return 0;
}







void put_content_in_string(char *file_name, char *hold)
{
    memset(hold,0,strlen(hold));
    FILE *file_ad = fopen(file_name, "r");
    if (!file_ad)
    {
        perror("fopen");
        return;
    }
    int i = 0;
    char c;
    while (1)
    {
        c = fgetc(file_ad);
        if (c == EOF || c=='\0')
            break;
        hold[i] = c;
        i++;
    }
    fclose(file_ad);
    rewind(file_ad);
}