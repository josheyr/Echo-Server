package ac.echo.practice.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.enums.MapType;
import ac.echo.practice.utils.Utils;
import cc.nano.gspigot.knockback.KnockbackConfig;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class Kit {


    @Getter @Setter String name;
    @Getter @Setter int id;

    ArrayList<EchoPlayer> players = new ArrayList<>();

    HashMap<EchoPlayer, ArrayList<Location>> placedBlocks = new HashMap<>();
    
    ItemStack[] inventoryContents;
    ItemStack[] inventoryArmorContents;

    @Getter @Setter boolean diesInWater = false;
    @Getter @Setter boolean regenHealth = true;
    @Setter boolean comboMode = false;

    @Getter @Setter boolean canPlaceBlocks = false;

    @Getter @Setter MapType requiredMapType = MapType.NORMAL;

    @Getter ItemStack kitIcon;

    ArrayList<PotionEffect> potions = new ArrayList<>();

    public Kit(int id, String name, ItemStack kitIcon, ItemStack[] inventoryContents, ItemStack[] inventoryArmorContents, ArrayList<PotionEffect> potions){
        this.name = name;
        this.id = id;
        this.kitIcon = kitIcon;

        this.inventoryContents = inventoryContents;
        this.inventoryArmorContents = inventoryArmorContents;
        
        for(PotionEffect potion : potions){
            this.potions.add(potion);
        }

    }

    public void applyKit(EchoPlayer ep){
        players.add(ep);
        Player p = ep.getPlayer();

        
        if(comboMode){
            p.setMaximumNoDamageTicks(3);
            ((CraftPlayer)p).getHandle().setKbProfile(KnockbackConfig.getKbProfileByName("Combo"));
        }

        if(canPlaceBlocks){
            ep.setCanPlaceBlocks(true);
            ep.setCanBreakBlocks(true);
        }

        p.setFoodLevel(10);
        p.setExhaustion(1);
        p.setSaturation(1.0f);

        p.getInventory().setContents(inventoryContents);
        p.getInventory().setArmorContents(inventoryArmorContents);

        for(PotionEffect potion : potions){
           p.addPotionEffect(potion);
        }
    }

    public boolean hasPlayer(EchoPlayer player){
        if(players.contains(player)){
            return true;
        }
        return false;
    }

    public void removePlayer(EchoPlayer player){
        players.remove(player);
    }

    public ArrayList<EchoPlayer> getPlayers(){
        return this.players;
    }

    public void addPlacedBlock(EchoPlayer ep, Location loc){
        if(placedBlocks.get(ep) != null){
            ArrayList<Location> playersPlacedBlocks = placedBlocks.get(ep);
            playersPlacedBlocks.add(loc);

            placedBlocks.put(ep, playersPlacedBlocks);
        }else{
            ArrayList<Location> playersPlacedBlocks = new ArrayList<>();
            playersPlacedBlocks.add(loc);

            placedBlocks.put(ep, playersPlacedBlocks);
        }
    }

    public void deletePlacedBlocks(EchoPlayer ep){
        ArrayList<Location> playersPlacedBlocks = placedBlocks.get(ep);

        if(playersPlacedBlocks != null){

            for(Location loc : placedBlocks.get(ep)){
                Utils.drainBlocks(loc);
                loc.getBlock().setType(Material.AIR);
                
                Utils.removeCobbleAround(loc);
            }

            placedBlocks.remove(ep);
        }
    }

    public EchoPlayer getPlayerRelatedToBlock(Location loc){
        for(HashMap.Entry<EchoPlayer, ArrayList<Location>> e : placedBlocks.entrySet()) {
            for(Location l : e.getValue()){
                if(l.distance(loc) < 2){
                    return e.getKey();
                }
            }
        }

        return null;
    }

    public void addGeneratedBlock(Location loc) {
        EchoPlayer playerRelatedToBlock = getPlayerRelatedToBlock(loc);
        if(playerRelatedToBlock != null){
            addPlacedBlock(playerRelatedToBlock, loc);
        }
	}

	public boolean canBreakBlock(Location loc) {
        for(HashMap.Entry<EchoPlayer, ArrayList<Location>> e : placedBlocks.entrySet()) {
            if(e.getValue().contains(loc)){
                return true;
            }
        }

		return false;
	}
}
