import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private Item[][] Inv = new Item[5][5];
    private final Item[] ShopItems = {new Item("Low HealPot", 1, "heal0",3), new Item("Mid HealPot", 1, "heal1",5), new Item("Large HealPot", 1, "heal3",7)};
    private static Player player;
    private static final Enemy[] enemys = {new Enemy("Slime", 20, 0, 10)};

    public Main() {
        player = new Player(inputString("Input the Player name : "));
    }

    public Player getPlayer() {
        return player;
    }

    public void fight() {
        int currentTurn = 0; // 0 = Player | 1 = Enemy
        printEnemys(enemys);
        int enemyFightIndex = inputInt("Which Enemy do u wanna fight : ");
        while (!player.isDead()) {
            if (currentTurn == 0) {
                enemys[enemyFightIndex].attacked(player.getAttackDmg());
                currentTurn = 1;
            } else {
                player.attacked(enemys[enemyFightIndex].getAttackDmg());
                currentTurn = 0;
            }

            if (enemys[enemyFightIndex].isDead()) {
                player.printHealth();
                System.out.println("U won the fight !!!");
                enemys[enemyFightIndex].reset();
                break;
            }
        }
        // To-Do Make it so u can enter the shop and buy a revive if possible if not Exit.
    }

    public static void main(String[] args) {
        Main game = new Main();

        String choose;
        while (true) {
            choose = inputString("What do u wish to do ? : ").toLowerCase();
            switch (choose) {
                case "fight" -> game.fight();
                case "printhp" -> game.getPlayer().printHealth();
                case "quit" -> System.exit(0);
                case "shop" -> {
                    printItem(game.ShopItems);
                    Item item = game.shop(inputInt("Please enter the Index : "));
                    int [] last = game.findEndInf(game.Inv);
                    // To-Do Change so it works with rows not collums and Formate
                    System.out.println(Arrays.toString(last));

                    game.Inv[last[0]][last[1]] = item;
                }
                case "inventory"->{
                    for (int x = 0; x < game.Inv.length; x++) {
                        for (int y = 0; y < game.Inv[0].length; y++) {
                            System.out.print(game.Inv[y][x]+" | ");
                        }
                        System.out.println();
                    }
                }
                case "useitem"->{

                    for (int x = 0; x < game.Inv.length; x++) {
                        for (int y = 0; y < game.Inv[0].length; y++) {
                            System.out.print(game.Inv[y][x]+" | ");
                            if(x == 0 && y == game.Inv[0].length  ){
                                System.out.print("<--- X");
                            }
                        }
                        System.out.println();
                    }
                    int x = -1;
                    int y = -1;
                    try {
                         x = inputInt("Please enter the X coordinate : ");
                         y = inputInt("Please enter the Y coordinate : ");
                    }catch (InputMismatchException i ){
                        System.out.println("Please enter a number !");
                    }
                    player =  game.Inv[x][y].useItem(player);
                    game.Inv [x][y] = null;
                }
                default -> System.out.println("Input not found please try again.");
            }
        }

    }

    public Item shop(int input) {
        switch (input) {
            case 0 -> {
                return ShopItems[0];
            }
            case 1 -> {
                return ShopItems[1];
            }
            case 2 -> {
                return ShopItems[2];
            }
            default -> System.out.println("Index not Found");
        }
        return new Item("",0,"",1);
    }

    public int[] findEndInf(Item[][] Inv) {
        int[] ret = new int[2];
        for (int y = 0; y < Inv.length; y++) {
            for (int x = 0; x < Inv[0].length; x++) {
                if (Inv[y][x] == null) {
                    ret[1] = x;
                    ret[0] = y;
                    System.out.println("findEndInf : "+Arrays.toString(ret));
                    return ret;
                }
                System.out.println();
            }
        }
        return ret;
    }

    public static String inputString(String msg) {
        Scanner scan = new Scanner(System.in);
        System.out.print(msg);
        try {
            return scan.nextLine();
        } catch (NoSuchElementException n) {
            System.out.println("No Name detected : " + n);
            return "";
        }
    }


    public static void printEnemys(Enemy[] enemys) {
        for (int i = 0; i < enemys.length; i++) {
            System.out.println("Index : [" + i + "] --> " + enemys[i].getName());
        }
    }

    public static void printItem(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println("Index : [" + i + "] --> " + items[i].getName());
        }
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
