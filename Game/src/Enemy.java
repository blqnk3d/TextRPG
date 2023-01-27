public class Enemy {
    private final String name;
    private double hp;
    private double maxHP;
    private double defence;
    private double attackDmg;

    private int exp;
    private int coins;
    private final int[] resetData;

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

    public void reset() {
        this.hp = this.resetData[0];
        this.defence = this.resetData[1];
        this.attackDmg = this.resetData[2];
        this.maxHP = this.hp;
    }

    public void attacked(double attack) {
        int c = 100; // Constante
        double damage = c * attack / (c + this.defence);
        this.setHp(this.getHp() - damage);
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = Math.max(0, hp);
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void updateMaxHp() {
        this.maxHP = getHp();
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = Math.max(this.defence, defence);
    }

    public double getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = Math.max(this.attackDmg, attackDmg);
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
