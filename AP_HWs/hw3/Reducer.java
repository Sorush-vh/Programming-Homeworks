

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Reducer extends Thread {
    public static class WordCountPair {
        private String word;
        private Integer count;
        public WordCountPair(String word, Integer count) {
            this.word = word;
            this.count = count;
        }
        public String getWord() {
            return word;
        }
        public Integer getCount() {
            return count;
        }
    }
    private LinkedBlockingQueue<WordCountPair> input;
    public Reducer(LinkedBlockingQueue<WordCountPair> input){
        this.input=input;
    }

    public HashMap<String, Integer> getWordsFrequency() {
        HashMap<String,Integer> result = new HashMap<>();
        while (input.size()>0){
            WordCountPair wordCountPair= input.poll();
            if (result.containsKey(wordCountPair.getWord())){
                result.computeIfPresent(wordCountPair.getWord(), (k, v) -> (v + wordCountPair.getCount()));
            }
            else {
                result.put(wordCountPair.getWord(),wordCountPair.getCount());
            }
        }
        return result;
    }
}

