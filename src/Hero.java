import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hero {
    protected String name, role;
    protected int attack, protection, maxhealth, health, speed;
    protected int[] damage;
    protected ArrayList<Hero> gang;
    protected Vector2 position;
    protected int rnd;

    public Hero(ArrayList<Hero> gang, String name, String role, int attack, int protection, int[]damage, int health, int speed, int x, int y){
        this.gang = gang;
        this.name = name;
        this.role = role;
        this.attack = attack;
        this.protection = protection;
        this.damage =damage;
        this.maxhealth = health;
        this.health = maxhealth - new Random().nextInt(maxhealth);
        this.speed = speed;
        this.position = new Vector2(x, y);
    }

    public String getInfo(){
       return role + " " + name + " [hp] " + health + "/" + maxhealth;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(int x, int y){
        position.x = x;
        position.y = y;
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int value){
        health = value;
    }

    public int getSpeed(){
        return speed;
    }

    public int getRnd(){
        return rnd;
    }

    public void setRnd()
    {
        Random random = new Random();
        rnd = random.nextInt(0, 1000); //Main.GANG_SIZE);
    }

    public int getMaxHealth(){
        return maxhealth;
    }

    protected void getDamage(float attackPower) {
        this.health -= attackPower;
        if (this.health < 0) {
            this.health = 0;
        } else if (this.health > maxhealth) {
            this.health = maxhealth;
        }
        //System.out.println(this.name + " " + this.health);
    }

    public Hero makeNewHero(ArrayList<Hero> heroList, int indx) {
        Hero oldHero = heroList.get(indx);
        Hero newHero;
        // проверим сторону героя и генерим только из его типа персонажей (кроме крестьянина - он общий)
         switch(oldHero.getRole()) {
            case "Peasant" -> {
                newHero = new Peasant(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
            }
            case "Rogue", "Sniper", "Warlock" -> {
                switch (new Random().nextInt(3)) {
                    case 0 -> newHero = new Rogue(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
                    case 1 -> newHero = new Sniper(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
                    default ->
                            newHero = new Warlock(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
                };
            }
            case "Spearman", "Crossbowman", "Monk" -> {
                switch (new Random().nextInt(3)) {
                    case 0 -> newHero = new Spearman(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
                    case 1 -> newHero = new Crossbowman(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
                    default ->
                            newHero = new Monk(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
                };
            }
            default -> newHero = new Peasant(heroList, oldHero.getName(), oldHero.getPosition().x, oldHero.getPosition().y);
        }
        return newHero;
    }

    public void step(ArrayList<Hero> heroList) {

    }

    @Override
    public String toString(){
        return ("Name: " + name + ".  Role: " + role + ".  Attack: " + attack + ".  Protection: " + protection +
                ".  Damage: " + Arrays.toString (damage) + ".  Health: " + health +
                ".  Speed: " + speed + ".  Random: " + rnd);
    }

}

