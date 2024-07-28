import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simple {
    public static void main(String[] args) {
        String txt = "1234 fasdf 45435";
        String regex = "\\d+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txt);

        if(matcher.find()){
            System.out.println(matcher.start() +" "+ matcher.end());
        }else System.out.println("no find");
        if(matcher.find()){
            System.out.println(matcher.start() + " "+ matcher.end());
        }else System.out.println("no find");
        if(matcher.matches()){
            System.out.println(matcher.start() +" " + matcher.end());
        }else System.out.println("no match");
        if(matcher.lookingAt()){
            System.out.println(matcher.start() +" " + matcher.end());
        }else System.out.println("no looking at");

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        txt = "name: mamadd password: 1234 cvv2: 404";
        regex= "name: (?<name>\\S+) password: (?<pass>\\d{4}) cvv2: (?<cvv2>\\d{3})";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(txt);
        if(matcher.matches()){
            String name = matcher.group("name");
            System.out.println(name);
            if(!name.matches("\\w+")) System.out.println("bad name");
            System.out.println(matcher.group("pass"));
            System.out.println(matcher.group("cvv2"));
        }

    }
}
