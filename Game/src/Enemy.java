public class Enemy {
    private String name ;
    private int hp;
    private int maxHP;
    private int defence;
    private int attackDmg;

    private int[] resetData;
    public Enemy(String name, int hp, int defence, int attackDmg) {
        this.name = name;
        this.hp = hp;
        this.defence = defence;
        this.attackDmg = attackDmg;
        this.maxHP = hp;
        this.resetData = new int[]{hp, defence, attackDmg};
    }
    public void reset(){
        this.hp = this.resetData[0];
        this.defence = this.resetData[1];
        this.attackDmg = this.resetData[2];
        this.maxHP = this.hp;
    }

    public void attacked(int attack){
        int c = 100; // Constante
        int damage = c * attack / (c + this.defence);
        this.setHp(this.getHp()-damage);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0,hp);
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void updateMaxHp() {
        this.maxHP = getHp();
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = Math.max(this.defence,defence);
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = Math.max(this.attackDmg,attackDmg);
    }
    public boolean isDead(){
        return this.hp <= 0 ;
    }
}
