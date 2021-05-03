package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class EntityRegainHealthEventHandler implements Listener {

    EchoPractice echoPractice;

    public EntityRegainHealthEventHandler(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent e) {
        if(e.getEntity() instanceof Player){

            Player p = (Player)e.getEntity();

        if(e.getRegainReason() == RegainReason.SATIATED || e.getRegainReason() == RegainReason.REGEN){
            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if (!echoPractice.getKitManager().canRegenHealth(ep)) {
                e.setCancelled(true);
            }
        }
    }
    }
}
