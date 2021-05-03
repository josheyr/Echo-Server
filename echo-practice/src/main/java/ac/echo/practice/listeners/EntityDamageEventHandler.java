package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Match;

public class EntityDamageEventHandler implements Listener {
    
    EchoPractice echoPractice;

    public EntityDamageEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();

            if(e.getCause().equals(DamageCause.VOID) && e.getDamage() < 20.0){
                e.setCancelled(true);
                p.teleport(echoPractice.getSpawn());

                return;
            }
            // if spectator ignore all and take no damage

            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if(!echoPractice.getKitManager().isKitted(ep)){
                e.setCancelled(true);
                return;
            }else{
                Match match = echoPractice.getMatchManager().getIngameMatch(ep);
                
                if(match != null){
                    if(match.isCountingDown()){
                        e.setCancelled(true);
                    }
                }

                if(echoPractice.getKitManager().canDieInWater(ep)){
                    e.setDamage(0);
                }
            }

            if ((p.getHealth() - e.getFinalDamage()) <= 0){
                echoPractice.killPlayer(p);
                e.setCancelled(true);
            }
        }
    }
}
