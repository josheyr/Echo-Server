package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import ac.echo.practice.EchoPractice;

public class PlayerInteractEventHandler implements Listener {
    
    EchoPractice echoPractice;

    public PlayerInteractEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(echoPractice.getItemManager().triggerItemClick(p.getUniqueId(), p.getItemInHand())){
                e.setCancelled(true);
            }
        }
    }
}
