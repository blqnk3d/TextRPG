package enemyGen;

import enemy.Enemy;
import player.Player;

import java.util.Random;

public class Generator {
    double mutation = 0.05;
    Random random = new Random();
    Player player = new Player("TestPlayer");
    Enemy bestEnemy = new Enemy("Test", 100, 1, 1, 0, 0);
    double enemyHP = bestEnemy.getHp();
    Enemy currentEnemy = bestEnemy;
    double threashhold = 0.1;

    public static void main(String[] args) {
        Generator generator = new Generator();
        for (int i = 0; i < 1000; i++) {
            generator.tryMakeBetter(20);

        }
    }

    public Enemy tryMakeBetter(int hpWanted) {
        int currentTurn = 0;
        currentEnemy = bestEnemy;

        while (!currentEnemy.isDead()) {
            if (currentTurn == 0) {
                currentEnemy.attacked(player.getAttackDmg());

                currentTurn = 1;
            } else {
                player.attacked(currentEnemy.getAttackDmg());

                currentTurn = 0;
            }
        }

        if (player.getHp() < hpWanted) {
            currentEnemy = bestEnemy;
            currentEnemy.setAttackDmg(currentEnemy.getAttackDmg() * random.nextDouble(1 - mutation, 1 ));
            System.out.println("REDUCING");
        }else {
            currentEnemy = bestEnemy;
            currentEnemy.setAttackDmg(currentEnemy.getAttackDmg() * random.nextDouble(1 , 1+ mutation ));
            System.out.println("ADDING");
        }
        // ToDo make it if the enemy is closer to the hp amount than change the best enemy

        if (checkEnemy()) {
            player.printHealth();
            bestEnemy.printStats();
        }
        player = new Player("TestPlayer");
        currentEnemy.setHp(enemyHP);
        return null;
    }
    public boolean checkEnemy(){
        if (player.getHp() < player.getHp()+player.getHp()*threashhold &&player.getHp() > player.getHp()-player.getHp()*threashhold){
    return true;
        }
        return false;
    }

}
