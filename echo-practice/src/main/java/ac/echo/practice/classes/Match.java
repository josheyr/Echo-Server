package ac.echo.practice.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.utils.ChatUtils;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.enums.MatchState;
import lombok.Getter;
import lombok.Setter;

public class Match {

    EchoPractice echoPractice;

    @Getter Map map;
    @Getter @Setter MatchState state = MatchState.REQUESTED;
    ArrayList<Team> teams;
    
    ArrayList<EchoPlayer> spectators = new ArrayList<>();

    Kit kit;

    @Getter EchoPlayer requester = null;
    @Getter EchoPlayer requestee = null;

    Queue queue = null;
    boolean queued = false;

    @Getter boolean countingDown = true;

    HashMap<EchoPlayer, UUID> deadPlayers = new HashMap<>();

    @Getter long startTime = 0;

    public Match(ArrayList<Team> teams, EchoPlayer requester, EchoPlayer requestee, Map map, Kit kit, EchoPractice echoPractice){
        Bukkit.getPlayer(requester.getUuid()).sendMessage("made it! map: " + map.getName() + ", team size: " + teams.size());

        this.kit = kit;
        this.requestee = requestee;
        this.requester = requester;
        this.teams = teams;
        this.map = map;
        this.echoPractice = echoPractice;
    }

    public Match(ArrayList<Team> teams, Map map, Kit kit, EchoPractice echoPractice){
        this.teams = teams;
        this.map = map;
        this.kit = kit;
        this.echoPractice = echoPractice;
        startMatch();
    }

    public Match(ArrayList<Team> teams, Map map, Kit kit, EchoPractice echoPractice, Queue queue, boolean ranked){
        this.queued = true;
        
        this.queue = queue;
        ranked = queue.isRanked();

        this.teams = teams;
        this.map = map;
        this.kit = kit;
        this.echoPractice = echoPractice;
        startMatch();
    }

    public void acceptRequest(){
        startMatch();
    }

    public void startMatch(){
        this.state = MatchState.INGAME;

        ArrayList<Location> spawnPoints = map.getSpawnPoints();

        map.setOccupied(true);

        int i = 0;
        for(Team team : teams) {
            if(spawnPoints.size() > 0){
                Location spawnPoint = spawnPoints.get(i);

                if(spawnPoints.size() > i){
                    spawnPoint = spawnPoints.get(i);
                    i = 0;
                }

                for(EchoPlayer ep : team.getPlayers()){
                    Player p = Bukkit.getPlayer(ep.getUuid());
                    echoPractice.getKitManager().applyKit(ep, kit);
                    echoPractice.getScoreboardManager().giveMatchScoreboard(p, this);

                    p.teleport(spawnPoint);
                }

                i++;
            }
        }

        for(i = 5; i > 0; i--){

            int count = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
                public void run() {
                        for(EchoPlayer p : getAlivePlayers()){
                                p.getPlayer().sendMessage(Integer.toString(count) + " seconds until match starts.");
                        }
                }
            }, 20L * (5 - i));
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
            public void run() {
                if(state != MatchState.FINISHED)
                    countingDown = false;
                    startTime = System.currentTimeMillis();
                for(EchoPlayer p : getAlivePlayers()){
                    p.getPlayer().sendMessage("Match has started!");
                }
            }
        }, 20L * 5);
    }

    public ArrayList<EchoPlayer> getAlivePlayers(){
        ArrayList<EchoPlayer> alivePlayers = new ArrayList<>();
        for(Team team : teams){
            for(EchoPlayer p : team.getPlayers()){
                if(!isDead(p)){
                    alivePlayers.add(p);
                }
            }
        }
        return alivePlayers;
    }

    public Team getTeam(EchoPlayer ep){
        for(Team team : teams){
            for(EchoPlayer p : team.getPlayers()){
                if(p == ep){
                    return team;
                }
            }
        }

        return null;
    }

    public ArrayList<EchoPlayer> getAliveOpponents(Team team){
        ArrayList<EchoPlayer> alivePlayers = new ArrayList<>();
        for(Team otherteam : teams){
            if(otherteam != team){
                for(EchoPlayer p : otherteam.getPlayers()){
                    if(!isDead(p)){
                        alivePlayers.add(p);
                    }
                }
            }
        }
        return alivePlayers;
    }

    public void addSpectator(EchoPlayer ep){
        spectators.add(ep);

        ep.getPlayer().sendMessage("added to spec");

        for(Team team : teams){
            for(EchoPlayer p : team.getPlayers()){
                if(!isDead(p)){
                    p.getPlayer().hidePlayer(ep.getPlayer());
                }
            }
        }

        for(EchoPlayer p : spectators){
            if(p.getPlayer() != null){
                p.getPlayer().showPlayer(ep.getPlayer());
            }
        }
    }

    public void removeSpectator(EchoPlayer ep){
        spectators.remove(ep);
    }

    public boolean hasSpectator(EchoPlayer ep){
        return spectators.contains(ep);
    }

    public void checkForWinners() {
        Bukkit.getLogger().info("checked");

        Team winningTeam = null;
        int aliveTeams = 0;

        for(Team team : teams){
            boolean teamAlive = false;
            for(EchoPlayer p : team.getPlayers()){
                if(!isDead(p)){
                    teamAlive = true;
                }
            }

            if(teamAlive){
                winningTeam = team;
                aliveTeams++;
            }
        }

        if(aliveTeams == 1){
            for(EchoPlayer ep : winningTeam.getPlayers()){
                Player p = Bukkit.getPlayer(ep.getUuid());
                p.sendMessage("you win!");



                if(!isDead(ep)){
                    setDead(p, true, false, true);                        
                }
            }

        
            UUID firstUuid = null;
            UUID lastUuid = null;

            for(Team team : teams){
                for(EchoPlayer ep : team.getPlayers()){

                    UUID finalInventoryUuid = deadPlayers.get(ep);

                    if(lastUuid != null){

                        FinalInventory lastFinalInventory = echoPractice.getFinalInventoryManager().getInventory(lastUuid);
                        lastFinalInventory.setNextInventory(finalInventoryUuid);


                        FinalInventory finalInventory = echoPractice.getFinalInventoryManager().getInventory(finalInventoryUuid);
                        finalInventory.setPreviousInventory(lastUuid);
                    }
                    if(firstUuid == null){
                        firstUuid = finalInventoryUuid;
                    }

                    lastUuid = finalInventoryUuid;

                    Player p = Bukkit.getPlayer(ep.getUuid());
                    if(p != null){
                        ChatUtils.sendJsonMessage(p, "{text:\"" + "\u00A7lClick to see ur inv" + "\",clickEvent:{action:run_command,value:\"" + "/inventory " + finalInventoryUuid + "\"}}");
                    }
                }
            }

            FinalInventory lastFinalInventory = echoPractice.getFinalInventoryManager().getInventory(lastUuid);
            lastFinalInventory.setNextInventory(firstUuid);

            FinalInventory firstFinalInventory = echoPractice.getFinalInventoryManager().getInventory(firstUuid);
            firstFinalInventory.setPreviousInventory(lastUuid);

            map.setOccupied(false);
            state = MatchState.FINISHED;

            Match match = this;

            Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
                public void run() {
                    ArrayList<EchoPlayer> spectatorList = new ArrayList<EchoPlayer>(spectators);
                    for(EchoPlayer spectator : spectatorList){
                        echoPractice.getMatchManager().removeSpectator(spectator, match);
                    }
                }
            }, 20L * 5);

          
            
        }
    }

    public void setDead(Player p, boolean dead, boolean actuallyDied, boolean dontdeapply){
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

        if(dead){
            if(p != null){
                for(EchoPlayer sp : spectators){
                    if(p.getPlayer() != null){
                        p.getPlayer().showPlayer(sp.getPlayer());
                    }
                }
                deadPlayers.put(ep, echoPractice.getFinalInventoryManager().createInventory(p, this, actuallyDied));
                if(!dontdeapply)
                    echoPractice.getKitManager().deapplyKit(p);

                echoPractice.getMatchManager().addSpectator(ep, this, false, dontdeapply);
            }
        }else{
            deadPlayers.remove(ep);
        }

        checkForWinners();
    }

    public boolean isDead(EchoPlayer ep){
        return deadPlayers.containsKey(ep);
    }

    public boolean isInMatch(EchoPlayer ep){
        for(Team team : teams){
            for(EchoPlayer p : team.getPlayers()){
                if(p == ep){
                    if(!isDead(p)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
