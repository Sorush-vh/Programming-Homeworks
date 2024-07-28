import java.util.Scanner;

public class hw0_q2{
    static Scanner input= new Scanner(System.in);

    public static void main(String[] args){
        int length;
        int[] data= new int[200009];
        int testCount= input.nextInt();

        for (int i = 0; i < testCount; i++) {
            length=input.nextInt();
            readTestCase(data, length);

            if( checkPalindrome(data, length) ) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean checkPalindrome(int[] array,int length){
        int firstBug=0,secondBug=0,start=0;
        boolean check=true;

        for (int i = 0; i < length/2; i++) {
            if(array[i] != array [length-1-i]) {
                start=i;
                secondBug=array[i];
                firstBug=array[length-1-i];
                check=false;
                break;
            }
        }
        if (check) {
            return true;
        }

        int[] firstEdit=new int[200009];
        boolean secondcheck=true;
        int lengthPrime=buildWithExeptions(array, firstBug, firstEdit,length,start);


            for (int i = 0; i < lengthPrime/2; i++) {
                if(firstEdit[i] != firstEdit [lengthPrime-1-i]) {
                    secondcheck=false;
                    break;
                }
            }
            if(secondcheck) return true;

        int[] secondEdit=new int[200009];
        lengthPrime=buildWithExeptions(array, secondBug, secondEdit,length,start);

        for (int i = 0; i < lengthPrime/2; i++) {
            if(secondEdit[i] != secondEdit [lengthPrime-1-i]) {
                return false;
            }
        }
        return true; 
    }


    static int buildWithExeptions(int[] source, int exception , int[] target, int length,int start){
        int j=0;
        for (int i = start; i < length-start; i++) {
            if(source[i] != exception) {
                target[j]=source[i];
                j++;
            }
        }
        return j;
    }

    static void readTestCase(int[] array, int length){
        for (int j = 0; j < length; j++) {
            array[j]=input.nextInt();
        }
    }

}