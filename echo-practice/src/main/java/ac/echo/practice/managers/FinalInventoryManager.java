package ac.echo.practice.managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.FinalInventory;
import ac.echo.practice.classes.Match;

public class FinalInventoryManager {
    
    HashMap<UUID, FinalInventory> finalInventories = new HashMap<>();

    EchoPractice echoPractice;

    public FinalInventoryManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }


    public UUID createInventory(Player p, Match match, boolean died){
        return addInventory(new FinalInventory(p, match, died));
    }

    public void setInventoryNext(UUID uuid, UUID nextUuid){
        getInventory(uuid).setNextInventory(nextUuid);
    }

    public UUID addInventory(FinalInventory inventory){
        UUID uuid = UUID.randomUUID();

        finalInventories.put(uuid, inventory);

        return uuid;
    }

    public FinalInventory getInventory(UUID uuid){
        return finalInventories.get(uuid);
    }
}
