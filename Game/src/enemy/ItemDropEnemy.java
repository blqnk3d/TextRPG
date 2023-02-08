package enemy;

import player.Item;

/**
 * Created:
 *
 * @author Julian Sieberer ()
 */

public class ItemDropEnemy {
    private int chance;

    private Item drop;

    public ItemDropEnemy(int chance, Item drop) {
        this.chance = chance;
        this.drop = drop;
    }

    public int getChance() {
        return chance;
    }


    public Item getDrop() {
        return drop;
    }


}
