package ac.echo.practice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.utils.Utils;

public class DrainCommand implements CommandExecutor {
    EchoPractice echoPractice;
    
    public DrainCommand(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            EchoPlayer rp = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            Utils.drainBlocks(p.getLocation());
        }

        return true;
    }
}
