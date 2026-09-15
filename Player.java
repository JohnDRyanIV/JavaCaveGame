// Represents a player-controlled character.
// Players can attack, defend, heal, cast spell, and upgrade equipment/spells.
public class Player extends Character {

    // Used to assign unique IDs to players
    private static int NEXT_PLAYER_ID = 1;

    // Maximum mana value for the player
    private static final int MAX_MANA = 100;

    // Player-specific identifier
    private final int id;

    // Number of healing potions the player has
    private int potions;

    // Damage dealt when attacking
    private int weaponDamage;

    // Damage dealt when attacking with spell
    private int spellDamage;

    // Amount of mana player has
    private int mana;

    // Amount of mana restored per turn
    private int manaRecharge;

    // Cost of casting spell
    private int spellManaCost;

    // Creates a new player with starting stats
    public Player(int hp) {
        super(hp);
        this.id = NEXT_PLAYER_ID++;
        this.potions = 2;
        this.weaponDamage = 4;      // Starter weapon damage
        this.manaRecharge = 15;     // Starter mana recharge rate
        this.mana = 100;            // Player total mana
        this.spellManaCost = 50;    // Cost of casting spell
        this.spellDamage = 8;       // Damage of spell
    }

    // Returns the damage dealt by an attack
    public int attack() {
        return weaponDamage;
    }

    // Returns damage dealt by spell attack and reduces mana by cost
    public int spellAttack() {
        setMana(getMana() - this.spellManaCost);
        return spellDamage;
    }

    // Uses a healing potion to restore random HP
    public void usePotion() {

        // Prevent potion use if none remain
        if (potions <= 0) {
            System.out.println("Player " + id + " has no potions left!");
            return;
        }

        // Heal between 1 and 6 HP
        int heal = (int)(Math.random() * 6) + 1;
        hp += heal;
        potions--;

        System.out.println("Player " + id + " heals " + heal + " HP!");
    }

    // Getter for potion count
    public int getPotions() {
        return potions;
    }

    // Heals a fixed amount (used by rewards and round recovery)
    public void heal(int amount) {
        hp += amount;
    }

    // Adds a healing potion to the inventory
    public void addPotion() {
        potions++;
    }

    // Increases weapon damage (used for upgrades)
    public void upgradeWeapon(int amount) {
        weaponDamage += amount;
    }

    // Increases spell damage (used for upgrades)
    public void upgradeSpellDamage(int amount) {
        spellDamage += amount;
    }

    // Increases mana restored every turn by this amount
    public void upgradeManaRecharge(int amount) {
        setManaRecharge(getManaRecharge() + amount);    
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    // Player's current mana
    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    // Player's current mana recharge rate
    public int getManaRecharge() {
        return this.manaRecharge;
    }

    /**
     * Sets mana recharge rate
     * @param rechargeAmount - recharge rate that mana will be set to
     */
    public void setManaRecharge(int rechargeAmount) {
        this.manaRecharge = rechargeAmount;
    }

    /**
     * Cost of casting a spell
     * @return - mana cost of casting a spell
     */
    public int getSpellCost() {
        return this.spellManaCost;
    }

    /**
     * Increments player's mana by manaRechargeRate every turn, capped at 100
     */
    public void incrementManaValue() {

        int currentMana = getMana();
        currentMana += getManaRecharge();
        // Maximum mana is MAX_MANA, so this if statement ensures mana stays
        // at the maximum cap.
        if(currentMana >= MAX_MANA) {
            currentMana = MAX_MANA;
        }
        setMana(currentMana);
    }

    // Returns formatted weapon info for display
    public String getWeaponInfo() {
        return "Weapon Damage: " + weaponDamage;
    }

    // Returns formatted spell info for display
    public String getSpellInfo() {
        return "Spell Damage: " + spellDamage;
    }

    // Returns formatted mana recharge rate info for display
    public String getManaRechargeInfo() {
        return "Mana Recharge Rate: " + manaRecharge;
    }
    // Getter for player ID
        public int getId() {
            return id;
        }
        
    // Convenience method for increasing damage (for a later version...)
    /**public void increaseDamage(int amount) {
    *    weaponDamage += amount;
    *}
    **/
    
}
