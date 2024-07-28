import java.util.Scanner;
import java.util.*;

public class hw0_q3{

    static Scanner input= new Scanner(System.in);
    static boolean keepGoing=true;
    static int n;
    static int[][] map=new int[11][11];
    static HashSet<Integer> fixxed=new HashSet<Integer>();
    static int max;
    static int[] destination= new int[2];
    static int[][] neighborzz=new int[6][2];
    public static void main(String[] args){

        n=input.nextInt();
        int[] start= new int[2];
        fillMap(map,n,start);
        max=findMax();
        setLocation(max, destination, n);
        findPath( 1, start);
    }
    

    static void findPath(int step,  int[] currentLoc){
        if(!keepGoing) return;
        if(step==max){
            printMap(map,n);
            keepGoing=false;
            return ;
        } 

        int left=max-step;
        int di=currentLoc[0]-destination[0];
        int dj=currentLoc[1]-destination[1];
        int distance;
        if(di<0) di=-di;
        if(dj<0) dj=-dj;
        if(di>=dj) distance=di;
        else distance=dj;

        if(distance>left) return;


        int[] nextLoc=new int[2];
        int[] saveLoc=new int[2];

        saveLoc[0]=currentLoc[0];
        saveLoc[1]=currentLoc[1];

        findNeighbors( step, saveLoc, n, neighborzz);
        map[currentLoc[0]][currentLoc[1]]=step;

        if(isFixed(step+1)){
            for (int[] neighbor : neighborzz) {
                if(neighbor[0]>-1)
                if (step+1==map[neighbor[0]][neighbor[1]]) {
                    nextLoc[0] =neighbor[0];
                    nextLoc[1]=neighbor[1];
                    findPath( step+1,nextLoc);
                }
            }
            return ;
        }




        int restoration;
        for (int i = 0; i < 6; i++) {
            if( neighborzz[i][0] !=-1 ){
                nextLoc[0]=neighborzz[i][0];
                nextLoc[1]=neighborzz[i][1];
                restoration=map[nextLoc[0]][nextLoc[1]];
                findPath( step+1, nextLoc);
                map[nextLoc[0]][nextLoc[1]]=restoration;
                findNeighbors(step,saveLoc,n,neighborzz);
            }
        }
        return ;
    }

    static void printMap(int[][] map,int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n+i; j++) {
                System.out.print(""+map[i][j]+" ");
            }
            System.out.print("\n");
        }
        int i;
        int s;
        for ( i =n,s=n-2 ; i < 2*n-1; i++,s--) {
            for (int j = 0; j < n+s; j++) {
                System.out.print(""+map[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    static boolean isFixed(int a){
        if (fixxed.contains(a)) {
            return true;
        }
        return false;
    }

    static void findNeighbors(int step, int[] currentLoc, int n,int[][] neighbors){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                neighbors[i][j]=-1;
            }
        }

        int satr=currentLoc[0],sotun=currentLoc[1];
        if( sotun-1>-1  ) 
            if( map[satr][sotun-1]==0 || map[satr][sotun-1]==step+1 ) {
            neighbors[2][0]=satr;
            neighbors[2][1]=sotun-1;
        }
        if( sotun+1 < 2*n-1 ) 
            if( map[satr][sotun+1]==0 || map[satr][sotun+1]==step+1 ){
            neighbors[0][0]=satr;
            neighbors[0][1]=sotun+1;
        }
        if(satr<n-1){
                if( satr-1>-1 && sotun-1>-1  ) 
                    if( map[satr-1][sotun-1]==0 || map[satr-1][sotun-1]==step+1 ){
                neighbors[1][0]=satr-1;
                neighbors[1][1]=sotun-1;
                }
                if( satr-1>-1  )
                    if( map[satr-1][sotun]==0 || map[satr-1][sotun]==step+1) {
                    neighbors[4][0]=satr-1;
                    neighbors[4][1]=sotun;
                }
                if(  map[satr+1][sotun]==0 || map[satr+1][sotun]==step+1 ) {
                    neighbors[5][0]=satr+1;
                    neighbors[5][1]=sotun;
                }
                if(sotun+1<2*n-1)
                    if( map[satr+1][sotun+1]==0  || map[satr+1][sotun+1]==step+1) {
                    neighbors[3][0]=satr+1;
                    neighbors[3][1]=sotun+1;
                }
        }

        else if(satr>n-1){
            if( sotun+1<2*n-1  ) 
            if( map[satr-1][sotun+1]==0 || map[satr-1][sotun+1]==step+1 ) {
            neighbors[1][0]=satr-1;
            neighbors[1][1]=sotun+1;
            }
            if( map[satr-1][sotun]==0 || map[satr-1][sotun]==step+1) {
                neighbors[4][0]=satr-1;
                neighbors[4][1]=sotun;
            }
            if( satr+1<2*n-1  )
                if( map[satr+1][sotun]==0 || map[satr+1][sotun]==step+1 ) {
                neighbors[5][0]=satr+1;
                neighbors[5][1]=sotun;
            }
            if( satr+1<2*n-1 && sotun-1>-1 ) 
                if( map[satr+1][sotun-1]==0 || map[satr+1][sotun-1]==step+1 ) {
                neighbors[3][0]=satr+1;
                neighbors[3][1]=sotun-1;
            }
    }
    else{

        if( sotun-1>-1 )
            if( map[satr-1][sotun-1]==0 || map[satr-1][sotun-1]==step+1 ) {
            neighbors[1][0]=satr-1;
            neighbors[1][1]=sotun-1;
            }
            if( map[satr-1][sotun]==0 || map[satr-1][sotun]==step+1) {
                neighbors[4][0]=satr-1;
                neighbors[4][1]=sotun;
            }
            if(  map[satr+1][sotun]==0 || map[satr+1][sotun]==step+1) {
                neighbors[5][0]=satr+1;
                neighbors[5][1]=sotun;
            }
            if( sotun-1>-1 )
                if( map[satr+1][sotun-1]==0 || map[satr+1][sotun-1]==step+1 ) {
                neighbors[3][0]=satr+1;
                neighbors[3][1]=sotun-1;
            }
    }
    }

    static void setLocation(int val, int loc[], int n){
        for (int i = 0; i < 2*n-1; i++) {
            for (int j = 0; j < 2*n-1; j++) {
                if (val==map[i][j]) {
                    loc[0]=i;
                    loc[1]=j;
                    return ;
                }
            }
        }

    }

    static int findMax(){
        int m=0;
        for (Integer val : fixxed) {
                if(val>m) m=val;
        }
        return m;
    }

    static void fillMap(int[][] map,int n,int[] start){
        for (int i = 0; i < 2*n-1 ; i++) {
            for (int j = 0; j < 2*n-1; j++) {
                map[i][j]=-1;
            }
        }
        int i;
        for ( i = 0; i < n; i++) {
            for (int j = 0; j < n+i; j++) {
                map[i][j]=input.nextInt();

                if(map[i][j]>0){
                    fixxed.add(map[i][j]);
                    if(map[i][j]==1) {
                        start[0]=i;
                        start[1]=j;
                    }
                }
            }
        }

        int s;
        for ( i =n,s=n-2 ; i < 2*n-1; i++,s--) {
            for (int j = 0; j < n+s; j++) {
                map[i][j]=input.nextInt();

                if(map[i][j]>0){
                    fixxed.add(map[i][j]);
                    if(map[i][j]==1) {
                        start[0]=i;
                        start[1]=j;
                    }
                }
            }
        }
    }
}