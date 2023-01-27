

public class Player {
    private String name;
    private double hp;
    private double maxHP;
    private double defence;
    private double attackDmg;

    private int lvl;

    private int maxExp;

    private int money;

    private int maxlvl;
    private  int currentLvl;


    public Player(String name) {
        this.name = name;
        this.hp = 100;
        this.maxHP = hp;
        this.defence = 5;
        this.attackDmg = 6;
        this.money = 0;
        this.maxExp =100;
        this.maxlvl = 100;
    }

    public void attacked(double attack) {
        int c = 100; // Constante
        double damage = c * attack / (c + this.defence);
        this.setHp(Math.round(this.getHp() - damage));
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        if (hp <= maxHP && hp > 0) {
            this.hp = Math.max(0, hp);
        } else if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = maxHP;
        }
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void updateMaxHp(double maxHP) {
        this.maxHP = getHp();
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = Math.max(this.defence, defence);
    }

    public double getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(double attackDmg) {
        this.attackDmg = Math.max(this.attackDmg, attackDmg);
    }

    public boolean isDead() {
        if(hp <= 1){
            return true;
        }else {
            return false;
        }
    }

    public void printStats(){
        System.out.println("--------------------");
        System.out.println(name+" -> "+"LvL["+currentLvl+"]\n" +
                "Current Health: "+hp+"\n"
        +"Attack Stat : ["+attackDmg+"]\n"
        +"Defence Stat : ["+defence+"]\n"
        +"Current Money : ["+money+"]");
        System.out.println("--------------------");
    }

    public void printHealth() {
        double hpProz = 100 * (this.getHp() / this.getMaxHP());
        System.out.println(Barplot.drawBar("[ " + Barplot.drawLabel(this.name, 10) + " ]", Math.round((float) hpProz)));
        System.out.println(this.hp);
    }

    public int getMaxExp() {
        return maxExp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = Math.max(money,0);
    }

    public static void main(String[] args) {
        Player player = new Player("Test");
        player.printHealth();
        player.setHp(110);
        player.printHealth();

    }

    public void levelUp() {
        if (lvl >= maxExp) {
            lvl -= maxExp;
            this.maxExp *= 1.5;
            switch (Main.inputString("Choose a stat you wannt to + 1% : \nAttack | Defence | MaxHP").toLowerCase()) {
                case "attack" -> this.attackDmg *= 1.01;
                case "defence" -> this.defence *= 1.01;
                case "health" -> this.maxHP *= 1.01;
            }
            currentLvl++;
        }
    }
}
