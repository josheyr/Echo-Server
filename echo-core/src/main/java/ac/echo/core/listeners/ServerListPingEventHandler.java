package ac.echo.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import ac.echo.core.EchoCore;

public class ServerListPingEventHandler implements Listener {
    EchoCore echoCore;

    public ServerListPingEventHandler(EchoCore echoCore) {
        this.echoCore = echoCore;
    }
    
    @EventHandler
    public void onServerListPing(ServerListPingEvent e){
        e.setMotd(echoCore.getMotd().replace("%ip", e.getAddress().toString()));
    }
}
