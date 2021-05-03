package ac.echo.core.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.EchoCore;
import ac.echo.core.classes.EchoPlayer;

public class LobbyCommand implements CommandExecutor {
    EchoCore echoCore;
    
    public LobbyCommand(EchoCore echoCore){
        this.echoCore = echoCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            EchoPlayer ep = echoCore.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
            

            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                out.writeUTF("Connect");
                out.writeUTF("lobby");

                p.sendPluginMessage(echoCore, "BungeeCord", b.toByteArray());
            } catch (IOException e1) {
                p.sendMessage("§cThere was an error while connecting you to §lLobby§c, please try again later. If this issue persists then contact an Administrator!");
            }
        }

        return true;
    }
}
