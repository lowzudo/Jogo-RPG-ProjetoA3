package src.entities;

import java.util.*;
import src.utils.GameUtils;

public class Player {
    private String name;
    private String className;
    private int level;
    private int strength;
    private int health;
    private int maxHealth;
    private int stamina;
    private int maxStamina;
    private int potions;
    
    private int agility;
    private int resistance;
    private int perception;
    
    private Map<String, Attack> attacks;
    private List<String> attackNames;
    
    public Player(String name, String className) {
        this.name = name;
        this.className = className;
        this.level = 1;
        this.potions = 3;
        this.initializeClass();
        this.initializeAttacks();
    }
    
    private void initializeClass() {
        switch(this.className) {
            case "Mago":
                this.strength = 3;
                this.health = this.maxHealth = 7;
                this.stamina = this.maxStamina = 15;
                this.agility = 4;
                this.resistance = 2;
                this.perception = 6;
                break;
            case "Espadachim":
                this.strength = 5;
                this.health = this.maxHealth = 6;
                this.stamina = this.maxStamina = 12;
                this.agility = 5;
                this.resistance = 3;
                this.perception = 4;
                break;
            case "Berserker":
                this.strength = 4;
                this.health = this.maxHealth = 10;
                this.stamina = this.maxStamina = 14;
                this.agility = 3;
                this.resistance = 5;
                this.perception = 3;
                break;
            default:
                throw new IllegalArgumentException("Classe inválida.");
        }
    }
    
    private void initializeAttacks() {
        attacks = new HashMap<>();
        attackNames = new ArrayList<>();
        
        switch(this.className) {
            case "Mago":
                attacks.put("Bola De Fogo", new Attack(3, 2));
                attacks.put("Raio Congelante", new Attack(4, 3));
                attacks.put("Tempestade Arcana", new Attack(6, 4));
                break;
            case "Espadachim":
                attacks.put("Corte Rapido", new Attack(2, 1));
                attacks.put("Investida", new Attack(3, 2));
                attacks.put("Shishi Sonson", new Attack(5, 3));
                break;
            case "Berserker":
                attacks.put("Golpe Brutal", new Attack(4, 3));
                attacks.put("Furia Selvagem", new Attack(5, 4));
                attacks.put("Terremoto", new Attack(7, 5));
                break;
        }
        attackNames.addAll(attacks.keySet());
    }
    
    public String getName() { return name; }
    public String getClassName() { return className; }
    public int getLevel() { return level; }
    public int getStrength() { return strength; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getStamina() { return stamina; }
    public int getMaxStamina() { return maxStamina; }
    public int getPotions() { return potions; }
    public Map<String, Attack> getAttacks() { return attacks; }
    public List<String> getAttackNames() { return attackNames; }
    
    public int getAgility() { return agility; }
    public int getResistance() { return resistance; }
    public int getPerception() { return perception; }
    
    public void setHealth(int health) { this.health = health; }
    public void setStamina(int stamina) { this.stamina = stamina; }
    public void setPotions(int potions) { this.potions = potions; }
    public void setStrength(int strength) { this.strength = strength; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public void setMaxStamina(int maxStamina) { this.maxStamina = maxStamina; }
    
    public void setAgility(int agility) { this.agility = agility; }
    public void setResistance(int resistance) { this.resistance = resistance; }
    public void setPerception(int perception) { this.perception = perception; }
    public void setLevel(int level) { this.level = level; }
    
    public void levelUp() {
        int oldStrength = strength;
        int oldHealth = maxHealth;
        int oldStamina = maxStamina;
        int oldAgility = agility;
        int oldResistance = resistance;
        int oldPerception = perception;
        
        level++;
        strength += 2;
        maxHealth += 3;
        maxStamina += 5;
        agility += 1;
        resistance += 1;
        perception += 1;
        health = maxHealth;
        stamina = maxStamina;
        
        GameUtils.dramaticPrint("=".repeat(60));
        if (level <= 5) {
            GameUtils.dramaticPrint("Você realmente está evoluindo, fracassado! Você alcançou o nivel " + level + ".");
        } else if (level > 5 && level <= 10) {
            GameUtils.dramaticPrint("Você está se saindo bem, mas ainda é um fracassado! Você alcançou o nivel " + level + ".");
        } else {
            GameUtils.dramaticPrint("Quem diria que você fosse chegar até aqui, o admiro garoto. Você conseguiu ultrapassar os limites do jogo e está acima do nivel máximo. Um verdadeiro prodígio entre os fracassados. Alegre-se, você se tornou uma lenda!");
        }
        
        GameUtils.dramaticPrint("-".repeat(40));
        GameUtils.dramaticPrint("Força: " + oldStrength + " -> " + strength + " (+2)");
        GameUtils.dramaticPrint("Vida: " + oldHealth + " -> " + maxHealth + " (+3)");
        GameUtils.dramaticPrint("Stamina: " + oldStamina + " -> " + maxStamina + " (+5)");
        GameUtils.dramaticPrint("Agilidade: " + oldAgility + " -> " + agility + " (+1)");
        GameUtils.dramaticPrint("Resistência: " + oldResistance + " -> " + resistance + " (+1)");
        GameUtils.dramaticPrint("Percepção: " + oldPerception + " -> " + perception + " (+1)");
        GameUtils.dramaticPrint("=".repeat(60));
    }
    
    public void rest() {
        health = maxHealth;
        stamina = maxStamina;
        GameUtils.dramaticPrint("Você descansou e recuperou toda a vida e stamina!");
    }
    
    public void showAttacks() {
        GameUtils.dramaticPrint("\n=== SEUS ATAQUES ===");
        for (String ataque : attackNames) {
            Attack info = attacks.get(ataque);
            int danoReal = info.getBaseDamage() + this.strength;
            GameUtils.dramaticPrint(ataque + " | Dano: " + danoReal + " | Stamina: " + info.getStaminaCost());
        }
    }   

    public void addAttack(String name, int staminaCost, int baseDamage) {
        this.attacks.put(name, new Attack(staminaCost, baseDamage));
        if (!this.attackNames.contains(name)) {
            this.attackNames.add(name);
        }
    }
}