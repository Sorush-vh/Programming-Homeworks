

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class Master {
    private String input;
    public HashMap<String,Integer> all= new HashMap<>();
    public Master(String input) throws InterruptedException{
        this.input= input;
    }
    public static LinkedBlockingQueue<Reducer.WordCountPair> queue0=new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<Reducer.WordCountPair> queue1=new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<Reducer.WordCountPair> queue2=new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<Reducer.WordCountPair> queue3=new LinkedBlockingQueue<>();
    public HashMap<String, Integer> getWordsFrequency() throws InterruptedException {
        String[] strings = input.split("\n");
        LinkedBlockingQueue<Reducer.WordCountPair  > blockingQueue = new LinkedBlockingQueue<>();
        ArrayList <Mapper> mappers = new ArrayList<>();
        for (int i=0; i< strings.length;i++){
            if (strings[i]!=null){
                Mapper mapper = new Mapper(strings[i]);
                mappers.add(mapper);
                mapper.start();
            }
        }
        for (int i=0; i< mappers.size();i++){
            mappers.get(i).join();
            for (Map.Entry <String,Integer> entry: mappers.get(i).getWordsFrequency().entrySet()){
               // System.out.println(entry.getKey()+"++"+entry.getValue()+"++++");
                //System.out.println(entry.hashCode());
                if (entry.hashCode()==1){
                    break;
                }
                if (entry.hashCode()%4==0){
                    Reducer.WordCountPair wordCountPair = new Reducer.WordCountPair(entry.getKey(),entry.getValue());
                    queue0.add(wordCountPair);
                    //System.out.println(wordCountPair.getWord()+wordCountPair.getCount()+"---");
                }
                else if (entry.hashCode()%4==1){
                    Reducer.WordCountPair wordCountPair = new Reducer.WordCountPair(entry.getKey(),entry.getValue());
                    queue1.add(wordCountPair);
                  //  System.out.println(wordCountPair.getWord()+wordCountPair.getCount()+"---+");
                }
                else if (entry.hashCode()%4==2){
                    Reducer.WordCountPair wordCountPair = new Reducer.WordCountPair(entry.getKey(),entry.getValue());
                    queue2.add(wordCountPair);
                //    System.out.println(wordCountPair.getWord()+wordCountPair.getCount()+"---");
                }
                else {
                    Reducer.WordCountPair wordCountPair = new Reducer.WordCountPair(entry.getKey(),entry.getValue());
                    queue3.add(wordCountPair);
                 //   System.out.println(wordCountPair.getWord()+wordCountPair.getCount()+"---");
                }
            }
        }
        ArrayList<Reducer> reducers= new ArrayList<>();
        Reducer reducer0=new Reducer(queue0);

        reducers.add(reducer0);
        reducer0.start();
        Reducer reducer1=new Reducer(queue1);
        reducers.add(reducer1);
        reducer1.start();
        Reducer reducer2=new Reducer(queue2);
        reducers.add(reducer2);
        reducer2.start();
        Reducer reducer3=new Reducer(queue3);
        reducers.add(reducer3);
        reducer3.start();
        //System.out.println(queue3+"qq");
        reducer0.join();
        reducer1.join();
        reducer2.join();
        reducer3.join();
        HashMap<String,Integer> end= new HashMap<>();
        end.putAll(reducer0.getWordsFrequency());
        end.putAll(reducer1.getWordsFrequency());
        end.putAll(reducer2.getWordsFrequency());
        end.putAll(reducer3.getWordsFrequency());
        return end;}
}
