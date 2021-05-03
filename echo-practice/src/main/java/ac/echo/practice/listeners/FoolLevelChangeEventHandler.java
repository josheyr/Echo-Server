package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class FoolLevelChangeEventHandler implements Listener{
    
    EchoPractice echoPractice;

    public FoolLevelChangeEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if(!echoPractice.getKitManager().isKitted(ep)){
                e.setCancelled(true);
            }
        }
    }
}
