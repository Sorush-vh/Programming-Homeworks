import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hw0_q4{

    static Scanner scanner= new Scanner(System.in);

    // static String word="(\"(?<quotew>[^\"]*)\")|(?<normalw>[^\n\\s])";

    static class Orders{
        public boolean OptionArg=false;
        public int filemode=0;
        public int commandnumber;
        public String command;
        public String subcommand=null;
        public ArrayList<String[]> options;
        public ArrayList<String> flags;
        public ArrayList<String> arguements;
        public ArrayList<Orders> inputCommands;
        public String relatedFile;

        public static int totalOrders;
        public static ArrayList<Orders> pipedOrders;
        public static ArrayList<Orders> allComands;

        static{
            totalOrders=0;
            allComands= new ArrayList<Orders>();
            pipedOrders= new  ArrayList<Orders>();
        }

        private Orders(String command){
            this.options=new ArrayList<String[]>();
            this.flags=new ArrayList<String>();
            this.arguements=new ArrayList<String>();
            this.inputCommands=new ArrayList<Orders>();

            this.command=command;
            Orders.totalOrders++;
            this.commandnumber=Orders.totalOrders;
            Orders.allComands.add(this);
        }
    }

    static class Files{
        public String fileName;
        public String fileData="";
        public static ArrayList <Files> allFiles;
        static{
            allFiles= new ArrayList<Files>();
        }
        private  Files(String name){
            this.fileName=name;
            Files.allFiles.add(this);
        }

        public static Files getFilebyName(String name){
            for (Files file : allFiles) {
                if (file.fileName.equals(name)) {
                    return file;
                }
            }
            return null;
        }
    }


    public static void main(String[] args){

        String input="";
        input=readInput(input);

        
        // System.out.println("=====");
        // System.out.println(input);
        // System.out.println("====");
        //reading an entire order
        String untillAFinisher="(?<order>[^\\;\\|]*)"+"(?<ender>[\\;\\|])";
        Pattern pattern=Pattern.compile(untillAFinisher);
        Matcher matcher=pattern.matcher(input);

        int quoteChecker=0;
        String order="",temp;
        while (matcher.find()) {
            temp=matcher.group("order")+matcher.group("ender");
            order=order.concat(temp);
            quoteChecker=(quoteChecker+countOccurences(temp, '\"'))%2;
            if(quoteChecker==0){
                readorder(order);
                order="";
            }
            else{
                continue;
            }
            
        }


        // Pattern pattern1=Pattern.compile(word);
        // Matcher matcher1=pattern1.matcher(input);
        // System.out.println(matcher1.find()); 
        // System.out.println(matcher1.group("quotew"));
    }



    public static void readorder(String FullOrder){
    //empty nabashe quote ha
    // handele exit ya bedone arg ha

         String word="[\\s\n]*"+"(\"(?<quotew>[^\"]*)\")|(?<normalw>[^\n\\s\"]+)";

         Pattern pattern=Pattern.compile(word);
         Matcher matcher=pattern.matcher(FullOrder);

         matcher.find();
         String commandName=matcher.group("normalw");
         commandName=trimIfNeeded(commandName, matcher);
         if(commandName.length()==0) return ;
         Orders newOrder= new Orders(commandName);
         if(commandName.equals("exit;")) return ;

         if(matcher.find()){ 
         String nextInput=parseNextWord(matcher);
         String exe=trimIfNeeded(nextInput,matcher);
         if(isSub(exe, matcher)){
            newOrder.subcommand=exe;
         }
         else{
            handleIndex(exe, newOrder, matcher);
            handleEnd(nextInput, newOrder);
         }
        }String nextInput;
        String exe;
         
         while (matcher.find()) {
            nextInput=parseNextWord(matcher);
            exe=trimIfNeeded(nextInput,matcher);
            handleIndex(exe, newOrder, matcher);
            handleEnd(nextInput, newOrder);
         }
         executeOrder(newOrder);

    }

    public static String readInput(String storage){
        String lineInput;
        String endFormat="(.*\\s+)?"+"exit"+"\\s*"+"\\;"+"\\s*";
        Pattern patternEnd=Pattern.compile(endFormat);

        int quoteChecker=0;

        while (true) {
            lineInput=scanner.nextLine();
            storage=storage.concat(lineInput+"\n");


            quoteChecker=(quoteChecker+countOccurences(lineInput, '\"') )%2;
            if(quoteChecker==0){
                Matcher matcher=patternEnd.matcher(lineInput);
                if (matcher.matches()) {
                    return storage;
                }
            }
        }
    }

    public static int countOccurences(String target,char c){
        int ans=0;
        for (int i = 0; i < target.length(); i++) {
            if(target.charAt(i)==c) ans++;
        }
        return ans;
    }

    public static void handleIndex(String word, Orders order, Matcher matcher){

        if (word.equals(">") || word.equals(">>")){
            matcher.find();
            Boolean check=true;
            String fileName="\""+matcher.group("quotew")+"\"";
            for (Files file : Files.allFiles) {
                if(file.fileName.equals(fileName)){
                    check=false;
                    break;
                }
            }
            if (check) {
                Files newfile= new Files(fileName);
            }

            if(word.equals(">")){
            order.filemode=1;
            }
            else{
                order.filemode=2;
            }
            order.relatedFile=fileName;
            order.OptionArg=false;
        }

        else if (word.startsWith("-")&&!word.startsWith("--")) {
            for (int i = 1; i < word.length(); i++) {
                String[] optArr= new String[2];
                optArr[0]=""+word.charAt(i);
                optArr[1]=null;
                order.options.add(optArr);
            }
            order.OptionArg=true;
            
        }
        else if(word.startsWith("--")){
            String flag= word.substring(2);
            order.flags.add(flag);
           order.OptionArg=false;
        }
        else if(!word.equals("|")&&!word.equals(";")&&word.length()!=0){
            order.arguements.add(word);
            if (order.OptionArg) {
                order.options.get(order.options.size()-1)[1]=word;
            }
            order.OptionArg=false;
        }
    }

    public static String parseNextWord(Matcher matcher){
        if (matcher.group("quotew")!=null) {
            return "\""+matcher.group("quotew")+"\"";
        }
        else{
            return matcher.group("normalw");
        }
        
    }

    public static boolean isSub(String word,Matcher matcher){
        if(matcher.group("quotew")!=null) return false;
        else if (word.startsWith("-") || word.startsWith("--")) return false;
        else if (word.equals(">") || word.equals(">>")) return false;
        else if(word.length()==0) return false;
        return true;
    }

    public static String trimIfNeeded(String word , Matcher matcher){
        if(matcher.group("quotew")!=null) return word;
        if(word.charAt(word.length()-1)=='|'||word.charAt(word.length()-1)==';'){
            String correct="";
            for (int i = 0; i < word.length()-1; i++) {
                correct=correct.concat(""+word.charAt(i));
            }
            return correct;
        }
        return word;
    }

    public static boolean handleEnd(String word, Orders order){
        if(word.length()==0) return false;
        if(word.charAt(word.length()-1)=='|'){
            Orders.pipedOrders.add(order);
            return true;
        }
        else return false;

    }

    public static void executeOrder(Orders order){
        String output="";
        if(order.command.equals("exit")) return;
        output=output.concat("command "+order.commandnumber+"\n");
        System.out.println("command "+order.commandnumber);
        output=output.concat("command: "+order.command+"\n");
        System.out.println("command: "+order.command);


        if(order.subcommand!=null) {
            System.out.println("subcommand: "+order.subcommand);
            output=output.concat("subcommand: "+order.subcommand+"\n");
        }

        for (int i = 0; i < order.options.size(); i++) {
            System.out.print("option "+(i+1)+": "+order.options.get(i)[0]);
            output=output.concat("option "+(i+1)+": "+order.options.get(i)[0]);
            if (order.options.get(i)[1]!=null) {
                System.out.print(" = "+order.options.get(i)[1]);
                output=output.concat(" = "+order.options.get(i)[1]);
            }
            System.out.print("\n");
            output=output.concat("\n");
        }
        for (int i = 0; i < order.flags.size(); i++) {
            System.out.println("flag "+(i+1)+": "+order.flags.get(i));
            output=output.concat("flag "+(i+1)+": "+order.flags.get(i)+"\n");
        }
        for (int i = 0; i < order.arguements.size(); i++) {
            System.out.println("argument "+(i+1)+": "+order.arguements.get(i));
            output=output.concat("argument "+(i+1)+": "+order.arguements.get(i)+"\n");
        }



        output=output.concat(handlePipe(order));
        System.out.println("");
        output=output.concat("\n");

        handleFile(order, output);
        
        if (order.command.equals("cat")) {
        printFile(order.arguements.get(0));
        }
    }

    public static String handlePipe(Orders order){
        String info="";
        int i=1;
        for (Orders tempOrder : Orders.pipedOrders) {
            if (tempOrder.commandnumber!=order.commandnumber) {
                System.out.println("input "+i+": command "+tempOrder.commandnumber);
                info=info.concat("input "+i+": command "+tempOrder.commandnumber+"\n");
            }
            i++;
        }
        if(Orders.pipedOrders.size()>0)
        if(Orders.pipedOrders.get(Orders.pipedOrders.size()-1).commandnumber!=order.commandnumber){
            Orders.pipedOrders.clear();
        }
        return info;

    }

    public static void handleFile(Orders order,String output){

        if (order.filemode==0) {
            return ;
        }



        Files targetFile= Files.getFilebyName(order.relatedFile);

        if (order.filemode==2) {
            // if (targetFile.fileData.length()>0) {
            //     targetFile.fileData=targetFile.fileData.concat("\n");
            // }
           targetFile.fileData= targetFile.fileData.concat(output);
           return ;
        }
         targetFile.fileData=output;
    }

    public static void printFile(String fileName){
        Files target=Files.getFilebyName(fileName);
        System.out.print(target.fileData);
    }
}