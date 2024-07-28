import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static void run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true){
            command = scanner.nextLine();
            if((matcher = getCommandMatcher("^register u (?<username>\\S+) p (?<password>\\S+) n (?<nickname>.+)$", command)) !=  null){
                register(matcher);
            }else if((matcher = getCommandMatcher("^login u (?<username>\\S+) p (?<password>\\S+)$", command)) !=  null){
                login(matcher);
            }else System.out.println("invalid command");
        }
    }
    private static Matcher getCommandMatcher(String regex, String input){
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if(matcher.matches())
            return matcher;
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
    private static void register(Matcher matcher){
        if(!matcher.group("username").matches("^[a-zA-Z0-9_]+$")){
            System.out.println("username's format is invalid!");
        }else if(!matcher.group("nickname").matches("^[a-zA-Z ]+$")){
            System.out.println("nickname's format is invalid!");
        }else if(!matcher.group("password").matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[*.!@$%^&(){}\\[\\]:;<>,?/~_+-=|]).{8,32}$")){
            System.out.println("password is weak!");
        }
    }
    private static void login(Matcher matcher){
        if(!matcher.group("username").matches("^[a-zA-Z0-9_]+$")) {
            System.out.println("username's format is invalid!");
        }
    }
}
