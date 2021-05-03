package ac.echo.practice;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ac.echo.core.EchoCore;
import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.managers.*;
import ac.echo.practice.classes.Match;
import ac.echo.practice.commands.*;
import ac.echo.practice.listeners.*;
import ac.echo.practice.managers.FinalInventoryManager;
import ac.echo.practice.managers.GuiManager;
import ac.echo.practice.managers.ItemManager;
import ac.echo.practice.managers.KitManager;
import ac.echo.practice.managers.MapManager;
import ac.echo.practice.managers.MatchManager;
import ac.echo.practice.managers.PartyManager;
import ac.echo.practice.managers.QueueManager;
import ac.echo.practice.managers.ScoreboardManager;
import lombok.Getter;

public class EchoPractice extends JavaPlugin {

    EchoCore echoCore;

    @Getter MapManager mapManager;
    @Getter MatchManager matchManager;
    @Getter KitManager kitManager;
    @Getter QueueManager queueManager;
    @Getter GuiManager guiManager;
    @Getter ItemManager itemManager;
    @Getter FinalInventoryManager finalInventoryManager;
    @Getter ScoreboardManager scoreboardManager;
    @Getter PartyManager partyManager;
    @Getter Location spawn;
    @Getter String title;
    
    @Override
    public void onEnable() {        
        echoCore = (EchoCore)getServer().getPluginManager().getPlugin("EchoCore");
        

        echoCore.setPearlCooldownActive(true);
        echoCore.setMotd("\u00A79             \u00A7l \u00A79    \u2727 \u00A7b\u00A7lEcho Practice \u00A79\u2727\u00A7r\n                   \u00A77[\u00A7f1.7x - 1.8x Pot PvP\u00A77]");


        spawn = new Location(Bukkit.getWorld("world"), 0.5, 64, 0.5, -90.0f, 0.0f);
        title = "EU PRACTICE";

        mapManager = new MapManager(this);
        matchManager = new MatchManager(this);
        kitManager = new KitManager(this);
        queueManager = new QueueManager(this);
        guiManager = new GuiManager(this);
        itemManager = new ItemManager(this);
        finalInventoryManager = new FinalInventoryManager(this);
        scoreboardManager = new ScoreboardManager(this);
        partyManager = new PartyManager(this);

        for(Player p : Bukkit.getOnlinePlayers()){
            p.teleport(getSpawn());

            p.setFlying(false);
            p.setAllowFlight(false);

            if(p.getGameMode() != GameMode.SURVIVAL){
                p.setGameMode(GameMode.SURVIVAL);
            }

            p.getInventory().setContents(new ItemStack[36]);
            p.getInventory().setArmorContents(new ItemStack[4]);
            
            getItemManager().giveDefaultItems(p);
            getScoreboardManager().giveDefaultScoreboard(p);
        }

        registerListeners();
        registerCommands();

    }

    @Override
    public void onDisable() {

        kitManager.deapplyAllKits();
        this.getServer().getScheduler().cancelTasks(this);
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerMoveEventHandler(this), this);
        pm.registerEvents(new PlayerJoinEventHandler(this), this);
        pm.registerEvents(new PlayerQuitEventHandler(this), this);
        pm.registerEvents(new BlockBreakEventHandler(this), this);
        pm.registerEvents(new BlockPlaceEventHandler(this), this);
        pm.registerEvents(new PlayerDeathEventHandler(this), this);
        pm.registerEvents(new EntityDamageEventHandler(this), this);
        pm.registerEvents(new EntityDamageByEntityEventHandler(this), this);
        pm.registerEvents(new InventoryClickEventHandler(this), this);
        pm.registerEvents(new EntityRegainHealthEventHandler(this), this);
        pm.registerEvents(new PlayerInteractEventHandler(this), this);
        pm.registerEvents(new PotionSplashEventHandler(this), this);
        pm.registerEvents(new ProjectileLaunchEventHandler(this), this);
        pm.registerEvents(new FoolLevelChangeEventHandler(this), this);
        pm.registerEvents(new BlockFromToEventHandler(this), this);
        pm.registerEvents(new PlayerEmptyBucketEventHandler(this), this);
    }

    private void registerCommands() {
        getCommand("duel").setExecutor(new DuelCommand(this));
        getCommand("accept").setExecutor(new AcceptCommand(this));
        getCommand("kit").setExecutor(new KitCommand(this));
        getCommand("hash").setExecutor(new HashCommand(this));
        getCommand("queue").setExecutor(new QueueCommand(this));
        getCommand("inventory").setExecutor(new InventoryCommand(this));
        getCommand("drain").setExecutor(new DrainCommand(this));
        getCommand("spec").setExecutor(new SpecCommand(this));
        getCommand("party").setExecutor(new PartyCommand(this));
    }

    public EchoCore getCore() {
        return echoCore;
    }
    
    public RegionManager getRegionManager() {
        return echoCore.getRegionManager();
    }

    public EchoPlayerManager getEchoPlayerManager() {
        return echoCore.getEchoPlayerManager();
    }

    public SidebarManager getSidebarManager() {
        return echoCore.getSidebarManager();
    }
    
    public void killPlayer(Player p){
        EchoPlayer ep = getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
        Match match = getMatchManager().getIngameMatch(ep);
    
        ((CraftPlayer) p).getHandle().getDataWatcher().watch(9, (byte) 0);

        if(match != null){
            match.setDead(p, true, true, false);
        }else{
            getKitManager().deapplyKit(p);
            getItemManager().giveDefaultItems(p);
            getScoreboardManager().giveDefaultScoreboard(p);
            p.teleport(getSpawn());
        }
    }
}
