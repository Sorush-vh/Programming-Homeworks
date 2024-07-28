

import java.util.ArrayList;
import java.util.Arrays;

import model.Diary;
import model.EncryptedMessage;
import model.Gun;
import model.John;
import model.JohnWickChild;
import model.Message;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        // User user = new User("taha7900");
        // user.setFirstName("M.Taha");
        // user.setLastName("Jahani-Nezhad");
        // ArrayList<Integer> pipi=new ArrayList<>();
      
        // // user.setLol(pipi);
        // String result = new YourConvertor().serialize(user);
        // System.out.println(result);
        YourConvertor convertor=new YourConvertor();

        String json = "{\"john\":{\"name\":\"Wick\"}}";
        Object tor = convertor.deserialize(json, "model.John");

        John torr = (John) tor;
        System.out.println(torr.john.getName());


 

    }
}
