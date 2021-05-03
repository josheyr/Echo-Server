package ac.echo.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.managers.KitManager;

public class BlockPlaceEventHandler implements Listener {
        
    EchoPractice echoPractice;

    public BlockPlaceEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        Player p = e.getPlayer();
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
        
        KitManager km = echoPractice.getKitManager();
        if(km.canPlaceBlocks(ep)){
            km.addPlacedBlock(ep, e.getBlock().getLocation());
        }else{
            
        }
    }
}
