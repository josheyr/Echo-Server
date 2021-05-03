package ac.echo.practice.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class ItemManager {
    EchoPractice echoPractice;

    HashMap<Pair<UUID, ItemStack>, Runnable> itemResponses = new HashMap<>();


    public ItemManager(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }


    public void giveQueueItems(Player p){
        p.getInventory().setContents(new ItemStack[36]);
        p.getInventory().setArmorContents(new ItemStack[4]);

        removeAllResponses(p);

        ItemStack dequeueItem = new ItemStack(Material.REDSTONE);
        ItemMeta dequeueItemM = dequeueItem.getItemMeta();

        dequeueItemM.setDisplayName("§cLeave Current Queue");
        dequeueItem.setItemMeta(dequeueItemM);

        p.getInventory().setItem(8, dequeueItem);

        itemResponses.put(Pair.of(p.getUniqueId(), dequeueItem), () -> {

            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
            echoPractice.getQueueManager().dequeuePlayer(ep);
            p.getInventory().setContents(new ItemStack[36]);
            p.getInventory().setArmorContents(new ItemStack[4]);

            echoPractice.getScoreboardManager().giveDefaultScoreboard(p);
            giveDefaultItems(p);
        });
    }

    public void givePartyItems(Player p){
        p.getInventory().setContents(new ItemStack[36]);
        p.getInventory().setArmorContents(new ItemStack[4]);

        removeAllResponses(p);

    }

    public void giveDefaultItems(Player p){
        p.getInventory().setContents(new ItemStack[36]);
        p.getInventory().setArmorContents(new ItemStack[4]);

        removeAllResponses(p);

        ItemStack swordItem = new ItemStack(Material.STONE_SWORD);
        ItemMeta swordItemM = swordItem.getItemMeta();

        swordItemM.setDisplayName("§aJoin Unranked Queue");
        swordItem.setItemMeta(swordItemM);

        p.getInventory().setItem(0, swordItem);

        itemResponses.put(Pair.of(p.getUniqueId(), swordItem), () -> {
            echoPractice.getGuiManager().showQueueUnrankedGui(p, null);
        });
    }

    public void giveSpectatorItems(Player p){
        removeAllResponses(p);

        p.getInventory().setContents(new ItemStack[36]);
        p.getInventory().setArmorContents(new ItemStack[4]);
    

        ItemStack exitItem = new ItemStack(Material.REDSTONE);
        ItemMeta exitItemM = exitItem.getItemMeta();

        exitItemM.setDisplayName("§cExit spectator");
        exitItem.setItemMeta(exitItemM);

        
        itemResponses.put(Pair.of(p.getUniqueId(), exitItem), () -> {

            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
            echoPractice.getMatchManager().removeSpectator(ep);
        });

        p.getInventory().setItem(8, exitItem);
    }

    public ArrayList<Pair<UUID, ItemStack>> getAllResponses(Player p){
        ArrayList<Pair<UUID, ItemStack>> responsesList = new ArrayList<>();

        for(HashMap.Entry<Pair<UUID, ItemStack>, Runnable> e : itemResponses.entrySet()){
            if(e.getKey().getKey().equals(p.getUniqueId())){
                responsesList.add(e.getKey());
            }
        }

        return responsesList;
    }

    public void removeAllResponses(Player p){
        for(Pair<UUID, ItemStack> pair : getAllResponses(p)){
            itemResponses.remove(pair);
        }
    }

    public boolean triggerItemClick(UUID uuid, ItemStack itemStack){
        Runnable itemResponse = itemResponses.get(Pair.of(uuid, itemStack));

        if(itemResponse != null){
            itemResponse.run();
            return true;
        }

        return false;
    }
}
