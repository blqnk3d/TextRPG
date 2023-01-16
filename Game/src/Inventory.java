import java.util.Arrays;

public class Inventory {
    private Item[][] Inv = new Item[5][5];

    public Item[][] getInv() {
        return Inv;
    }

    public void setInv(Item[][] inv) {
        Inv = inv;
    }

    public void setItem(Item item) {
        if (item.getAmount() != 0) {
            int[] coords = findEndInf();
            this.Inv[coords[0]][coords[1]] = item;
        }
    }

    public int[] findEndInf() {
        int[] ret = new int[2];
        for (int y = 0; y < this.Inv.length; y++) {
            for (int x = 0; x < this.Inv[0].length; x++) {
                if (this.Inv[y][x] == null) {
                    ret[1] = x;
                    ret[0] = y;
                    System.out.println("findEndInf : " + Arrays.toString(ret));
                    return ret;
                }
                System.out.println();
            }
        }
        return ret;
    }

    public void compresserInv(Item[] ShopItems) { // TO-DO Check if an Ammount is 0 and delet it instant
        int[] count = new int[ShopItems.length];

        for (int i = 0; i < ShopItems.length; i++) {
            for (int j = 0; j < Inv.length; j++) {
                for (int k = 0; k < Inv[0].length; k++) {
                    if (Inv[j][k] == ShopItems[i]) {
                        count[i] += 1;
                    }
                }
            }
        }
        clearInv(); // Clears the inv and than adds the Items with the amount
        for (int i = 0; i < ShopItems.length; i++) {
            setItem(new Item(ShopItems[i].getName(), ShopItems[i].getAmount(), ShopItems[i].getType(), ShopItems[i].getPrice()));
        }


    }

    public void clearInv() {
        for (int i = 0; i < this.Inv.length; i++) {
            for (int j = 0; j < this.Inv[0].length; j++) {
                this.Inv[i][j] = null;
            }
        }
    }

    @Override
    public String toString() {
        String ret = "";

        for (int i = 0; i < Inv.length; i++) {
            for (int j = 0; j < Inv[0].length; j++) {

                if (Inv[i][j] == null) {
                    ret += fill("", 10, ' ') + "|";
                } else {
                    ret += fill(Inv[i][j].toString(), 10, ' ') + "|";
                }
            }
            ret += "\n";
        }
        return ret;
    }

    public String fill(String num, int max, char ch) {
        String ret = "";
        for (int i = 0; i < max - num.length(); i++) {
            ret = ret + ch;
        }
        return ret;
    }

    // TO-Do Get item methot to

    public static void main(String[] args) {
        Inventory inv = new Inventory();

        System.out.println(inv.toString());

    }
}
