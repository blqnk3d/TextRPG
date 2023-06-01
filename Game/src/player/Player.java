package player;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Player {
    private final String name;
    private double hp;
    private double maxHP;
    private double defence;
    private double attackDmg;

    private int lvl;

    private int maxExp;
    private int currentExp;

    private int money;

    private int maxlvl;
    private int currentLvl;


    public Player(String name) {
        this.name = name;
        this.hp = 100;
        this.maxHP = hp;
        this.defence = 5;
        this.attackDmg = 6;
        this.money = 0;
        this.maxExp = 100;
        this.maxlvl = 100;
    }

    /**
     * The damage is equal to the attack multiplied by a constant divided by the sum of the constant and the defence.
     *
     * @param attack The attack of the attacker
     */
    public void attacked(double attack) {
        int c = 100; // Constante
        double damage = c * attack / (c + this.defence);
        this.setHp(Math.round(this.getHp() - damage));
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
     * If the hp is less than or equal to the maxHP and greater than 0, then set the hp to the max of 0 and hp. Otherwise,
     * if the hp is less than 0, set the hp to 0. Otherwise, set the hp to maxHP
     *
     * @param hp The current health of the player.
     */
    public void setHp(double hp) {
        if (hp <= maxHP && hp > 0) {
            this.hp = Math.max(0, hp);
        } else if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = maxHP;
        }
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
     *
     * @param maxHP The maximum HP of the entity.
     */
    public void updateMaxHp(double maxHP) {
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
     * @param defence The defence of the player.
     */
    public void setDefence(double defence) {
        this.defence = Math.max(this.defence, defence);
    }

    /**
     * > This function returns the attack damage of the player
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
    public void setAttackDmg(double attackDmg) {
        this.attackDmg = Math.max(this.attackDmg, attackDmg);
    }

    /**
     * Returns true if the player's health is less than or equal to 1.
     *
     * @return The boolean value of whether the hp is less than or equal to 1.
     */
    public boolean isDead() {
        return hp <= 1;
    }

    /**
     * This function prints out the stats of the player
     */
    public void printStats() {
        StringBuilder ret = new StringBuilder();
        ret.append("=================================================\n");
        ret.append(String.format("| Name:%40s |\n",name));
        ret.append(String.format("| Class:%39s |\n","CLASS"));
        ret.append(String.format("| Level:%39s |\n",lvl));
        ret.append(String.format("| Health:%38s |\n",Math.round(maxHP)+"/"+Math.round(hp)));
        ret.append(String.format("| Attack:%38s |\n",Math.round(attackDmg)));
        ret.append(String.format("| Defense:%37s |\n",Math.round(defence)));
        ret.append(String.format("| Exp Pnts:%36s |\n",Math.round(maxExp)+"/"+Math.round(currentExp)));
        ret.append(String.format("| Gold:%40s |\n",Math.round(money)));

    /* /!
        ret.append("| Weapon:   Sword of 1000 Truths  +125 attack  |\n");
        ret.append("| Chest:    Blinding Breastplate  +90  defense |\n");
        ret.append("| Helmet:   Crown of Death        +64  defense |\n");
        ret.append("| Feet:     Hell's Sabatons       +60  defense |\n");
     */
        ret.append("=================================================");
        System.out.println(ret);
    }

    /**
     * It prints a barplot of the current health of the player
     */
    public void printHealth() {
        double hpProz = 100 * (this.getHp() / this.getMaxHP());
        System.out.println((Inventory.fill(Barplot.drawBar("[ " + centerString(10, this.name) + " ]", Math.round((float) hpProz)), 116, ' ')) + "|   [" + this.hp + "]");
    }

    /**
     * This function returns the maxExp variable.
     *
     * @return The maxExp variable is being returned.
     */
    public int getMaxExp() {
        return maxExp;
    }

    /**
     * This function returns the value of the variable lvl
     *
     * @return The value of the variable lvl.
     */
    public int getLvl() {
        return lvl;
    }

    /**
     * This function returns the value of the money variable.
     *
     * @return The money variable is being returned.
     */
    public int getMoney() {
        return money;
    }

    /**
     * If the money is less than 0, set it to 0.
     *
     * @param money The amount of money the player has.
     */
    public void setMoney(int money) {
        this.money = Math.max(money, 0);
    }
    
    /**
     * If the player's level is greater than or equal to the maximum level, then the player's level is set to the remainder
     * of the player's level divided by the maximum level, the maximum level is multiplied by 1.5, the player's attack
     * damage, defence, or maximum health is multiplied by 1.01 depending on the player's choice, and the player's current
     * level is incremented by 1
     */
    public void levelUp() {
        if (lvl >= this.maxlvl) {
            lvl -= maxlvl;
            this.maxlvl *= 1.5;
            switch (inputString("Choose a stat you want to + 1% : \nAttack | Defence | MaxHP : ").toLowerCase()) {
                case "attack" -> this.attackDmg *= 1.01;
                case "defence" -> this.defence *= 1.01;
                case "health" -> this.maxHP *= 1.01;
            }
            currentLvl++;
        }
    }

    /**
     * It takes a string and a width, and returns a string that is the original string centered in a string of the given
     * width
     *
     * @param width The width of the entire string
     * @param s     The string to be centered
     * @return A string that is centered in a string of a given width.
     */
    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
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
}
