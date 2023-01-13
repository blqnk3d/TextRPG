public class Item {
    private final String name;
    private final int amount;
    private final String type;

    private final int price;

    public Item(String name, int amount, String type,int price) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.price = price;
    }

    public Player useItem(Player player) {
        if ("heal0".equals(this.type)) {
            System.out.println("Healed 5 hp ");
            player.setHp(player.getHp() + 5);
        } else {
            System.out.println("Type not found");
        }
        return player;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + name + "]"+"["+ amount +"]";
    }
}
