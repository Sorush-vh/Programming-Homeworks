import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        User user = new User("taha7900");
        user.setFirstName("M.Taha");
        user.setLastName("Jahani-Nezhad");
        ArrayList<Integer> pipi=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            pipi.add(i);
        }
        // user.setLol(pipi);
        String result = new YourConvertor().serialize(user);
        System.out.println(result);
    }
}
