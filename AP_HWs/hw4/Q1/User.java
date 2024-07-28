import java.util.ArrayList;

public class User {
    // private static ArrayList<User> allUsers = new ArrayList<>();

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private boolean isSuperUser = false;
    private char colorCode = 'G';
    private Card card;
    // private ArrayList<Integer> lol;

    public User(String username) {
        this.username = username;
        this.card = new Card(this);
    }

    // public void setLol(ArrayList<Integer> lol){
    //     this.lol=lol;
    // }

    // public ArrayList<Integer> getLol(){
    //     return lol;
    // }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
