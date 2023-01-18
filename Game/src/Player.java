

public class Player {
    private String name;
    private double hp;
    private double maxHP;
    private double defence;
    private double attackDmg;

    private int lvl;

    private int maxLvL;

    private int attackDodge;


    public Player(String name) {
        this.name = name;
        this.hp = 100;
        this.maxHP = hp;
        this.defence = 5;
        this.attackDmg = 6;

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
        return this.hp <= 0;
    }

    public String fill(double num, int max, char ch) {
        StringBuilder ret = new StringBuilder();
        if (max - num == 1) {
            ret.append(String.valueOf(ch).repeat(Math.max(0, max)));
        }
        for (int i = 0; i < max - num; i++) {
            ret.append(ch);
        }
        return ret.toString();
    }

    public String fill(String num, int max, char ch) {
        StringBuilder ret = new StringBuilder();
        if (max - num.length() == 1) {
            ret.append(String.valueOf(ch).repeat(Math.max(0, max)));
        }
        ret.append(String.valueOf(ch).repeat(Math.max(0, max - num.length())));
        return ret.toString();
    }

    public void printHealth() {
        double hpProz = 100 * (this.getHp() / this.getMaxHP());
        System.out.println(Barplot.drawBar("[ " + Barplot.drawLabel(this.name, 10) + " ]", Math.round((float) hpProz)));
    }

    public static void main(String[] args) {
        Player player = new Player("Test");
        player.printHealth();
        player.setHp(110);
        player.printHealth();

    }

    public int getMaxLvL() {
        return maxLvL;
    }

    public int getLvl() {
        return lvl;
    }

    public void levelUp() {
        if (lvl >= maxLvL) {
            lvl -= maxLvL;
            this.maxLvL *= 1.5;
            switch (Main.inputString("Choose a stat you wannt to + 1% : \nAttack | Defence | MaxHP").toLowerCase()) {
                case "attack" -> this.attackDmg *= 1.01;
                case "defence" -> this.defence *= 1.01;
                case "health" -> this.maxHP *= 1.01;
            }
        }
    }
}
