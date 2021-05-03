package ac.echo.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ac.echo.core.EchoCore;

public class PlayerQuitEventHandler implements Listener {

    EchoCore echoCore;

    public PlayerQuitEventHandler(EchoCore echoCore) {
        this.echoCore = echoCore;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        echoCore.getSidebarManager().destroySidebar(p);
        //DONT DESTROY ECHO PLAYER HEAR BECAUSE IT NEEDS TO BE RAN AFTER THE STUFF IN THE GAMEMODE PLUGIN
    }
}
