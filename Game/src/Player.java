import com.sun.source.tree.BreakTree;

public class Player {
    private String name ;
    private int hp;
    private int maxHP;
    private int defence;
    private int attackDmg;

    private  int attackDodge;



    public Player(String name  ) {
        this.name = name;
        this.hp = 100;
        this.maxHP = hp+10;
        this.defence = 5;
        this.attackDmg = 6;

    }
    public void attacked(int attack){
        int c = 100; // Constante
        int damage = c * attack / (c + this.defence);
        this.setHp(this.getHp()-damage);
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

    public void updateMaxHp(int maxHP) {
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
