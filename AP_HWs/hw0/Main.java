import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /*
    program to match all of fallowing phone numbers
    1234567890
    123 456 7890
    123-456-7890
    (123) 456 7890
    (123)-456-7890
    +1 123 456 7890
    +1-123-456-7890
     */

    public static void main(String[] args) {
        String txt = "{ 1234567890 }"+ "{ 123 456 7890 }"+ "{ 123-456-7890 }"+"{ (123)-456-7890 }"+"{ +1 (123)-456-7890 }";
        String phoneRegex = "\\(?(?<first>\\d{3})\\)?[ \\-]?(?<second>\\d{3})[ \\-]?(?<third>\\d{4})";
        String areaCodeRegex = "((?<areaCode>\\+1)[ \\-]?)?";
        String finalRegex = "\\{ " +areaCodeRegex+ phoneRegex + " }";

        Pattern pattern = Pattern.compile(finalRegex);
        Matcher matcher = pattern.matcher(txt);

        while(matcher.find()){
            System.out.println("############################################");
            if(matcher.group("areaCode") != null){
                System.out.println(matcher.group("areaCode"));
            }
            System.out.println(matcher.group("first"));
            System.out.println(matcher.group("second"));
            System.out.println(matcher.group("third"));
        }
    }
}
