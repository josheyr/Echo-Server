package ac.echo.practice.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Kit;
import ac.echo.practice.classes.Map;
import ac.echo.practice.classes.Match;
import ac.echo.practice.classes.Queue;
import ac.echo.practice.classes.Team;
import ac.echo.practice.enums.MapType;
import ac.echo.practice.enums.MatchState;

public class MatchManager {
    ArrayList<Match> matches = new ArrayList<>();

    EchoPractice echoPractice;

    public MatchManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public Match acceptDuelMatch(EchoPlayer requestee, EchoPlayer requester) {
        for (Match match : matches) {
            if (match.getState() == MatchState.REQUESTED) {
                if (match.getRequester().getUuid().equals(requester.getUuid())
                        && match.getRequestee().getUuid().equals(requestee.getUuid())) {
                    match.acceptRequest();
                    return match;
                }
            }
        }
        return null;
    }

    public Match createQueueMatch(EchoPlayer player1, EchoPlayer player2, Queue queue) {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team(player1));
        teams.add(new Team(player2));

        Map map = echoPractice.getMapManager().getRandomFreeMap(queue.getMapType());
        if (map != null) {
            Match match = new Match(teams, map, queue.getKit(), echoPractice, queue, queue.isRanked());

            if (match != null) {
                matches.add(match);

                return match;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Match createDuelMatch(Map map, Kit kit, EchoPlayer requester, EchoPlayer requestee) {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team(requester));
        teams.add(new Team(requestee));

        MapType mapType = kit.getRequiredMapType();
        if (mapType == null) {
            mapType = MapType.NORMAL;
        }

        Match match = new Match(teams, requester, requestee, map, kit, echoPractice);

        matches.add(match);

        return match;
    }

    public void createFFAMatch(Map map, Team team) {

    }

    public Match getIngameMatch(EchoPlayer player) {
        for (Match match : matches) {
            if (match.getState() == MatchState.INGAME) {
                if(match.isInMatch(player)){
                    return match;
                }
            }
        }
        return null;
    }

    public boolean inIngameMatch(EchoPlayer player) {
        for (Match match : matches) {
            if (match.getState() == MatchState.INGAME) {
                if(match.isInMatch(player)){
                    return true;
                }
            }
        }
        return false;
    }

    public Match getRequestedMatch(EchoPlayer player) {
        for (Match match : matches) {
            if (match.getState() == MatchState.REQUESTED) {
                if (match.getRequester() == player) {
                    return match;
                }
            }
        }
        return null;
    }

    public void deletePreviousRequestedMatch(EchoPlayer player) {
        Match requestedMatch = getRequestedMatch(player);

        if (requestedMatch != null) {
            matches.remove(requestedMatch);
        }
    }

    public void addSpectator(EchoPlayer ep, Match match, boolean teleport, boolean noitems) {
        Player p = ep.getPlayer();

        Location specSpawn = new Location(Bukkit.getWorld("world"), (match.getMap().getSpawnPoints().get(0).getX() + match.getMap().getSpawnPoints().get(1).getX()) / 2,
        (match.getMap().getSpawnPoints().get(0).getY() + match.getMap().getSpawnPoints().get(1).getY()) / 2,
        (match.getMap().getSpawnPoints().get(0).getZ() + match.getMap().getSpawnPoints().get(1).getZ()) / 2);
        
        p.setAllowFlight(true);

        Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
            public void run() {
                if(p.getAllowFlight())
                    p.setFlying(true);
            }
        }, 2L);

        specSpawn.setY(specSpawn.getY() + 10);

        if(teleport)
            p.teleport(specSpawn);
        match.addSpectator(ep);
        if(!noitems)
            echoPractice.getItemManager().giveSpectatorItems(ep.getPlayer());
    }

    public int getPlaying(){
        int total = 0;
        for(Match match : matches){
            if(match.getState() == MatchState.INGAME)
                total = total + match.getAlivePlayers().size();
        }

        return total;
    }

    public void removeSpectator(EchoPlayer ep, Match match) {
        Player p = ep.getPlayer();

        match.removeSpectator(ep);

        if(p != null){
            p.setAllowFlight(false);
            p.setFlying(false);

            echoPractice.killPlayer(p);
        }
    }

    public void removeSpectator(EchoPlayer ep){
        for(Match match : matches){
            if(match.hasSpectator(ep)){
               removeSpectator(ep, match);
            }
        }
    }

}
