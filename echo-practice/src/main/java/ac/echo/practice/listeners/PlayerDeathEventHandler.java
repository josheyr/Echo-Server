package ac.echo.practice.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ac.echo.practice.EchoPractice;

public class PlayerDeathEventHandler implements Listener {
    
    EchoPractice main;

    public PlayerDeathEventHandler(EchoPractice main){
        this.main = main;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        
    }
}
