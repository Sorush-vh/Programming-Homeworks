import java.util.ArrayList;
import java.util.Scanner;

public class determinan {

    public static Scanner Scanner=new Scanner(System.in);

    public static void main(String[] args) {
        String firstLine=Scanner.nextLine();
        int matriceLength=Integer.parseInt(firstLine);
        int[][] matrice=getInputMatrice(matriceLength);
        System.out.println(calculateMatriceDet(matrice, matriceLength));
    }


    public static int[][] getsubMatriceByIndex(int[][] matrice, int matriceLength, int xOfIndex){

        int[][] newMatrice=new int[matriceLength-1][matriceLength-1];
        int newI=0,newJ=0;
        
        for (int i = 1; i < matriceLength; i++){ 
            for (int j = 0; j < matriceLength; j++) {

                if(j==xOfIndex) continue;
                newMatrice[newI][newJ]=matrice[i][j];
                newJ++;
            }
            newI++;
            newJ=0;
        }
        return newMatrice;
    }

    public static int calculateMatriceDet(int[][] matrice, int matriceLength){

        if(matriceLength==2) return calculate2by2Det(matrice);

        int detValue=0;
        for (int i = 0; i < matriceLength; i++) {
            int[][] newMatrice=getsubMatriceByIndex(matrice, matriceLength, i);
            detValue+=matrice[0][i]*(int) Math.pow(-1, i%2) *calculateMatriceDet(newMatrice, matriceLength-1);
        }
        return detValue;
    }

    public static int[][] getInputMatrice(int matriceLength){
        String enteredLine;
        int[][] matrice=new int[matriceLength][matriceLength];

        for (int i = 0; i < matriceLength; i++) {

            enteredLine=Scanner.nextLine();
            String[] lineIndexesInString=enteredLine.split(" "); 

            for (int j = 0; j < matriceLength; j++) 
                matrice[i][j]=Integer.parseInt(lineIndexesInString[j]);
        }
        return matrice;
    }

    public static int calculate2by2Det(int[][] matrice){
        return matrice[0][0]*matrice[1][1]-matrice[0][1]*matrice[1][0];
    }
}
