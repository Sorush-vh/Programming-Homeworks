

import java.util.ArrayList;
import java.util.HashMap;

public class Mapper extends Thread {
    private String input;

    HashMap<String, Integer> result=new HashMap<>();
    public Mapper(String input) {
        this.input=input;
    }
    @Override
    public void run(){
        System.out.println("ssss");

        String[] strings = input.split(" ");
        for (int i=0; i<strings.length;i++){
            if (strings[i]!=null){
                if (this.result.containsKey(strings[i])){
                    this.result.computeIfPresent(strings[i], (k, v) -> v + 1);
                }
                else {
                    this.result.put(strings[i],1);
                }
            }
        }
    }
    public HashMap<String, Integer> getWordsFrequency() {

        System.out.println(result.containsKey("world"));
        return this.result;
    }
}