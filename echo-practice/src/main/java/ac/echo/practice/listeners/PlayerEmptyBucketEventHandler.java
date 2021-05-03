package ac.echo.practice.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.managers.KitManager;

public class PlayerEmptyBucketEventHandler implements Listener {

    EchoPractice echoPractice;

    public PlayerEmptyBucketEventHandler(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onPlayerEmptyBucket(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

        Location accusedLocation = e.getBlockClicked().getRelative(e.getBlockFace()).getLocation();
        
        KitManager km = echoPractice.getKitManager();
        if(km.canPlaceBlocks(ep)){
            if(accusedLocation.getBlock().getType() == Material.WATER || accusedLocation.getBlock().getType() == Material.AIR){
                km.addPlacedBlock(ep, accusedLocation);
            }
        }else{
            e.setCancelled(true);
        }
    }
    
}
