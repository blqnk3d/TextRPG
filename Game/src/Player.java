

public class Player {
    private String name ;
    private double hp;
    private double maxHP;
    private int defence;
    private int attackDmg;

    private int lvl;

    private int maxLvL;

    private  int attackDodge;



    public Player(String name  ) {
        this.name = name;
        this.hp = 100;
        this.maxHP = hp+5;
        this.defence = 5;
        this.attackDmg = 6;

    }
    public void attacked(int attack){
        int c = 100; // Constante
        int damage = c * attack / (c + this.defence);
        this.setHp(this.getHp()-damage);
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        if (hp <= maxHP && hp >0){
            this.hp = Math.max(0,hp);
        } else if (hp < 0 ) {
            this.hp = 0;
        }else {
            this.hp = maxHP;
        }
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void updateMaxHp(double maxHP) {
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
    public String fill(double num,int max,char ch ){
        String ret = "";
        for (int i = 0; i < max-num; i++) {
            ret = ret+ch;
        }
        return  ret;
    }

    public void printHealth(){
        System.out.println(this.getHp()+"  /  "+this.getMaxHP());
        double hpProz = this.getHp() / this.getMaxHP();
        System.out.println(this.getName()+" : ["+fill(hpProz*100,100,'#')+"] | "+Math.round(100*hpProz)+"%");
        System.out.println(this.getHp());
    }
}
