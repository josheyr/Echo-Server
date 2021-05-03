package ac.echo.core.managers;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;

public class SidebarManager {
    ScoreboardManager scoreboardManager;

    HashMap<UUID, Triple<String, List<String>, Integer>> playerSidebars = new HashMap<>();

    public SidebarManager(){
        scoreboardManager = Bukkit.getScoreboardManager();

        
    }

    public void createSidebar(Player p, String title, List<String> texts, int id){
        playerSidebars.put(p.getUniqueId(), Triple.of(title, texts, id));
    }

    public Triple<String, List<String>, Integer> getSidebar(Player p){
        return playerSidebars.get(p.getUniqueId());
    }

    public boolean hasSidebar(Player p){
        return playerSidebars.containsKey(p.getUniqueId());
    }

    public void destroySidebar(Player p){
        playerSidebars.remove(p.getUniqueId());
    }
}
