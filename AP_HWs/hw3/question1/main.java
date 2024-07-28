public class main {

    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("A", 1);
        myHashMap.put("B", 2);
        myHashMap.put("C", 3);
        myHashMap.put("A", 4);
        myHashMap.remove("B");
        System.out.println(myHashMap.get("A"));
        System.out.println(myHashMap.get("B"));
        System.out.println(myHashMap.get("C"));
    }
}