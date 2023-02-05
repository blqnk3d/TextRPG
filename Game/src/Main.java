import java.io.*;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static final Enemy[] enemys = {
            new Enemy("Giant Rat", 15, 5, 10, 10, 15),
            new Enemy("Slime", 20, 10, 10, 10, 15),
            new Enemy("Spiderling", 25, 5, 10, 15, 20),
            new Enemy("Goblin Scout", 30, 5, 10, 15, 20),
            new Enemy("Skeleton Archer", 40, 15, 15, 20, 25),
            new Enemy("Zombie Minion", 50, 10, 20, 25, 30),
            new Enemy("Imp", 70, 20, 30, 35, 40),
            new Enemy("Kobold", 60, 15, 25, 30, 35),
            new Enemy("Goblin Thug", 80, 25, 35, 40, 45),
            new Enemy("Skeleton Guard", 90, 30, 40, 45, 50)
    };
    private static Player player;
    private final Inventory Inv = new Inventory();
    private final Item[] ShopItems = {
            new Item("Low HealPot", 1, "heal0", 3, "Heals 1 % of YOUR ma Hp"),
            new Item("Mid HealPot", 1, "heal1", 5, "Heals 5 % of YOUR ma Hp"),
            new Item("Large HealPot", 1, "heal2", 7, "Heals 10 % of YOUR ma Hp"),
            new Item("Wooden Stick", 1, "attack0", 20, "Added +2 Damage to ur Attacks"),
            new Item("Sword", 1, "attack1", 30, "Added +5 Damage to ur Attacks"),
            new Item("Wooden Plank", 1, "defence0", 20, "Added +2 Defence to ur Defence"),
            new Item("Iron Shield", 1, "defence1", 30, "Added +5 Defence to ur Defence"),
            new Item("Revive", 1, "rev", 250, "This item revives u if u have it in ur Inv.")

    };

    public Main() {
            player = new Player(inputString("Input the Player name : "));
    }

    public static void main(String[] args) {


        Main game = new Main();
        String choose;

        while (true) {
            System.out.println("List of commands: fight [f]   printhp [php]   quit [q]   shop [s]   inventory [inv]   useitem [ui] stats");
            choose = inputString("What do u wish to do ? : ").toLowerCase();
            switch (choose) {

                case "fight", "f" -> game.fight();
                case "printhp", "php" -> game.getPlayer().printHealth();

                case "quit", "q" -> {
                    System.exit(0);
                }

                case "shop", "s" -> {
                    printItem(game.ShopItems);
                    Item item = game.shop(inputInt("Please enter the Index : "));
                        game.Inv.setItem(item);
                        //game.Inv.compresserInv(game.ShopItems);
                }
                case "inventory", "inv" -> System.out.println(game.Inv);

                case "useitem", "ui" -> {

                    System.out.println(game.Inv);

                    int coords = -1;
                    try {
                        coords = inputInt("Please input the Index Starting Left Top Corner: ") - 1;
                    } catch (InputMismatchException i) {
                        System.out.println("Please enter a number !");
                    }
                    try{
                        player = game.Inv.getInv()[coords].useItem(player);
                    }catch (NullPointerException n){
                        System.out.println("The is no item in that spot");
                    }
                    if (game.Inv.getInv()[coords].getAmount() - 1 <= 0) {
                        game.Inv.getInv()[coords] = null;
                    } else {
                        game.Inv.getInv()[coords].setAmount(game.Inv.getInv()[coords].getAmount() - 1);

                    }
                }
                case "debug" -> {

                }
                case "stats" -> player.printStats();
                default -> System.out.println("Input not found please try again.");
            }
        }

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
        System.out.println(player.getName().toUpperCase()+" current Money : "+player.getMoney());
        for (int i = 0; i < items.length; i++) {
            System.out.println("Index : [" + i + "] --> " +Inventory.fill(items[i].getName(),15,' ')+" ["+items[i].getPrice()+"$]");
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



    public Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Main.player = player;
    }

    public void fight() {
        int currentTurn = 0; // 0 = Player | 1 = Enemy
        printEnemys(enemys);
        int enemyFightIndex = inputInt("Which Enemy do u wanna fight : ");
        try{
            enemys[enemyFightIndex].isDead();
        }catch (ArrayIndexOutOfBoundsException ao){
            System.out.println("Index out of range.");
            return;
        }
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
                player.setMoney(player.getMoney()+enemys[enemyFightIndex].getCoins());
                enemys[enemyFightIndex].reset();
                break;
            } else if (player.isDead()) {
                //ToDo Here check if revive in Inv if not Exit with a System.exit(0)
                System.out.println("Sorry but u lost that fight ");
                System.out.println("Game will be now exiting");
                //!System.exit(0);
        /*
        if(Inv.findItemWType("rev")!= null && Objects.equals(inputString("Do wanna Buy/Use a Revive?"), "yes")){
            int[]coord = Inv.findItemWType("rev");
            player = Inv.getInv()[coord[1]][coord[0]].useItem(player);
        }else {
            // ToDo Shop here

        }

         */
            }
        }
        if (player.getLvl() >= player.getMaxExp()) {
            player.levelUp();
        }
    }

    public Item shop(int input) {
        for (int i = 0; i < ShopItems.length; i++) {
            if (input == i) {
                if (player.getMoney() >= ShopItems[input].getPrice()){
                    player.setMoney(player.getMoney()-ShopItems[input].getPrice());
                    return ShopItems[i];
                }else {
                    System.out.println("Sorry but you dont have enouth money .");
                }
            }
        }
        return null;
    }

}
