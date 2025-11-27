package src.entities;

public class Attack {
    private int staminaCost;
    private int baseDamage;
    
    public Attack(int staminaCost, int baseDamage) {
        this.staminaCost = staminaCost;
        this.baseDamage = baseDamage;
    }
    
    public int getStaminaCost() {
        return staminaCost;
    }
    
    public int getBaseDamage() {
        return baseDamage;
    }
}