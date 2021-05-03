package ac.echo.practice.classes;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.enums.MapType;
import ac.echo.practice.enums.QueueType;
import lombok.Getter;

public class Queue {
    @Getter Kit kit;
    @Getter MapType mapType;

    EchoPractice echoPractice;

    @Getter ArrayList<EchoPlayer> players = new ArrayList<>();
    @Getter boolean ranked = false;

    @Getter String fullName;
    @Getter QueueType queueType;

    @Getter String name;
    @Getter int id;

    public Queue(int id, String name, String fullName, QueueType queueType, Kit kit, MapType mapType, boolean ranked, EchoPractice echoPractice){
        this.id = id;
        this.name = name;
        this.kit = kit;
        this.mapType = mapType;
        this.ranked = ranked;
        this.fullName = fullName;
        this.queueType = queueType;
        this.echoPractice = echoPractice;
    }

    public void refreshQueue(){
        if(ranked == false){
            if(players.size() > 1){
                echoPractice.getMatchManager().createQueueMatch(players.get(0), players.get(1), this);
                
                Bukkit.getPlayer(players.get(0).getUuid()).sendMessage("Queue matched you with " + players.get(1).getPlayer().getDisplayName());
                Bukkit.getPlayer(players.get(1).getUuid()).sendMessage("Queue matched you with " + players.get(0).getPlayer().getDisplayName());
                players.remove(players.get(0));
                players.remove(players.get(0));
            }
        }
    }


    public void addPlayer(EchoPlayer player){
        players.add(player);
        refreshQueue();
    }

    public void removePlayer(EchoPlayer player){
        players.remove(player);
    }

    public boolean inQueue(EchoPlayer player){
        return players.contains(player);
    }
}
