package ac.echo.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;

import ac.echo.practice.EchoPractice;

public class PotionSplashEventHandler implements Listener {

    EchoPractice echoPractice;

    public PotionSplashEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    void onPotionSplash(PotionSplashEvent e) {
        if(e.getEntity().getShooter() instanceof Player){
            Player p = (Player)e.getEntity().getShooter();

            if(p.isSprinting() && e.getIntensity(p) > 0.5D){
                e.setIntensity(p, 1.0D);
            }
        }
    }
}
