public class Inventory {
    private Item[][] Inv = new Item[5][5];

    public static void main(String[] args) {
        Item[] testShop = {new Item("test", 1, "heal0", 1,"")};
        Inventory inv = new Inventory();
        inv.setItem(new Item("test", 1, "heal0", 1,""));
        inv.setItem(new Item("test", 1, "heal0", 1,""));
        inv.setItem(new Item("test", 1, "heal0", 1,""));
        inv.setItem(new Item("test", 1, "heal0", 1,""));
        inv.setItem(new Item("test", 1, "heal0", 1,""));
        inv.setItem(new Item("test", 1, "heal0", 1,""));
        System.out.println(inv);
        inv.compresserInv(testShop);
        System.out.println(inv);

    }

    public Item[][] getInv() {
        return Inv;
    }

    public void setInv(Item[][] inv) {
        Inv = inv;
    }

    public void setItem(Item item) {
        if (item.getAmount() != 0) {
            int[] coords = findEndInv();
            this.Inv[coords[0]][coords[1]] = item;
        }
    }

    public int[] findEndInv() {
        int[] ret = new int[2];
        for (int y = 0; y < this.Inv.length; y++) {
            for (int x = 0; x < this.Inv[0].length; x++) {
                if (this.Inv[y][x] == null) {
                    ret[1] = x;
                    ret[0] = y;
                    return ret;
                }
                System.out.println();
            }
        }
        return ret;
    }

    public void compresserInv(Item[] ShopItems) {
        int[] count = new int[ShopItems.length];

        for (int i = 0; i < ShopItems.length; i++) {
            for (int j = 0; j < Inv.length; j++) {
                for (int k = 0; k < Inv[0].length; k++) {
                    if (Inv[j][k] == ShopItems[i]) {

                        count[i] += Inv[j][k].getAmount();
                    }
                }
            }
        }
        clearInv(); // Clears the inv and then adds the Items with the amount

        for (int i = 0; i < ShopItems.length; i++) {
            System.out.println(count[i]);
            setItem(new Item(ShopItems[i].getName(), count[i], ShopItems[i].getType(), ShopItems[i].getPrice(),ShopItems[i].getDescription()));
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
                    ret += fill("", 15, ' ') + "|";
                } else {

                    ret += fill(Inv[i][j].toString(), 15, ' ') + "|";
                }
            }
            ret += "\n";
        }
        return ret;
    }

    public int[]findItemWType(String type){
        int []ret = new int[2];
        for (int row = 0; row < Inv[0].length; row++) {
            for (int col = 0; col <Inv.length; col++) {
                if(type == Inv[row][col].getType()){
                    ret[1]=col;
                    ret[0]=row;
                 return ret ;
                }
            }
        }
        return null;
    }

    // TO-Do Get item methot to

    public String fill(String num, int max, char ch) {
        StringBuilder ret = new StringBuilder();
        if (num != null) {
            ret = new StringBuilder(num);
        }
        ret.append(String.valueOf(ch).repeat(Math.max(0, max - num.length())));

        return ret.toString();
    }
}
