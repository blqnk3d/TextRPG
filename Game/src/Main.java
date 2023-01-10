import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Player player;
    private static Enemy[] enemys = {new Enemy("Slime", 20, 0, -4)};

    public Main() {
        player = new Player(inputString("Input the Player name : "));
    }


    public void fight() {
        int currentTurn = 0; // 0 = Player | 1 = Enemy

        printEnemys();
        int enemyFightIndex = inputInt("Which Enemy do u wanna fight : ");
        while (!player.isDead()) {
            if (currentTurn == 0) {
                enemys[enemyFightIndex].attacked(player.getAttackDmg());
                currentTurn = 1;
            } else {
                player.attacked(enemys[enemyFightIndex].getAttackDmg());
                currentTurn = 0;
            }

            System.out.println(player.getHp() + "    " + enemys[enemyFightIndex].getHp());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (enemys[enemyFightIndex].isDead()) {
                System.out.println("U won the fight !!!");
                enemys[enemyFightIndex].reset();
                break;
            }
        }
    }

    public static void main(String[] args) {
        Main game = new Main();

        String choose = "";
        while (true) {
            choose = inputString("What do u wish to do ? : ").toLowerCase();
            switch (choose) {
                case "fight" -> game.fight();
                case "quit" -> System.exit(0);
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


    public static void printEnemys() {
        for (int i = 0; i < enemys.length; i++) {
            System.out.println("Index : [" + i + "] --> " + enemys[i].getName());
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
