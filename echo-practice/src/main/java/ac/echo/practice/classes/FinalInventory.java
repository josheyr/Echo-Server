package ac.echo.practice.classes;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

public class FinalInventory {
    double health;
    double hunger;

    String displayName;

    ItemStack[] contents;
    ItemStack[] armorContents;

    int longestCombo;
    int totalHits;

    boolean died;

    int potionCount;

    UUID nextInventory = null;
    UUID previousInventory = null;

    ArrayList<PotionEffect> effects = new ArrayList<>();

    public FinalInventory(Player p, Match match, boolean died){
        this.health = p.getHealth();
        this.hunger = p.getFoodLevel();

        this.effects = (ArrayList<PotionEffect>) p.getActivePotionEffects();

        this.displayName = p.getDisplayName();

        this.contents = p.getInventory().getContents();
        this.armorContents = p.getInventory().getArmorContents();
        
        this.died = died;

        int potions = 0;
        for(ItemStack itemStack : p.getInventory().getContents()){
            if(itemStack != null){
                if(itemStack.getType() == Material.POTION){
                    if(Potion.fromItemStack(itemStack).getType() == PotionType.INSTANT_HEAL){
                        potions++;
                    }
                }
            }
        }

        this.potionCount = potions;
    }

    public boolean getDied(){
        return died;
    }

    public String getDisplayName(){
        return displayName;
    }

    public double getHealth(){
        return this.health;
    }

    public double getHunger(){
        return this.hunger;
    }

    public ItemStack[] getContents(){
        return this.contents;
    }

    public ItemStack[] getArmorContents(){
        return this.armorContents;
    }

    public ArrayList<PotionEffect> getEffects(){
        return this.effects;
    }

    public void setNextInventory(UUID nextInventory){
        this.nextInventory = nextInventory;
    }

    public void setPreviousInventory(UUID previousInventory){
        this.previousInventory = previousInventory;
    }

    public int getPotionCount(){
        return potionCount;
    }

    public UUID getNextInventory(){
        return nextInventory;
    }

    public UUID getPreviousInventory(){
        return previousInventory;
    }
}
