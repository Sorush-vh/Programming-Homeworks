public class Card {
    private String number;
    private long credit;

    public Card(User user) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ;  i < 3 ; i++) {
            builder.append((int)((Math.random() * 8999) + 1000));
            builder.append(" ");
        }
        builder.append((int)((Math.random() * 8999) + 1000));
        this.number = builder.toString();
        this.credit = 100000;
    }
}
