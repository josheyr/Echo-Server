package ac.echo.practice.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.managers.SidebarManager;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Match;
import ac.echo.practice.classes.Party;
import ac.echo.practice.classes.Queue;

public class ScoreboardManager {

    SidebarManager sidebarManager;

    EchoPractice echoPractice;

    public ScoreboardManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
        sidebarManager = echoPractice.getSidebarManager();
    }

    public String getStopwatchFormat(long i) {
        String s = "";
        s = ((i % 60) % 10) + s;
        s = ((i % 60) / 10) + s;
        s = ":" + s;
        s = ((i / 60) % 10) + s;
        s = ((i / 60) / 10) + s;

        return s;
    }

    public void giveDefaultScoreboard(Player p) {
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
        if (ep != null) {
            int online = Bukkit.getOnlinePlayers().size();
            int playing = echoPractice.getMatchManager().getPlaying();

            List<String> scoreboard = new ArrayList<String>();

            scoreboard.addAll(Arrays.asList("§a§7§m---------§a§7§m--------§a", "Online: " + "§b" + online,
                    "Playing: " + "§b" + playing));

            Party party = echoPractice.getPartyManager().getParty(ep);
            if (party != null) {
                scoreboard.addAll(Arrays.asList("§b", "Party: ",
                        "  Leader: " + "§b" + party.getLeader().getPlayer().getDisplayName(),
                        "  Members: " + "§b" + (party.getMembers().size() + 1)));
            }

            scoreboard.addAll(Arrays.asList("§a", "§becho.ac", "§b§7§m---------§b§7§m--------§b"));

            sidebarManager.createSidebar(p, "§b§l" + echoPractice.getTitle(), scoreboard, 0);

            Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
                public void run() {
                    if (sidebarManager.getSidebar(p).getRight() == 0) {
                        giveDefaultScoreboard(p);
                    }
                }
            }, 10L);
        }
    }

    public void giveQueuingScoreboard(Player p, Queue queue, long beganQueue) {
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
        if (ep != null) {

            int online = Bukkit.getOnlinePlayers().size();
            int playing = echoPractice.getMatchManager().getPlaying();

            sidebarManager.createSidebar(p, "§b§l" + echoPractice.getTitle(),
                    Arrays.asList("§a§7§m---------§a§7§m--------§a", "Online: " + "§b" + online,
                            "Playing: " + "§b" + playing, "§a", "§b" + queue.getFullName(),
                            "Duration: " + "§b" + getStopwatchFormat((System.currentTimeMillis() - beganQueue) / 1000),
                            "§a", "§becho.ac", "§b§7§m---------§b§7§m--------§b"),
                    1);

            Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
                public void run() {
                    if (sidebarManager.getSidebar(p).getRight() == 1) {
                        giveQueuingScoreboard(p, queue, beganQueue);
                    }
                }
            }, 10L);
        }
    }

    public void giveMatchScoreboard(Player p, Match match) {
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
        if (ep != null) {
            ArrayList<EchoPlayer> aliveOpponents = match.getAliveOpponents(match.getTeam(ep));
            List<String> scoreboard = new ArrayList<String>();
            scoreboard.add("§a§7§m---------§a§7§m--------§a");

            if (aliveOpponents.size() != 0) {
                scoreboard.add("Opponent" + ((boolean) (aliveOpponents.size() == 1) ? "" : "s") + ":");

                int i = 0;
                for (EchoPlayer op : aliveOpponents) {
                    i++;
                    if (i <= 7) {
                        scoreboard.add("§b" + op.getPlayer().getDisplayName());
                    }
                }

                if (i > 7) {
                    scoreboard.add("§7   .. and " + (i - 7) + " more ..");
                }

                scoreboard.add("§b");
            }
            if (match.getStartTime() != 0) {
                scoreboard.addAll(Arrays.asList(
                        "Duration: " + "§b"
                                + getStopwatchFormat((System.currentTimeMillis() - match.getStartTime()) / 1000),
                        "§a"));
            }
            scoreboard.addAll(Arrays.asList("§becho.ac", "§b§7§m---------§b§7§m--------§b"));

            sidebarManager.createSidebar(p, "§b§l" + echoPractice.getTitle(), scoreboard, 2);

            Bukkit.getScheduler().scheduleSyncDelayedTask(echoPractice, new Runnable() {
                public void run() {
                    if (sidebarManager.getSidebar(p).getRight() == 2) {
                        giveMatchScoreboard(p, match);
                    }
                }
            }, 10L);
        }
    }
}
