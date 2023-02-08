package player;

/**
 * Created:
 *
 * @author Julian Sieberer ()
 */

public class CraftObject {

    private Item returnItem;
    private int[] checkPossible;

    public CraftObject(Item returnItem, int[] checkPossible) {
        this.returnItem = returnItem;
        this.checkPossible = checkPossible;
    }

    public boolean isCraftable(int[] toCheck) {
        int count = 0;
        for (int i = 0; i < toCheck.length; i++) {
            if (toCheck[i] >= checkPossible[i]) {
                count++;
            }
        }
        if (count == toCheck.length) {
            return true;
        }
        return false;
    }

    public Item craft() {
        return returnItem;
    }


}
