import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class hw0_q5{

    static class Costumers{
        public String name;
        public int id;
        public int balance=0;
        public static ArrayList<Costumers> allCostumers;
        public ArrayList<Items> shoppingCart;


        static{
            allCostumers=new ArrayList<Costumers>();
        }

        private Costumers( String name, int id){
            for (Costumers costumer : allCostumers) {
                if(costumer.id==id) {
                    System.out.println("The id already exists");
                    return ;
                }
            }
            this.id=id;
            this.name=name;
            this.shoppingCart=new ArrayList<Items>();
            allCostumers.add(this);
        }

        public static Costumers getCostumerById(int id){
            for (Costumers costumer : Costumers.allCostumers) {
                if(costumer.id==id) return costumer;
            }
            return null;
        }
    }

    public static ArrayList<String> Categories=new ArrayList<String>();

    public static void addCostumers(String info){
        String idPattern="(?<id>\\d+)";
        String namePattern="(?<name>[^\\s]+)";
        String formula="\\s*"+idPattern+"\\s+"+namePattern+"\\s*";

        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);

        if (!matcher.matches()) {
            System.out.println("Invalid command");
            return ;
        }

        int id=Integer.parseInt(matcher.group("id"));
        String name=matcher.group("name");
        Costumers temp=new Costumers(name, id);
    }

    public static void addCategory(String name){
            //regex ruye esm
            String formula="\\s*"+"(?<first>[a-zA-Z]+)(?<second>\\d*)"+"\\s*";
            String check=".*"+"(?<dul>[^a-zA-Z\\d\\s])"+".*";
            // String formula="(?<first> ([a-zA-Z]+)(\\d*) )";

            Pattern pattern=Pattern.compile(formula);
            Matcher matcher=pattern.matcher(name);

            Pattern patternd=Pattern.compile(check);
            Matcher matcherd=patternd.matcher(name);
            
            String formula2="\\s*"+"(?<start>[^0-9]*)"+"(\\d)+"+"([^0-9])+"+"\\s*";
            Pattern pattern2=Pattern.compile(formula2);
            Matcher matcher2=pattern2.matcher(name);

            String formula3="\\s*"+"([^\\s])+"+"\\s+"+"([^\\s]+)";
            Pattern pattern3=Pattern.compile(formula3);
            Matcher matcher3=pattern3.matcher(name);

            String formula4="\\s*"+"(?<first>[a-zA-Z]+)(?<second>\\d*)"+"\\s*";
            Pattern pattern4=Pattern.compile(formula4);
            Matcher matcher4=pattern4.matcher(name);

        //     if (matcherd.matches()) {
        //         System.out.println("kir");
        //         System.out.println("Category format is invalid");
        //         return ;
        //     }

        //     if((matcher2.find()) || matcher3.matches() ){
        //         System.out.println("Invalid command");
        //         return ;
        //     }


        //    if(!matcher.matches()){
        //     System.out.println("Category format is invalid");
        //     return ;
        //    }
        //    if (!matcher4.matches()) {
        //     System.out.println("Invalid command");
        //         return ;
        //    }

        if (!matcher.matches()) {
            if (matcher3.matches()) {
                System.out.println("Invalid command");
                return;
            }
            if (matcherd.matches()) {
                System.out.println("Category format is invalid");
                return ;
            }
            System.out.println("Invalid command");
            return ;


        }




           String categoryName=matcher.group("first")+matcher.group("second");

        for (String category: Categories) {
            if(category.equals(categoryName)){
                System.out.println("The category already exists");
                return ;
            }
        }
        Categories.add(categoryName);
    }

    static class Items{
      public String name;
      public String category;
      public int price;
      public int id;
      public int count;
      public double rate=0;
      public int rateCount=0;
      public ArrayList<String> details;
      public ArrayList<String> comments;
      public static ArrayList<Items> allItems;

      static{
        allItems=new ArrayList<Items>();
      }

      public void addDetail(String detail){
        details.add(detail);
      }
      public void addComent(String comment){
        comments.add(comment);
      }

      public static Items getItembyId(int id){
        for (Items item : Items.allItems) {
            if (item.id==id) {
                return item;
            }
        }
        return null;
      }

      private Items(String name, String category, int price, int id){
        this.category=category;
        this.name=name;
        this.price=price;
        this.id=id;
        this.details=new ArrayList<String>();
        this.comments=new ArrayList<String>();
      }
    }

    public static void addItem(String info){
        String idPattern="(?<id>\\d+)";
        // String categoryPattern="(?<firstc>[a-zA-Z]+)(?<secondc>\\d*)";
        String categoryPattern="(?<category>[^\\s]+)";
        String namePattern="(?<name>[^\\s]+)";
        String pricePattern="(?<price>\\d+)";
        String detailsPattern="(\\?detail (?<detailz>.*))?";
        
        String formula="\\s*"+idPattern+"\\s+"+categoryPattern+"\\s+"+namePattern+"\\s+"+pricePattern+"\\s*"+detailsPattern;
        
        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);

        if (!matcher.matches()) {
            System.out.println("Invalid command");
            return ;
        }

        String itemName=matcher.group("name");
        String itemPrice=matcher.group("price");
        String itemId=matcher.group("id");
        //inja check kon null nazare
        // String itemCategory=matcher.group("firstc")+matcher.group("secondc");
        String itemCategory=matcher.group("category");

        int intPrice=Integer.parseInt(itemPrice);
        int intId=Integer.parseInt(itemId);
        
        for (Items item : Items.allItems) {
            if(item.id==intId){
                System.out.println("The id already exists");
                return ;
            }
        }
        boolean validCategory=false;
        for (String category : Categories) {
            if(category.equals(itemCategory)){
                validCategory=true;
                break;
            }
        }
        if(!validCategory){
            System.out.println("The category does not exist");
            return;
        }

        Items temp= new Items(itemName,itemCategory,intPrice,intId);
        Items.allItems.add(temp);

        
        if(matcher.group("detailz") != null ){
        String detailz=matcher.group("detailz");
        detailAdder(temp, detailz);
        }

    }

    public static void detailAdder(Items item, String details){
        String formula="\\{"+"(?<detail>[^\\}]*)"+"\\}";
        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(details);
        while (matcher.find()) {
            String output=matcher.group("detail");
            item.addDetail(output);
        }
    }

    public static void addToCart(String info){
        String idPattern="(?<id>\\d+)";
        String itemsIdPattern="(?<itemsId>.+)";
        String formula="\\s*"+idPattern+"\\s+"+itemsIdPattern+"\\s*";

        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);
        if (!matcher.matches()) {
            System.out.println("Invalid command");
            return ;
        }

    
        String costumerId= matcher.group("id");
        int intCostumerId=Integer.parseInt(costumerId);
        boolean validCostumer=false;

        for (Costumers costumer : Costumers.allCostumers) {
            if(costumer.id==intCostumerId){
                validCostumer=true;
                break;
            }
        }
        if(!validCostumer){
            System.out.println("Customer's id does not exist");
            return ;
        }

        String itemsId=matcher.group("itemsId");
        String format2="(?<nextId>\\d+)"+"\\,?";
        Pattern pattern2=Pattern.compile(format2);
        Matcher matcher2=pattern2.matcher(itemsId);
        int itemCode;
        boolean existingItem=false;
        while (matcher2.find()) {
            itemCode=Integer.parseInt(matcher2.group("nextId"));
            for (Items item : Items.allItems) {
                if(item.id==itemCode){
                    existingItem=true;
                    break;
                }
            }
            if(!existingItem){
                System.out.println("Id "+itemCode + " does not exist");
                continue ;
            }

            for (Items item : Costumers.getCostumerById(intCostumerId).shoppingCart) {
                if (item.id==itemCode) {
                    System.out.println("Id "+itemCode+" has already been added");
                    existingItem=false;
                    break ;
                }
            }

            if (!existingItem) {
                continue;
            }
            existingItem=false;
            Costumers.getCostumerById(intCostumerId).shoppingCart.add(Items.getItembyId(itemCode));
        }
    }

    public static void increaseInventory(String info){
        String idPattern="(?<id>\\d+)";
        String amountPattern="(?<pattern>\\d+)";
        String formula="\\s*"+idPattern+"\\s+"+amountPattern+"\\s*";

        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);

        if (!matcher.matches()) {
            System.out.println("Invalid command");
            return ;
        }
        if ( Items.getItembyId(Integer.parseInt(matcher.group("id")))==null) {
            System.out.println("The id does not exist");
            return ;
        }

        Items.getItembyId(Integer.parseInt(matcher.group("id"))).count+=
        Integer.parseInt(matcher.group("pattern"));
    }

    public static void tellCostumerInfo(String info){
        String idPattern="(?<id>\\d+)";
        String formula="\\s*"+idPattern+"\\s*";

        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);
        if(!matcher.matches()){
            System.out.println("Invalid command");
            return ;
        }

        int intCostumerId=Integer.parseInt(matcher.group("id"));
        boolean validCostumer=false;

        for (Costumers costumer : Costumers.allCostumers) {
            if(costumer.id==intCostumerId){
                validCostumer=true;
                break;
            }
        }
        if(!validCostumer){
            System.out.println("The id does not exist");
            return ;
        }

        Costumers target=Costumers.getCostumerById(intCostumerId);
        System.out.println("name : "+target.name);
        System.out.println("balance : "+target.balance);
        for (int i = 0; i < target.shoppingCart.size(); i++) {
            System.out.println(""+(i+1)+".commodity : "+target.shoppingCart.get(i).name);
        }

    }

    public static void tellItemInfo(String info){
        
            String idPattern="(?<id>\\d+)";
            String formula="\\s*"+idPattern+"\\s*";
    
            Pattern pattern=Pattern.compile(formula);
            Matcher matcher=pattern.matcher(info);
            if(!matcher.matches()){
                System.out.println("Invalid command");
                return ;
            }
    
            int intItemId=Integer.parseInt(matcher.group("id"));
            boolean validItem=false;
            for (Items item : Items.allItems) {
                if(item.id==intItemId){
                    validItem=true;
                    break ;
                }
            }
            if (!validItem) {
                System.out.println("The id does not exist");
                return ;
            }

            Items target=Items.getItembyId(intItemId);
            System.out.println("category : "+target.category);
            System.out.println("name : "+target.name);
            System.out.println("price : "+target.price);
            System.out.println("count : "+target.count);
            for (String detail : target.details) {
                System.out.println("detail : "+detail);
            }
            System.out.println("rate : "+String.format("%.2f", target.rate));
            for (String comment : target.comments) {
                System.out.println("comment : "+comment);
            }
    }

    public static void filterItems(String info){
        String categoryPattern="(?<pattern>[^\\s]+)";
        String lowerPattern="\\("+"0x"+"(?<lower>[\\d|A-F]+)"+"\\)";
        String upperPattern="\\("+"0x"+"(?<upper>[\\d|A-F]+)"+"\\)";
        String formula="\\s*"+categoryPattern+"\\s+"+lowerPattern+"\\s+"+"to"+"\\s+"+upperPattern+"\\s*";

        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);
            if(!matcher.matches()){
                System.out.println("Invalid command");
                return ;
            }
        int lowerLimit=Integer.parseInt(matcher.group("lower"),16);
        int upperLimit=Integer.parseInt(matcher.group("upper"),16);
        String itemCategory=matcher.group("pattern");

        boolean validCategory=false;
        for (String category : Categories) {
            if(category.equals(itemCategory)){
                validCategory=true;
                break;
            }
        }
        if (!validCategory) {
            System.out.println("The category does not exist");
            return;
        }
        if(lowerLimit>=upperLimit){
            System.out.println("Invalid bounds");
            return ;
        }
        int i=1;
        for (Items item : Items.allItems) {
            if(item.category.equals(itemCategory)&&item.price>lowerLimit&&item.price<upperLimit){
                System.out.println(Integer.toString(i)+"."+item.name);
                i++;
            }
        }

    }

    public static void buyItem(String info){
        String costumerIdPattern="(?<Id>\\d+)";
        String itemIdPattern="(?<Id2>\\d+)";
        String countPattern="(?<count>\\d+)";
        String commentPattern=  "(\\?comment\\s+\\[(?<comment>.+)\\])?";
        String ratingPattern= "(\\?rate\\s*\\(\\s*(?<rating>\\d)\\s*\\)\\s*)?";
        

        String formula="\\s*"+costumerIdPattern+"\\s+"+itemIdPattern+"\\s+"+countPattern+"\\s*"+
        commentPattern+"\\s*"+ratingPattern+"\\s*";

        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);

        
        if (!matcher.matches()) {
            System.out.println("Invalid command");
            return ;
        }

        int costumerId=Integer.parseInt(matcher.group("Id"));
        int itemId=Integer.parseInt(matcher.group("Id2"));
        int count=Integer.parseInt(matcher.group("count"));
        String comment;
        int rate=0;

        if (matcher.group("rating") != null) {
            rate=Integer.parseInt(matcher.group("rating"));
            if(rate<1||rate>5){
                System.out.println("Invalid command");
                return;
            }
        }


        boolean validCostumer=false;

        for (Costumers costumer : Costumers.allCostumers) {
            if(costumer.id==costumerId){
                validCostumer=true;
                break;
            }
        }
        if(!validCostumer){
            System.out.println("The customer's id does not exist");
            return ;
        }

        boolean validItem=false;
        for (Items item : Items.allItems) {
            if(item.id==itemId){
                validItem=true;
                break ;
            }
        }
        if (!validItem) {
            System.out.println("The commodity's id does not exist");
            return ;
        }

        boolean isInWishlist=false;
        for (Items item : Costumers.getCostumerById(costumerId).shoppingCart) {
            if (item.id==itemId) {
                isInWishlist=true;
                break;
            }
        }
        if(!isInWishlist){
            System.out.println("Customer's cart does not include this commodity");
            return;
        }

        int totalCost=count*Items.getItembyId(itemId).price;
        if (totalCost>Costumers.getCostumerById(costumerId).balance) {
            System.out.println("Balance is not enough");
            return ;
        }

        if (count>Items.getItembyId(itemId).count) {
            System.out.println("Insufficient stock");
            return;
        }


        Costumers moshtari= Costumers.getCostumerById(costumerId);
        Items jens=Items.getItembyId(itemId);

        moshtari.balance-=totalCost;
        jens.count-=count;
        int i=0;
        for (Items item : moshtari.shoppingCart) {
            if (item.id==itemId) {
                moshtari.shoppingCart.remove(i);
                break;
            }
            i++;
        }
        

        if(matcher.group("comment") != null){
            comment=matcher.group("comment");
            jens.comments.add(comment);
        }

        if (matcher.group("rating") != null) {
        jens.rate=(jens.rate*jens.rateCount+rate) / (jens.rateCount+1);
        jens.rateCount++;
        }
    
    }

    public static void changeBalance(String info){
        String idPattern="(?<Id>\\d+)";
        String minusSign="(?<minus>\\-)?";
        String amountPattern="(?<amount>\\d+)";
        String formula="\\s*"+idPattern+"\\s+"+minusSign+amountPattern+"\\s*";


        Pattern pattern=Pattern.compile(formula);
        Matcher matcher=pattern.matcher(info);

        if (!matcher.matches()) {
            System.out.println("Invalid command");
            return ;
        }

        int id=Integer.parseInt(matcher.group("Id"));
        int amount=Integer.parseInt(matcher.group("amount"));
        if(matcher.group("minus") != null){
            amount=-amount;
        }

        if (Costumers.getCostumerById(id)== null) {
            System.out.println("The id does not exist");
            return ;
        }
        Costumers.getCostumerById(id).balance+=amount;
    }
    public static void main(String[] args){

        Scanner scanner=new Scanner(System.in);
        String input;
        boolean isStarted=false;
        
        while (true) {
            Boolean check=true;
            String order;
            input=scanner.nextLine();

            String onekey="\\s*"+"(?<firstword>[^\\s]+)"+"\\s*";
            Pattern pattern1=Pattern.compile(onekey);
            Matcher matcher1=pattern1.matcher(input);

            String twoKeys="\\s*"+"(?<firstword>[^\\s]+)"+"\\s+"+"(?<secondword>[^\\s]+)"+"(?<args>.+)";
            Pattern pattern2=Pattern.compile(twoKeys);
            Matcher matcher2=pattern2.matcher(input);

            String threeKeys="\\s*"+"(?<firstword>[^\\s]+)"+"\\s+"+"(?<secondword>[^\\s]+)"+
            "\\s+"+"(?<thirdword>[^\\s]+)"+"(?<args>.+)";
            Pattern pattern3=Pattern.compile(threeKeys);
            Matcher matcher3=pattern3.matcher(input);

             if (matcher1.matches()) {
                order=matcher1.group("firstword");
                if (order.equals("start")&& !isStarted) {
                    isStarted=true;
                    continue;
                }
                else if(order.equals("end")&& isStarted){
                    break;
                }
            }

             if (matcher2.matches()&&isStarted) {
                order=matcher2.group("firstword")+" "+matcher2.group("secondword");
                String argz=matcher2.group("args");

                 if (order.equals("add customer")) {
                    addCostumers(argz);
                    continue;
                }
                 if (order.equals("add category")) {
                    addCategory(argz);
                    continue;
                }
                 if (order.equals("add commodity")) {
                    addItem(argz);
                    continue;
                }
                 if (order.equals("increase inventory")) {
                    increaseInventory(argz);
                    continue;
                }
                 if( order.equals("change balance")){
                    changeBalance(argz);
                    continue;
                }
                 if(order.equals("filter commodity")){
                    filterItems(argz);
                    continue;
                }
                 if(order.equals("buy commodity")){
                    buyItem(argz);
                    continue;
                }
                if(order.equals("commodity info")){
                    tellItemInfo(argz);
                    continue;
                }
                 if(order.equals("customer info")){
                    tellCostumerInfo(argz);
                    continue;
                }
            }
             if (isStarted&&matcher3.matches()&&check) {
                order=matcher3.group("firstword")+" "+matcher3.group("secondword")
                +" "+matcher3.group("thirdword");
                String argzz=matcher3.group("args");
                if (order.equals("add to cart")) {
                    addToCart(argzz);
                    continue;
                }
            }
               if(isStarted) System.out.println("Invalid command");
            
        }
    }

}