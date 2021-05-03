package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.managers.EchoPlayerManager;
import ac.echo.practice.EchoPractice;

public class PlayerQuitEventHandler implements Listener {

    EchoPractice echoPractice;

    public PlayerQuitEventHandler(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent e) {
        EchoPlayerManager echoPlayerManager = echoPractice.getEchoPlayerManager();
        Player p = e.getPlayer();
        EchoPlayer ep = echoPlayerManager.getEchoPlayer(p.getUniqueId());

        echoPractice.getMatchManager().removeSpectator(ep);

        echoPractice.killPlayer(p);
        echoPractice.getPartyManager().removeFromParty(ep);
        echoPractice.getQueueManager().dequeuePlayer(ep);
        echoPlayerManager.destroyEchoPlayer(p.getUniqueId());
    }
}
