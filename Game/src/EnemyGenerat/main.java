package EnemyGenerat;

import enemy.Enemy;
import player.Player;

import java.util.Random;

/**
 * Created:
 *
 * @author Julian Sieberer ()
 */

public class main {
    private Enemy currentEnemy = new Enemy("testingEnemy",15,5,10,1,1);
    private Enemy testEnemy;

    private Enemy finishedEnemy;
    private Player player = new Player("test");
    private Random random = new Random();


   public void test(int hp){
       double randomAdd = random.nextDouble(1.0,1.1);
       double randomDeg = random.nextDouble(0.9,1.0);
       int range = 3;
       int currentTurn = 0;

       testEnemy = currentEnemy;





       while(!player.isDead()|| !testEnemy.isDead()){
           if (currentTurn == 0) {
               testEnemy.attacked(player.getAttackDmg());
               currentTurn = 1;
           } else {
               player.attacked(testEnemy.getAttackDmg());
               currentTurn = 0;
           }
           player.printStats();
           testEnemy.printStats();
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }

       player = new Player("test");
       if (player.getHp() <= hp-range && player.getHp() >= hp+range){
           finishedEnemy = currentEnemy;
       } else if (player.getHp() < hp) {
           currentEnemy.setDefence((int) (currentEnemy.getDefence() * randomDeg));
       }else if (player.getHp() > hp) {
           currentEnemy.setDefence((int) (currentEnemy.getDefence() * randomAdd));
       }

   }


    public static void main(String[] args) {
        main test = new main();

        test.test(80);

    }
}
