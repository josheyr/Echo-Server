package ac.echo.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import ac.echo.core.EchoCore;

public class BlockPlaceEventHandler implements Listener {
        
    EchoCore main;

    public BlockPlaceEventHandler(EchoCore main){
        this.main = main;
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        if(!main.getEchoPlayerManager().getEchoPlayer(e.getPlayer().getUniqueId()).getCanPlaceBlocks()){
            e.setCancelled(true);
        }
    }
}
