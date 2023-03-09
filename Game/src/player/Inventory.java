package player;

import java.util.Arrays;
import java.util.Objects;


public class Inventory {
    private Item[] Inv = new Item[25];

    public static void main(String[] args) {
        Item[] testShop = {new Item("test", 1, "heal0", 1, "")};
        Inventory inv = new Inventory();
        inv.setItem(new Item("test", 1, "heal0", 1, ""));
        inv.setItem(new Item("test", 1, "heal0", 1, ""));
        inv.setItem(new Item("test", 1, "heal0", 1, ""));
        inv.setItem(new Item("test", 1, "heal0", 1, ""));
        inv.setItem(new Item("test", 1, "heal0", 1, ""));
        inv.setItem(new Item("test", 1, "heal0", 1, ""));
        System.out.println(inv);

    }

    /**
     * If the length of the string is less than the max, then fill the string with the character until it reaches the max
     * length.
     *
     * @param num The number to be formatted.
     * @param max The maximum length of the string.
     * @param ch  The character to fill the string with.
     * @return A string of the number with the character repeated to fill the max length.
     */
    public static String fill(String num, int max, char ch) {
        StringBuilder ret = new StringBuilder();
        if (num != null) {
            ret = new StringBuilder(num);
        }

        ret.append(String.valueOf(ch).repeat(Math.max(0, max - num.length())));

        return ret.toString();
    }

    /**
     * This function returns the value of the variable Inv.
     *
     * @return The array Inv is being returned.
     */
    public Item[] getInv() {
        return Inv;
    }

    /**
     * This function sets the value of the variable Inv to the value of the variable inv.
     *
     * @param inv The inventory of the player.
     */
    public void setInv(Item[] inv) {
        Inv = inv;
    }

    /**
     * If the item's amount is not 0, find the end of the inventory and set the item there
     *
     * @param item The item you want to add to the inventory.
     */
    public void setItem(Item item) {
        if (item != null && item.getAmount() != 0) {
            int coords = findEndInv();
            this.Inv[coords] = item;
        }
    }

    /**
     * Find the first empty slot in the inventory.
     *
     * @return The index of the first empty slot in the inventory.
     */
    public int findEndInv() {
        int ret = -1;
        for (int y = 0; y < this.Inv.length; y++) {
            if (Inv[y] == null) {
                return y;
            }
        }
        return ret;
    }

    /**
     * It takes an array of items and counts how many of each item is in the inventory, then clears the inventory and adds
     * the items back in with the correct amount
     *
     * @param ShopItems The items that are in the shop
     */
    public void compresserInv(Item[] ShopItems) {
        int[] count = new int[ShopItems.length];

        for (int i = 0; i < ShopItems.length; i++) {
            for (Item item : Inv) {
                if (item == ShopItems[i]) {
                    count[i]++;
                }
            }
        }
        clearInv(); // Clears the inv and then adds the Items with the amount
        /*
        for (int i = 0; i < ShopItems.length; i++) {
            System.out.println(count[i]);
            setItem(new Item(ShopItems[i].getName(), count[i], ShopItems[i].getType(), ShopItems[i].getPrice(), ShopItems[i].getDescription()));
        }
*/

    }

    /**
     * It clears the inventory of the player
     */
    public void clearInv() {
        Arrays.fill(this.Inv, null);
    }

    @Override
    // Printing the inventory in a nice way.
    public String toString() {
        String ret = "";
        String s = String.valueOf('-').repeat("                                                                                ".length()) + "\n";
        ret += s;

        for (int i = 1; i < Inv.length + 1; i++) {
            if (i % 5 != 0) {
                if (Inv[i - 1] == null) {
                    ret += Player.centerString(15, " ") + "|";
                } else {
                    ret += Player.centerString(15, Inv[i - 1].toString()) + "|";

                }
            } else {
                if (Inv[i - 1] == null) {
                    ret += Player.centerString(15, " ") + "|\n";
                } else {
                    ret += Player.centerString(15, Inv[i - 1].toString()) + "|\n";

                }
            }
        }
        ret += s;
        return ret;
    }

    /**
     * This function takes a string as an argument and returns the index of the first item in the inventory that matches
     * the string
     *
     * @param type The type of item you want to find.
     * @return The index of the item in the inventory.
     */
    public int findItemWType(String type) {
        for (int Index = 0; Index < Inv.length; Index++) {
            if (Objects.equals(type, Inv[Index].getType())) {
                return Index;
            }
        }
        return -1;
    }

    public int findIndex(String itemName) {
        for (int i = 0; i < Inv.length; i++) {
            try {
                if (Inv[i].getName().equals(itemName)) {
                    return i;
                }
            } catch (NullPointerException n) {
                System.out.println("Nullpointer");
            }
        }
        return -1;
    }

    public void deleteItem(int index) {
        try {
            Inv[index] = null;
        } catch (ArrayIndexOutOfBoundsException ab) {
            System.out.println("player.Item not found : " + ab);
        }
    }
}
