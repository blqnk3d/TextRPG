package player;

public class Item {
    private final String name;

    private int amount;

    private final String type;
    private final int price;

    private final String description;
    //ToDo Constructor fuer dropped Items
    public Item(String name, int amount, String type, int price, String decription) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.price = price;
        this.description = decription;

    }

    /**
     * This function sets the amount of the object to the amount passed in.
     *
     * @param amount The amount of the transaction.
     */
    public void setAmount(int amount) {

        this.amount = amount;
    }

    /**
     * This function returns the amount of money in the account.
     *
     * @return The amount of the item.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * This function returns the type of the current object
     *
     * @return The type of the object.
     */
    public String getType() {
        return type;
    }

    /**
     * This function returns the price of the item.
     *
     * @return The price of the item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * > This function returns the description of the object
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This function returns the length of the name variable.
     *
     * @return The length of the name variable.
     */
    public int length() {
        return name.length();
    }

    /**
     * If the item has an amount greater than 0, then depending on the type of item, the player's stats will be modified
     *
     * @param player The player object that is using the item
     * @return The player object is being returned.
     */
    public Player useItem(Player player) {
        if (this.amount > 0) {
            switch (this.type) {
                case "heal0" -> player.setHp(player.getHp() * 1.01);
                case "heal1" -> player.setHp(player.getHp()* 1.05);
                case "heal2" -> player.setHp(player.getHp() * 1.1);
                case "attack0" -> player.setAttackDmg(player.getAttackDmg() + 2);
                case "attack1" -> player.setAttackDmg(player.getAttackDmg() + 5);
                case "defence0" -> player.setDefence(player.getDefence() + 2);
                case "defence1" -> player.setDefence(player.getDefence() + 5);
                case "rev" -> player.setHp(player.getMaxHP());
                case default -> System.out.println("Type not found");
            }
            this.amount -= 1;
        }
        return player;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    @Override
    // This function is overriding the toString function.
    public String toString() {
        return "[" + name + "]" + "[" + amount + "]";
    }
}
