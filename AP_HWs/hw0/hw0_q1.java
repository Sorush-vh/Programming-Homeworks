import java.util.ArrayList;
import java.util.Scanner;

public class hw0_q1 {

static int checkClone(ArrayList<String> rank, String word){
for (String check : rank)
    if(word.equals(check)) return 1;

    return 0;
}

static int isRepeated(ArrayList<ArrayList<String>> source, String word){
    for (int i = 0; i < source.size(); i++) {
        if(isSimiliar( source.get(i).get(0), word)==1) {
            if(checkClone(source.get(i), word)==0)
            source.get(i).add(word);
            return 1;
        }
    }
    ArrayList<String> temp= new ArrayList<String>();
    temp.add(word);
    source.add(temp);
    return 0;
}

static  int isSimiliar(String s1, String s2) {
        String cloneWord=s2;
        //beshe ya nashe
        if (s1.length() != s2.length())
            return 0;
            int check=0;


        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
                if (s1.charAt(i) == cloneWord.charAt(j)) {
                    String c=""+cloneWord.charAt(j);
                    check=1;
                    cloneWord=cloneWord.replaceFirst(c, "");
                    break;
                }
            }
            if(check==1){
                check=0;
                break;
            }
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
    String a1,a2;
    a1="aaa";
    a2="aab";
            System.out.println(a1.compareTo(a2));
        Scanner inputs = new Scanner(System.in);
        System.out.println(Integer.parseInt("-51")*2);
        String data = inputs.nextLine();
        String[] eachWord = data.split("-");
        ArrayList <ArrayList<String>> groups= new ArrayList<ArrayList<String>>();
        
        for (String dastan : eachWord) {
            isRepeated(groups,dastan);
        }
        for (ArrayList<String> arrayList : groups) {
            for (String word : arrayList) {
                System.out.print("*"+word+"*"+" ");
            }
            System.out.print("\n");
        }
    }

}