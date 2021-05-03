package ac.echo.practice.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.utils.MathUtils;

import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.FinalInventory;
import ac.echo.practice.classes.Queue;
import ac.echo.practice.enums.QueueType;
import ac.echo.practice.utils.ItemUtils;

public class GuiManager {

    EchoPractice echoPractice;

    HashMap<Triple<UUID, Integer, ItemStack>, Runnable> guiResponses = new HashMap<>();

    public GuiManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    
    public ArrayList<Triple<UUID, Integer, ItemStack>> getAllResponses(Player p){
        ArrayList<Triple<UUID, Integer, ItemStack>> responsesList = new ArrayList<>();

        for(HashMap.Entry<Triple<UUID, Integer, ItemStack>, Runnable> e : guiResponses.entrySet()){
            if(e.getKey().getLeft().equals(p.getUniqueId())){
                responsesList.add(e.getKey());
            }
        }

        return responsesList;
    }

    public void removeAllResponses(Player p){
        for(Triple<UUID, Integer, ItemStack> pair : getAllResponses(p)){
            guiResponses.remove(pair);
        }
    }


    public void showQueueUnrankedGui(Player p, Inventory openedInventory){
        removeAllResponses(p);

        Inventory inventory;

        String guiTitle = "unranked queues";

        boolean openNew = false;


        if (openedInventory != null) {
            inventory = openedInventory;
            ItemUtils.updateInventoryTitle(p, guiTitle);
        } else {
            inventory = Bukkit.createInventory(p, 2 * 9, guiTitle);
            openNew = true;
        }

        QueueManager queueManager = echoPractice.getQueueManager();

        int i = 0;
        for(Queue queue : queueManager.getQueues()){
            if(queue.getQueueType() == QueueType.UNRANKED){
                ItemStack queueItem = queue.getKit().getKitIcon();
                ItemMeta queueItemM = queueItem.getItemMeta();

                queueItemM.setDisplayName(queue.getKit().getName());
                queueItemM.setLore(Arrays.asList(
                    Integer.toString(queue.getPlayers().size()) + " currently in queue"
                ));

                queueItem.setItemMeta(queueItemM);

                inventory.setItem(i, queueItem);

                int slot = i;

                guiResponses.put(Triple.of(p.getUniqueId(), i, queueItem), () -> {
                    EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

                    
                    if(!queueManager.isInQueue(ep)){
                        guiResponses.remove(Triple.of(p.getUniqueId(), slot, queueItem));

                        
                        queueManager.queuePlayer(ep, queue);
                        p.sendMessage("Joined " + queue.getKit().getName());
                        p.closeInventory();
                    }else{
                        p.sendMessage("already in a queue!");
                    }
                });

                i++;
            }
        }


        p.updateInventory();
        if (openNew)
            p.openInventory(inventory);
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
            public void run() {
                if(p != null){
                    if(p.getOpenInventory().getTitle() == inventory.getTitle()){
                        showQueueUnrankedGui(p, inventory);
                    }
                }
            }
        }, 20L);
    }

    public void showInventoryGui(Player p, FinalInventory finalInventory, Inventory openedInventory) {
        removeAllResponses(p);

        Inventory inventory;

        String guiTitle = finalInventory.getDisplayName() + "§e's inventory ";

        boolean openNew = false;

        // fromInventory = false;

        if (openedInventory != null) {
            inventory = openedInventory;
            ItemUtils.updateInventoryTitle(p, guiTitle);
        } else {
            inventory = Bukkit.createInventory(p, 6 * 9, guiTitle);
            openNew = true;
        }

        int i = 27;
        for (ItemStack item : finalInventory.getContents()) {
            inventory.setItem(i, item);
            i++;

            if (i == 36) {
                i = 0;
            }
        }

        i = 39;
        for (ItemStack item : finalInventory.getArmorContents()) {
            inventory.setItem(i, item);
            i--;
        }

        ItemStack healthItem = (finalInventory.getDied() ? new ItemStack(Material.SKULL_ITEM, (int) 0, (byte) 0)
                : new ItemStack(Material.SKULL_ITEM, (int) (finalInventory.getHealth() / 2), (byte) 3));
        ItemMeta healthItemM = healthItem.getItemMeta();

        healthItemM.setDisplayName("§eHealth: §d"
                + (finalInventory.getDied() ? "0.0" : (MathUtils.round(finalInventory.getHealth(), 1) / 2))
                + " / 10 ❤️");

        healthItem.setItemMeta(healthItemM);
        inventory.setItem(47, healthItem);

        ItemStack hungerItem = new ItemStack(Material.COOKED_BEEF, (int) (finalInventory.getHunger() / 2));
        ItemMeta hungerItemM = hungerItem.getItemMeta();

        hungerItemM.setDisplayName("§eHunger: §d" + (MathUtils.round(finalInventory.getHunger(), 1) / 2) + " / 10 ❤️");

        hungerItem.setItemMeta(hungerItemM);
        inventory.setItem(48, hungerItem);

        ItemStack potionsItem = new ItemStack(Material.POTION, finalInventory.getPotionCount(), (byte) 373);

        PotionMeta potionsItemM = (PotionMeta) potionsItem.getItemMeta();
        Potion potionsItemP = new Potion(1);

        potionsItemP.setSplash(true);
        potionsItemP.setType(PotionType.INSTANT_HEAL);
        potionsItemP.apply(potionsItem);

        potionsItemM.setDisplayName("§ePotions: §d" + finalInventory.getPotionCount());

        potionsItem.setItemMeta(potionsItemM);
        inventory.setItem(49, potionsItem);

        if (finalInventory.getNextInventory() != null) {
            ItemStack nextItem = new ItemStack(Material.ARROW);
            ItemMeta nextItemM = nextItem.getItemMeta();
            nextItemM.setDisplayName("§eview next inventory");
            // healthItemM.setLore(Arrays.asList(
            // "§7Return to main kit menu."
            // ));
            nextItem.setItemMeta(nextItemM);
            inventory.setItem(53, nextItem);

            guiResponses.put(Triple.of(p.getUniqueId(), 53, nextItem), () -> {
                guiResponses.remove(Triple.of(p.getUniqueId(), 53, nextItem));

                showInventoryGui(p,
                        echoPractice.getFinalInventoryManager().getInventory(finalInventory.getNextInventory()),
                        inventory);
            });
        }

        if (finalInventory.getPreviousInventory() != null) {
            ItemStack previousItem = new ItemStack(Material.ARROW);
            ItemMeta previousItemM = previousItem.getItemMeta();
            previousItemM.setDisplayName("§eview previous inventory");
            // healthItemM.setLore(Arrays.asList(
            // "§7Return to main kit menu."
            // ));
            previousItem.setItemMeta(previousItemM);

            inventory.setItem(45, previousItem);

            guiResponses.put(Triple.of(p.getUniqueId(), 45, previousItem), () -> {
                guiResponses.remove(Triple.of(p.getUniqueId(), 45, previousItem));

                showInventoryGui(p,
                        echoPractice.getFinalInventoryManager().getInventory(finalInventory.getPreviousInventory()),
                        inventory);
            });
        }

        p.updateInventory();
        if (openNew)
            p.openInventory(inventory);
    }

    public boolean triggerInventoryClick(UUID uuid, int slot, ItemStack itemStack) {
        Runnable guiResponse = guiResponses.get(Triple.of(uuid, slot, itemStack));

        if (guiResponse != null) {
            guiResponse.run();
            return true;
        }

        return false;
    }
}
