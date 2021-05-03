package ac.echo.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import ac.echo.core.adapters.EchoAssembleAdapter;
import ac.echo.core.commands.*;
import ac.echo.core.listeners.*;
import ac.echo.core.managers.*;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;

public class EchoCore extends JavaPlugin implements PluginMessageListener {

    EchoPlayerManager echoPlayerManager;
    RegionManager regionManager;
    SidebarManager sidebarManager;

    boolean pearlCooldownActive = false;
    
    String motd = "No MOTD set.";

    @Override
    public void onEnable() {
        Assemble assemble = new Assemble(this, new EchoAssembleAdapter(this));
        // Start Instance.	
        // Set Interval (Tip: 20 ticks = 1 second).
        assemble.setTicks(2);
        
        // Set Style (Tip: Viper Style starts at -1 and goes down).
        assemble.setAssembleStyle(AssembleStyle.ECHO);

        registerListeners();
        registerCommands();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        echoPlayerManager = new EchoPlayerManager();
        echoPlayerManager.initializeEchoPlayers();

        regionManager = new RegionManager();
        sidebarManager = new SidebarManager();
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }

    public EchoPlayerManager getEchoPlayerManager() {
		return echoPlayerManager;
    }

    public RegionManager getRegionManager(){
        return regionManager;
    }

    public SidebarManager getSidebarManager(){
        return sidebarManager;
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        
        pm.registerEvents(new PlayerJoinEventHandler(this), this);
        pm.registerEvents(new PlayerQuitEventHandler(this), this);
        pm.registerEvents(new PlayerInteractEventHandler(this), this);
        pm.registerEvents(new BlockBreakEventHandler(this), this);
        pm.registerEvents(new BlockPlaceEventHandler(this), this);
        pm.registerEvents(new ServerListPingEventHandler(this), this);
    }

    private void registerCommands() {
        getCommand("edit").setExecutor(new EditCommand(this));
        getCommand("lobby").setExecutor(new LobbyCommand(this));
    }

    public String getMotd(){
        return motd;
    }

    public void setMotd(String motd){
        this.motd = motd;

        Bukkit.getLogger().info("set motd to " + motd);
    }

    public boolean getPearlCooldownActive(){
        return pearlCooldownActive;
    }

    public void setPearlCooldownActive(boolean pearlCooldownActive){
        this.pearlCooldownActive = pearlCooldownActive;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
    }
}
 