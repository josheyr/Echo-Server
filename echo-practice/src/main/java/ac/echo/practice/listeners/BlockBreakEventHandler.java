package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class BlockBreakEventHandler implements Listener {
    
    EchoPractice echoPractice;

    public BlockBreakEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

        if(echoPractice.getKitManager().canBreakBlock(ep, e.getBlock().getLocation())){
            
        }else{
            if(!ep.getCanBreakBlocks()){
                e.setCancelled(true);
            }
            if(echoPractice.getKitManager().canPlaceBlocks(ep)){
                p.sendMessage("You can only break blocks placed in this match.");
                e.setCancelled(true);
            }
        }
    }
}
