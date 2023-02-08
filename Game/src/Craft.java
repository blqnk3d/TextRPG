import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created: 07.02.2023
 *
 * @author Julian Sieberer (julian)
 */

public class Craft {

    private final CraftObject[] craftTableList = new CraftObject[]{new CraftObject(new Item("Death's Bow",1,"attack1",0,"The Bow used by Death himself"),new int[]{3,3})};

    private final Item[] droppedItems = new Item[]{
            new Item("Bone",1,"default",0,"Just a old bone"),
            new Item("String",1,"default",0,"String dropped by a spiderling")




    };

    private Item [] itemsCurrent ;

    private int[] counted;

    public void setItemsCurrent(Item[] itemsCurrent) {
        this.itemsCurrent = itemsCurrent;
    }

    public  void countItems(){
        int[] couted = new int[ droppedItems.length];

        for (int i = 0; i < droppedItems.length; i++) {
            for (int j = 0; j < itemsCurrent.length; j++) {
                //ToDo Fix Null Pointer because Item,getName null
                try {
                    if (itemsCurrent[j].getName().equals(droppedItems[i].getName())) {
                        couted[i]++;
                    }
                }catch (NullPointerException n){
                    System.out.println("Nullpointer occurd "+n);
                }
            }
        }
        this.counted = couted;
    }
    public void printAbleCrafts(){
        for (int i = 0; i < craftTableList.length; i++) {
            if(craftTableList[i]!= null){
                if(craftTableList[i].isCraftable(counted)){
                    System.out.println("Index -->["+i+"] "+craftTableList[i].craft().getName());
                }
            }
        }
    }

    public Inventory craft(Inventory inv){
        inv.setItem(craftTableList[inputInt("Please input the index of the Item to buy :")].craft());

        for (int i = 0; i < counted.length; i++) {
            if (counted[i]>0){
                for (int j = 0; j < counted[i]; j++) {
                    try {
                        inv.deleteItem(inv.findIndex(craftTableList[i].craft().getName()));
                    }catch (ArrayIndexOutOfBoundsException ab){
                        System.out.println("ArrayOutBound in craft : "+ab);
                    }
                }
            }
        }

        return inv;
    }

    public static void main(String[] args) {
        Craft craft = new Craft();
        craft.setItemsCurrent(new Item[]{
                new Item("Low HealPot", 1, "heal0", 3, "Heals 1 % of YOUR ma Hp"),
                new Item("Mid HealPot", 1, "heal1", 5, "Heals 5 % of YOUR ma Hp"),
                new Item("Bone",1,"default",0,"Just a old bone"),
                new Item("Bone",1,"default",0,"Just a old bone"),
                new Item("Bone",1,"default",0,"Just a old bone"),
                new Item("String",1,"default",0,"String dropped by a spiderling"),
                new Item("String",1,"default",0,"String dropped by a spiderling"),
                new Item("String",1,"default",0,"String dropped by a spiderling")

        });
        craft.countItems();
        craft.printAbleCrafts();

    }
    public static int inputInt(String msg) {
        Scanner scan = new Scanner(System.in);
        System.out.print(msg);
        try {
            return scan.nextInt();
        } catch (NoSuchElementException n) {
            System.out.println("No Name detected : " + n);
            return 0;
        }
    }
}
