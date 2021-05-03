package ac.echo.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ac.echo.core.EchoCore;

public class PlayerJoinEventHandler implements Listener {
    
    EchoCore echoCore;

    public PlayerJoinEventHandler(EchoCore echoCore){
        this.echoCore = echoCore;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");

        Player p = e.getPlayer();
        echoCore.getEchoPlayerManager().initializeEchoPlayer(p.getUniqueId());
    }
}
