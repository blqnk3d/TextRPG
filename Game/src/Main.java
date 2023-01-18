import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private final Inventory Inv = new Inventory();
    private final Item[] ShopItems = {new Item("Low HealPot", 1, "heal0", 3,"Heals 1 % of YOUR ma Hp"),
                                      new Item("Mid HealPot", 1, "heal1", 5,"Heals 5 % of YOUR ma Hp"),
                                      new Item("Large HealPot", 1, "heal3", 7,"Heals 10 % of YOUR ma Hp"),
                                      new Item("Wooden Stick",1,"attack0",20,"Added +2 Damage to ur Attacks"),
                                      new Item("Sword",1,"attack1",30,"Added +5 Damage to ur Attacks") ,
                                      new Item("Wooden Plank",1,"defence0",20,"Added +2 Defence to ur Defence"),
                                      new Item("Iron Shield",1,"defence1",30,"Added +5 Defence to ur Defence")


    };
    private static Player player;
    private static final Enemy[] enemys = {new Enemy("Slime", 20, 0, 2,1,1),
            new Enemy("Goblin", 100, 4, 5,4,7),
            new Enemy("Ork", 150, 7, 20,9,3)};

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
        if (player.getLvl() >= player.getMaxLvL()) {
            player.levelUp();
        }
        // ToDo Make it so u can enter the shop and buy a revive if possible if not Exit.
    }

    public static void main(String[] args) {

        Main game = new Main();
        String choose;

        while (true) {
            System.out.println("List of commands: fight   printhp   quit   shop   inventory   useitem[WIP]");
            choose = inputString("What do u wish to do ? : ").toLowerCase();
            switch (choose) {

                case "fight" -> game.fight();
                case "printhp" -> game.getPlayer().printHealth();

                case "quit" -> System.exit(0);

                case "shop" -> {
                    printItem(game.ShopItems);
                    Item item = game.shop(inputInt("Please enter the Index : "));
                    game.Inv.setItem(item);
                    //game.Inv.compresserInv(game.ShopItems);
                }
                case "inventory" -> System.out.println(game.Inv);

                case "useitem" -> {

                    System.out.println(game.Inv);

                    int x = -1;
                    int y = -1;
                    try {
                        x = inputInt("Please enter the X coordinate : ");
                        y = inputInt("Please enter the Y coordinate : ");
                    } catch (InputMismatchException i) {
                        System.out.println("Please enter a number !");
                    }

                    player = game.Inv.getInv()[y][x].useItem(player);
                    if (game.Inv.getInv()[y][x].getAmount() - 1 <= 0) {
                        game.Inv.getInv()[y][x] = null;
                    } else {
                        game.Inv.getInv()[y][x].setAmount(game.Inv.getInv()[y][x].getAmount() - 1);

                    }
                }
                case "debug" -> {

                }
                default -> System.out.println("Input not found please try again.");
            }
        }

    }


    public Item shop(int input) {
        for (int i = 0; i < ShopItems.length; i++) {
            if (input == i) {
                return ShopItems[i];
            }
        }
        return new Item("", 0, "", 1,"");
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
