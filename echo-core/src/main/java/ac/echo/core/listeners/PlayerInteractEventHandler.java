package ac.echo.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import ac.echo.core.EchoCore;
import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.utils.MathUtils;

public class PlayerInteractEventHandler implements Listener {
    
    EchoCore echoCore;

    public PlayerInteractEventHandler(EchoCore echoCore){
        this.echoCore = echoCore;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        EchoPlayer ep = echoCore.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
        

        if(e.getItem() != null){
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(e.getItem().getType() == Material.ENDER_PEARL){
                    if(echoCore.getPearlCooldownActive()){
                        if(ep.getPearlTimeAgo() > 15000){
                            ep.setLastPearlThrowtime();
                        }else{
                            e.setCancelled(true);
                            p.sendMessage("\u00A7cYou cannot use this for another \u00A7l" + MathUtils.round((float)((float)((float)15000 - (float)ep.getPearlTimeAgo())) / 1000, 2) + "\u00A7c seconds!");
                        }
                    }
                }
            }
        }
    }
}