public class Item {
    private final String name;
    private final int amount;
    private final String type;

    public Item(String name, int amount, String type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
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
        return "Name [" + name + "]\nAmount [" + amount + "]\nType [" + type + "]";
    }
}
