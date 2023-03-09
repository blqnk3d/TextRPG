package enemy;

import player.Item;

import java.util.Random;

public class Enemy {
    private final String name;
    private double hp;
    private double maxHP;
    private double defence;
    private double attackDmg;

    private int exp;
    private int coins;
    private final int[] resetData;


    private ItemDropEnemy drops;

    public Enemy(String name, int hp, int defence, int attackDmg, int exp, int coins) {
        this.name = name;
        this.hp = hp;
        this.defence = defence;
        this.attackDmg = attackDmg;
        this.maxHP = hp;
        this.resetData = new int[]{hp, defence, attackDmg};
        this.exp = exp;
        this.coins = coins;
    }


    public Enemy(String name, int hp, int defence, int attackDmg, int exp, int coins, ItemDropEnemy drops) {
        this(name, hp, defence, attackDmg, exp, coins);
        this.drops = drops;
    }


    /**
     * This function resets the hp, defence, attackDmg and maxHP of the player to the values stored in the resetData array
     */
    public void reset() {
        this.hp = this.resetData[0];
        this.defence = this.resetData[1];
        this.attackDmg = this.resetData[2];
        this.maxHP = this.hp;
    }

    /**
     * The damage is equal to the attack multiplied by a constant divided by the sum of the constant and the defence.
     *
     * @param attack The attack value of the attacker
     */
    public void attacked(double attack) {
        int c = 100; // Constante
        double damage = c * attack / (c + this.defence);
        this.setHp(this.getHp() - damage);
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }


    /**
     * This function returns the value of the variable hp.
     *
     * @return The value of the hp variable.
     */
    public double getHp() {
        return hp;
    }

    /**
     * If the given hp is less than 0, set the hp to 0, otherwise set the hp to the given hp.
     *
     * @param hp The current health of the player.
     *           /**
     *           This function returns the maxHP variable.
     */

    public void setHp(double hp) {
        this.hp = Math.max(0, hp);
    }

    /**
     * This function returns the maxHP variable.
     *
     * @return The maxHP variable is being returned.
     */
    public double getMaxHP() {
        return maxHP;
    }

    /**
     * This function updates the maxHP variable to the current HP value.
     */
    public void updateMaxHp() {
        this.maxHP = getHp();
    }

    /**
     * This function returns the value of the variable defence.
     *
     * @return The defence variable is being returned.
     */
    public double getDefence() {
        return defence;
    }

    /**
     * If the new defence value is greater than the current defence value, then set the current defence value to the new
     * defence value.
     *
     * @param defence The amount of defence the player has.
     */
    public void setDefence(int defence) {
        this.defence = Math.max(this.defence, defence);
    }
    public void setDefence(double defence) {
        this.defence = Math.max(this.defence, defence);
    }

    /**
     * This function returns the attack damage of the player.
     *
     * @return The attackDmg variable.
     */
    public double getAttackDmg() {
        return attackDmg;
    }

    /**
     * If the new attackDmg is greater than the current attackDmg, then set the current attackDmg to the new attackDmg.
     *
     * @param attackDmg The amount of damage the weapon does.
     */
    public void setAttackDmg(int attackDmg) {
        this.attackDmg = Math.max(this.attackDmg, attackDmg);
    }

    public void setAttackDmg(double attackDmg) {
        this.attackDmg = Math.max(this.attackDmg, attackDmg);
    }

    /**
     * Returns true if the player's health is less than or equal to zero.
     *
     * @return The boolean value of whether the hp is less than or equal to 0.
     */
    public boolean isDead() {
        return this.hp <= 0;
    }

    /**
     * This function returns the value of the exp variable.
     *
     * @return The exp variable is being returned.
     */
    public int getExp() {
        return exp;
    }

    /**
     * This function sets the exp variable to the value of the exp parameter.
     *
     * @param exp The amount of experience the player has.
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * This function returns the number of coins the player has.
     *
     * @return The number of coins the player has.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * This function sets the coins variable to the value of the coins parameter.
     *
     * @param coins The amount of coins the player has.
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }


    public Item dropRoll(){
        if(drops != null){
            Random randy = new Random();
            if(randy.nextInt(100)  <=  drops.getChance() ){
                return drops.getDrop();
            }

        }
        return null;
    }

    public static void main(String[] args) {

    }
    public void printStats() {
        System.out.println("--------------------");
        System.out.println( "Current Health: " + hp + "\n"
                + "Attack Stat : [" + attackDmg + "]\n"
                + "Defence Stat : [" + Math.round(defence) );
        System.out.println("--------------------");
    }


}
