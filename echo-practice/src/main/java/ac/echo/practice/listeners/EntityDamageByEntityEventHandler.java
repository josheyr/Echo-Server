package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class EntityDamageByEntityEventHandler implements Listener {
    
    EchoPractice echoPractice;

    public EntityDamageByEntityEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player p = (Player)e.getDamager();

            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if(!echoPractice.getKitManager().isKitted(ep)){
                e.setCancelled(true);
            }
        }
    }
}
