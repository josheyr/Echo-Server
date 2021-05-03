package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ac.echo.practice.EchoPractice;

public class InventoryClickEventHandler implements Listener {

    EchoPractice echoPractice;

    public InventoryClickEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getWhoClicked() instanceof Player){
            Player p = (Player)e.getWhoClicked();
            
            
            if(echoPractice.getGuiManager().triggerInventoryClick(p.getUniqueId(), e.getSlot(), e.getCurrentItem())){
                e.setCancelled(true);
            }
        }
    }
}
