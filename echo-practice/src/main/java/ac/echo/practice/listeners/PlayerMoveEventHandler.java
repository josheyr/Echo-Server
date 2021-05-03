package ac.echo.practice.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class PlayerMoveEventHandler implements Listener {

    EchoPractice echoPractice;

    public PlayerMoveEventHandler(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().getBlock().isLiquid()) {
            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(e.getPlayer().getUniqueId());

            if (echoPractice.getKitManager().canDieInWater(ep)) {
                echoPractice.killPlayer(e.getPlayer());
            }

        }
    }
}
