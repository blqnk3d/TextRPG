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

    public Item[] getInv() {
        return Inv;
    }

    public void setInv(Item[] inv) {
        Inv = inv;
    }

    public void setItem(Item item) {
        if (item.getAmount() != 0) {
            int coords = findEndInv();
            this.Inv[coords] = item;
        }
    }

    public int findEndInv() {
        int ret = -1;
        for (int y = 0; y < this.Inv.length; y++) {
            if (Inv[y] == null) {
                return y;
            }
        }
        return ret;
    }

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

        for (int i = 0; i < ShopItems.length; i++) {
            System.out.println(count[i]);
            setItem(new Item(ShopItems[i].getName(), count[i], ShopItems[i].getType(), ShopItems[i].getPrice(), ShopItems[i].getDescription()));
        }


    }

    public void clearInv() {
        Arrays.fill(this.Inv, null);
    }

    @Override
    public String toString() {
        String ret = "";

        for (int i = 1; i < Inv.length + 1; i++) {
            if (i % 5 != 0) {
                if (Inv[i - 1] == null) {
                    ret +=Player.centerString (15, " ") + "|";
                } else {
                    ret += Player.centerString(15,Inv[i - 1].toString()) + "|";

                }
            } else {
                if (Inv[i - 1] == null) {
                    ret += Player.centerString (15, " ") + "|\n";
                } else {
                    ret += Player.centerString(15,Inv[i - 1].toString()) +"|\n";

                }
            }
        }
        return ret;
    }

    public int findItemWType(String type) {
        for (int Index = 0; Index < Inv.length; Index++) {
            if (Objects.equals(type, Inv[Index].getType())) {
                return Index;
            }
        }
        return -1;
    }

    // TO-Do Get item methot to

    public static String fill(String num, int max, char ch) {
        StringBuilder ret = new StringBuilder();
        if (num != null) {
            ret = new StringBuilder(num);
        }

        ret.append(String.valueOf(ch).repeat(Math.max(0, max - num.length())));

        return ret.toString();
    }
}
