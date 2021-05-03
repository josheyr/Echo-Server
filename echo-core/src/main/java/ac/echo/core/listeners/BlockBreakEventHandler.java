package ac.echo.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ac.echo.core.EchoCore;

public class BlockBreakEventHandler implements Listener {
    
    EchoCore echoCore;

    public BlockBreakEventHandler(EchoCore echoCore){
        this.echoCore = echoCore;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        if(!echoCore.getEchoPlayerManager().getEchoPlayer(e.getPlayer().getUniqueId()).getCanBreakBlocks()){
            e.setCancelled(true);
        }
    }
}
