

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
        this.maxHP = hp;
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
        System.out.println(this.getName()+" : ["+fill(hpProz,100,'#')+"] | "+100*hpProz+"%");
        System.out.println(this.getHp());
    }
}
