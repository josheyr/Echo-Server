package ac.echo.practice.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ac.echo.practice.EchoPractice;

public class PlayerJoinEventHandler implements Listener {
    
    EchoPractice echoPractice;

    public PlayerJoinEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        e.getPlayer().teleport(echoPractice.getSpawn());
        
        echoPractice.getKitManager().deapplyKit(e.getPlayer());
        echoPractice.getItemManager().giveDefaultItems(e.getPlayer());
        echoPractice.getScoreboardManager().giveDefaultScoreboard(e.getPlayer());
        
    }
}
