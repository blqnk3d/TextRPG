public class Item {
    private final String name;

    private int amount;

    private final String type;
    private final int price;

    private final String description;

    public Item(String name, int amount, String type, int price,String decription) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.price = price;
        this.description = decription;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Player useItem(Player player) {
        switch (this.type){
           case "heal0"-> player.setHp(player.getHp()*1.01);
            case "heal1"-> player.setHp(player.getHp()*1.05);
            case "heal2"-> player.setHp(player.getHp()*1.1);
            case "attack0"-> player.setAttackDmg(player.getAttackDmg()+2);
            case "attack1"-> player.setAttackDmg(player.getAttackDmg()+5);
            case "defence0"-> player.setDefence(player.getDefence()+2);
            case "defence1"-> player.setDefence(player.getDefence()+5);
           case default -> System.out.println("Type not found");

        }

        return player;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + name + "]" + "[" + amount + "]";
    }
}
