package ac.echo.core.managers;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;

public class EchoPlayerManager {

    ArrayList<EchoPlayer> players = new ArrayList<>();

    public EchoPlayerManager() {

    }

    public void initializeEchoPlayers(){
        for(Player p : Bukkit.getOnlinePlayers()){
            EchoPlayer echoPlayer = new EchoPlayer(p.getUniqueId());
            
            players.add(echoPlayer);
            
        }
    }

    public void initializeEchoPlayer(UUID uuid){
        if(getEchoPlayer(uuid) == null){
            EchoPlayer echoPlayer = new EchoPlayer(uuid);   
            players.add(echoPlayer);
        }
    }

    public void destroyEchoPlayer(UUID uuid){
        getEchoPlayer(uuid).destroy();
        players.remove(getEchoPlayer(uuid));
    }

    public ArrayList<EchoPlayer> getEchoPlayers(){
        return players;
    }

    public EchoPlayer getEchoPlayer(UUID uuid){
        for(EchoPlayer p : players){
            if(p.getUuid() == uuid){
                return p;
            }
        }

        return null;
    }
}
